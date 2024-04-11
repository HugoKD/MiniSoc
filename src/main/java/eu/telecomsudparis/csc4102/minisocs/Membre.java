package eu.telecomsudparis.csc4102.minisocs;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe réalise le concept de membre appartenant à un réseau.
 *
 * @author Alex Aïdan
 */
public class Membre {
	private String pseudo ; 
	private boolean moderateur ;
	private List<Message> messages; // representation du lien Membre --> Message
	private final Reseau reseau;
	private final Utilisateur utilisateur ;
	
	
	public Membre(String pseudo,Reseau reseau, Utilisateur utilisateur) {
        if (pseudo == null || pseudo.isBlank()) {
                throw new IllegalArgumentException("pseudo ne peut pas être null ou vide");
        }
        if (reseau == null /*|| reseau.isBlank()*/) {
                throw new IllegalArgumentException("reseau ne peut pas être null ou vide");
        }
        if (utilisateur == null /*|| utilisateur.isBlank()*/) {
            throw new IllegalArgumentException("reseau ne peut pas être null ou vide");
    }
        this.pseudo = pseudo;
        this.reseau = reseau;
        this.utilisateur = utilisateur ; 
        this.moderateur = false; 
        reseau.ajoutMembre(this);
      
	}
	
	public String getPseudo() {
		return pseudo ;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur ;
	}
	
	public Reseau getReseau() {
		return reseau;
	}
	
	public boolean estModerateur() {
		return moderateur ;
	}
	
	public void donnerDroits() {
		moderateur = true ;
	}
	
	public void retirerDroits() {
		moderateur = false;
	}
	
	public String isBloque() {
		return utilisateur.getEtatCompte().toString();
	}
	
	public String toString() {
		return  "Membre [pseudo=" + pseudo + ", utilisateur=" + utilisateur.toString() +"reseau=" + reseau.toString() + "moderateur =" + moderateur +  "]";
	}
	 @Override
     public int hashCode() {
             return Objects.hash(utilisateur,reseau);
     }
	 
	 @Override
     public boolean equals(final Object obj) {
             if (this == obj) {
                     return true;
             }
             if (!(obj instanceof Membre)) {
                     return false;
             }
             Membre other = (Membre) obj;
             return Objects.equals(pseudo, other.pseudo);
     }
	 
	 public List<Message> getMessages() {
		 return this.messages ;
	 }
	 
	 public boolean invariant() {
			return reseau != null && utilisateur != null;
		}
	 
	 public void ajouterMembre(final Utilisateur uAjout,final String pseudoM)
			 throws OperationImpossible{
	        if (uAjout == null /**|| pseudoUAjout.isBlank()**/) {
	                throw new OperationImpossible("uAjout ne peut pas être null ou vide");
	        }
	        if (pseudoM == null || pseudoM.isBlank()) {
                throw new OperationImpossible("pseudoMAjout ne peut pas être null ou vide");
	        }
	        
	        if (reseau.listerUtilisateur().contains(uAjout)) {
	        	throw new OperationImpossible("l'utilisateur est déjà dans ce réseau");}
	        if (!moderateur) {
	        	throw new OperationImpossible("le membre n'est pas modérateur");
	        }
	        Reseau rs = reseau ;
	        Membre m = new Membre(pseudoM, rs, uAjout) ;
	        rs.ajoutMembre(m);
		}
	 
	 public boolean RFC822ValidatorWithRegex(String message) {
		    // Expression régulière pour vérifier le format RFC 822
		    String regex = "(^From: .+\\r?\\nTo: .+\\r?\\nSubject: .+\\r?\\n\\r?\\n.+)";
		    Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		    Matcher matcher = pattern.matcher(message);
		    return matcher.matches(); //return true if message follows the RFC822 standard
		}
	 
	 /* l objet membre est attaché à un rs donc pas besoin de vérifier qu"il appartient bien à un rs  */
     public void posterMessage(String message) throws OperationImpossible {
                    if (message == null || message.isBlank()) {
                            throw new OperationImpossible("contenu non valide");
                    }
                    if (this.getUtilisateur().getEtatCompte() != EtatCompte.ACTIF) {
                            throw new OperationImpossible("utilisateur non autorisé");
                    }
                    if (RFC822ValidatorWithRegex(message)==false) {
                        throw new OperationImpossible("Le message ne suit pas le standard RCF822");
                    }
                    Message nouveauMessage = new Message(message, this, reseau);
                    this.messages.add(nouveauMessage);
                    if (this.estModerateur() == true ) {
                            reseau.addListeMessage(nouveauMessage); /*message non-soumis au processus de modération*/

                    }
                    else {
                            reseau.addListeModo(nouveauMessage) ; /*message soumis au processus de modération -> Queue*/
                    }                  
    }
}
