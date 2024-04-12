package eu.telecomsudparis.csc4102.minisocs;

import java.util.List;
import java.util.ArrayList;

import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe réalise le concept de membre appartenant à un réseau.
 *
 * @author Alex Aïdan
 */
public class Membre {
	/**
	 * pseudo du membre.
	 */
	private String pseudo; 
	/**
	 * moderateur indique true si le membre est moderateur.
	 */
	private boolean moderateur; 
	/**
	 * representation du lien Membre --> Message.
	 */
	private List<Message> messages; 
	/**
	 * reseau auquel le membre appartient.
	 */
	private final Reseau reseau; 
	/**
	 * utilisateur "derrière" le membre.
	 */
	private final Utilisateur utilisateur; 
	
	/**
	 * Constructeur de Membre.
	 * @param pseudo      le pseudo de membre de l'utilisateur
	 * @param reseau      le reseau auquel on ajout un membre
	 * @param utilisateur l'utilisateur qui devient membre du reseau
	 */
	public Membre(final String pseudo, final Reseau reseau, final Utilisateur utilisateur) {
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
        this.utilisateur = utilisateur;
        this.messages = new ArrayList<>();
        this.moderateur = false; 
        reseau.ajoutMembre(this);
        
        assert invariant();
	}
	
	/**
	 * getter pour le pseudo.
	 * @return pseudo du membre
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * getter pour l'utilisateur.
	 * @return utilisateur relié au membre
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	/**
	 * getter pour le reseau.
	 * @return reseau auquel le membre appartient
	 */
	public Reseau getReseau() {
		return reseau;
	}
	
	/**
	 * getter pour les droits de modération.
	 * @return boolean indiquant si le membre est moderateur ou non
	 */
	public boolean estModerateur() {
		return moderateur;
	}
	
	/**
	 * Cette méthode permet de rendre un membre modérateur.
	 */
	public void donnerDroits() {
		moderateur = true;
		assert invariant();
	}
	
	/**
	 * Cette méthode retire les droits de modérations à un membre.
	 */
	public void retirerDroits() {
		moderateur = false;
		assert invariant();
	}
	
	/**
	 *Verifie l'état de compte de l'utilisateur relié au membre.
	 *@return la version String de EtatCompte
	 */
	public String isBloque() {
		return utilisateur.getEtatCompte().toString();
	}
	
	/**
	 *Verifie si un membre est bloqué.
	 *@return une chaine de caractère comportant tous les champs principaux de membre
	 */
	public String toString() {
		return  "Membre [pseudo=" + pseudo + ", utilisateur=" + utilisateur.toString() + "reseau=" + reseau.toString() + "moderateur =" + moderateur + "]";
	}
	 @Override
     public int hashCode() {
             return Objects.hash(utilisateur, reseau);
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
	 
	 /**
		 * getter messages, la liste des message postés par le membre.
		 * @return l'attribut messages
		 */
	 public List<Message> getMessages() {
		 return this.messages;
	 }
	 
	 /**
		 * Cette méthode implémente l'invariant de la classe.
		 * @return boolean indiquant l'intégrité de la classe
		 */
	 public boolean invariant() {
			return reseau != null && utilisateur != null;
		}
	 
	 /**
	  *permet à un membre modérateur d'ajouter un autre utilisateur au réseau et donc de créer un nouveau membre.
	  *@param uAjout   l'utilisateur que le membre veut ajouter
	  *@param pseudoM       le pseudo que le nouveau membre a choisi
	  */
	 public void ajouterMembre(final Utilisateur uAjout, final String pseudoM)
			 throws OperationImpossible {
	        if (uAjout == null) {
	                throw new OperationImpossible("uAjout ne peut pas être null ou vide");
	        }
	        if (pseudoM == null || pseudoM.isBlank()) {
                throw new OperationImpossible("pseudoMAjout ne peut pas être null ou vide");
	        }
	        
	        if (reseau.listerUtilisateur().contains(uAjout)) {
	        	throw new OperationImpossible("l'utilisateur est déjà dans ce réseau");
	        	}
	        if (!moderateur) {
	        	throw new OperationImpossible("le membre n'est pas modérateur");
	        }
	        Reseau rs = reseau;
	        Membre m = new Membre(pseudoM, rs, uAjout);
	        rs.ajoutMembre(m);
		}
	 /**
	  *Cette méthode permet de vérifier la conformité du message avec le standard rfc822.
	  *@param message  le message à tester
	  *@return un boolean indiquant la conformité ou non au standard
	  */
	 public boolean validatorWithRegexRFC822(final String message) {
		    // Expression régulière pour vérifier le format RFC 822
		    Pattern pattern = Pattern.compile("(From: .+\nTo: .+\\r?\\nSubject: .+\\r?\\n\\r?\\n.+)", Pattern.DOTALL);
		    Matcher matcher = pattern.matcher(message);
		    boolean matchFound = matcher.find();
		    return matchFound; //return true if message follows the RFC822 standard
		}
	 
	 /* l objet membre est attaché à un rs donc pas besoin de vérifier qu'il appartient bien à un rs  */
	 /**
	  *Cette méthode permet à un membre de poster un message sur le réseau.
	  *@param message  le message à poster
	  */
     public void posterMessage(final String message) throws OperationImpossible {
                    if (message == null || message.isBlank()) {
                            throw new OperationImpossible("contenu non valide");
                    }
                    if (this.getUtilisateur().getEtatCompte() != EtatCompte.ACTIF) {
                            throw new OperationImpossible("utilisateur non autorisé");
                    }
                    if (!validatorWithRegexRFC822(message)) {
                        throw new OperationImpossible("Le message ne suit pas le standard RCF822");
                    }
                    Message nouveauMessage = new Message(message, this, reseau);
                    this.messages.add(nouveauMessage);
                    if (this.estModerateur()) {
                            reseau.addListeMessage(nouveauMessage); /*message non-soumis au processus de modération*/
                            nouveauMessage.rendreVisible(); //l'attribut visible est mis à true
                    } else {
                            reseau.addListeModo(nouveauMessage); /*message soumis au processus de modération -> Queue*/
                    }                  
    }
     
     /**
	  *Cette méthode permet à un membre de modérer un message sur le réseau.
	  *@param message  le message à modérer
	  */
     public void modererMessage(final Message message, int operation) throws OperationImpossible {  //0 : supp message 1 : rendre non visible
         if (message == null /*|| message.invariant()*/) {
                 throw new OperationImpossible("message non valide");
         }
         if (!message.getReseau().equals(this.reseau)) {
                 throw new OperationImpossible("vous n'êtes pas membre de ce reseau");
         }
         if (!this.estModerateur()) {
             throw new OperationImpossible("Vous n'êtes pas modérateur");
         }
         if (operation == 1) { //on veut rendre visible message 
	         if (message.estVisible()) {
	             throw new OperationImpossible("Le message est déjà visible");
	         }
	         Message messageToMakeVisible = null;
	        
	         // Parcours de la listeModo pour trouver le message  spécifié
	         for (Message mess: this.reseau.listeModo) { 
	             if (mess.getContenu().startsWith(message.getContenu())) {
	            	 messageToMakeVisible = mess;
	                 break;
	             }
	         }
	         if (messageToMakeVisible == null) { // message non trouvé 
	        	 throw new OperationImpossible("Le message n'existe pas");
	         }
	         this.reseau.listeModo.remove(messageToMakeVisible); //on enlève le message de la liste de message à modérer 
	         this.reseau.listeMessages.add(messageToMakeVisible); //on ajoute le message dans la liste de message du réseau
	         message.rendreVisible();
	         
         }
         if (operation == 0) { //on veut supprimer message
        	 if (!message.estVisible()) {
	             throw new OperationImpossible("Le message est déjà visible");
	         }
        	 Message messageToMakeVisible = null;
 	        
	         // Parcours de la listeMessage pour trouver le message  spécifié
	         for (Message mess: this.reseau.listeMessages) { 
	             if (mess.getContenu().startsWith(message.getContenu())) {
	            	 messageToMakeVisible = mess;
	                 break;
	             }
	         }
	         message.rendreinvisble();
	         this.reseau.listeMessages.remove(message);
         }
                       
     }
}
