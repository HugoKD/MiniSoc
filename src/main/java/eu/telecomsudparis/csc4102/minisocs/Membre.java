package eu.telecomsudparis.csc4102.minisocs;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.validator.routines.EmailValidator;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept de membre appartenant à un réseau.
 *
 * @author Alex Aïdan
 */


public class Membre {
	private String pseudo ; 
	private boolean moderateur ;
	private boolean bloque ;
	private final Reseau reseau;
	private final Utilisateur utilisateur ;
	
	
	public Membre(String pseudo,Reseau reseau, Utilisateur utilisateur) {
        if (pseudo == null || pseudo.isBlank()) {
                throw new IllegalArgumentException("pseudo ne peut pas être null ou vide");
        }
        /**if (reseau == null || reseau.isBlank()) {
                throw new IllegalArgumentException("reseau ne peut pas être null ou vide");
        }**/
        this.pseudo = pseudo;
        this.reseau = reseau;
        this.utilisateur = utilisateur ; 
        this.moderateur = false;  
        this.bloque = false ;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur ;
	}
	
	public String getPseudo() {
		return pseudo ;
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
	
	 @Override
     public int hashCode() {
             return Objects.hash(pseudo,reseau);
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
	 /* l obj membre est attaché à un rs donc pas besoin de vérifier qu"il appartient bien à un rs  */
	 public void posterMessage(String message) throws OperationImpossible {
			if (message == null || message == "") {
				throw new OperationImpossible("contenu non valide");
			}
			if (this.getUtilisateur().getEtatCompte() == EtatCompte.ACTIF) {
				throw new OperationImpossible("utilisateur non autorisé");
			}
			
			if (this.estModerateur() == true ) {
				Message nouveauMessageVerifiee = new Message(message, this); /*poster message -> enregistrement + visible */
				this.getReseau().listeMessage.add(nouveauMessageVerifiee);
				
			}
			else {
				Message nouveauMessageNonVerifiee = new Message(message, this);
				this.getReseau().listeModo.add(nouveauMessageNonVerifiee) ; /*message soumis au processus de modération -> Queue*/
			}		 
	}	 
}
