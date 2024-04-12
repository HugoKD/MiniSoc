package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Cette classe realise le concept de réseau.
 * 
 * @author Alex Aidan
 */

public class Reseau {
	/**
	 * utilisateur qui crée le reseau.
	 */
	private final Utilisateur createur;
	/**
	 * nom du reseau.
	 */
	private final String nomDuReseau;
	/**
	 * Liste des membres qui font partis du réseau.
	 */
	private Map<String, Membre> membres;
	/**
	 * Liste des messages à modérer.
	 */
	public LinkedList<Message> listeModo;
	/**
	 * Liste des messages déjà validés.
	 */
    public LinkedList<Message> listeMessages;
    
    /**
	 * Constructeur du reseau.
	 * @param nomDuReseau
	 * @param createur
	 * @param pseudoM
	 */	
	public Reseau(final String nomDuReseau, final Utilisateur createur, final String pseudoM) throws IllegalArgumentException {
        // Vérification du nom du réseau
        if (nomDuReseau == null || nomDuReseau.isBlank()) {
            throw new IllegalArgumentException("Le nom du réseau doit être non null et non vide");
        }

        // Vérification de l'état du créateur
        if (createur == null) {
            throw new IllegalArgumentException("Le créateur du réseau doit être spécifié");
        }
        if (createur.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new IllegalArgumentException("Le créateur du réseau est bloqué et ne peut pas créer de réseau");
        }

        this.nomDuReseau = nomDuReseau;
        this.createur = createur;
        //clef/valeur
        this.membres = new HashMap<>(); 
        Membre m = new Membre(pseudoM, this, createur);
        m.donnerDroits();	
        membres.put(createur.getPseudonyme(), m);
        this.listeMessages = new LinkedList<>();
        this.listeModo = new LinkedList<>();
        assert invariant();
    }
	
	
	/**
	 * getter pour l'attribut createur.
	 * @return createur
	 */
	public Utilisateur getCreateur() {
        return createur;
    }
	/**
	 * getter pour l'attribut nomDuReseau.
	 * @return nomDuReseau
	 */
	public String getNomRs() {
        return nomDuReseau;
    }
	/**
	 * getter pour la liste des messages à modérer.
	 * @return liste de messages
	 */
	public Queue<Message> getQueue() {
        return listeModo;
	}
	/**
	 * getter pour la liste des messages déjà modérés.
	 * @return liste de messages
	 */	
	public Queue<Message> getMessages() {
        return listeMessages;
	}
	/**
	 * methode permettant d'avoir la liste des membres qui font partis du réseau.
	 * @return la liste des membres
	 */
	public List<Membre> listerMembres() {
		return membres.values().stream().toList();
	}
	/**
	 * methode permettant d'avoir la liste des membres qui font partis du réseau, au format String.
	 * @return la liste des formats String des membres
	 */
	public List<String> listerMembresString() {
		return membres.values().stream().map(Membre::toString).toList();
	}
	/**
	 * methode permettant d'avoir la liste des utilisateurs qui font partis du réseau.
	 * @return la liste des utilisateurs
	 */
	public List<Utilisateur> listerUtilisateur() {
		return membres.values().stream().map(Membre::getUtilisateur).toList();
	}
	
	
	/**
	 * methode permettant d'afficher le reseau en format chaine de caractères.
	 * @return chaine de caractère
	 */
	public String toString() {
		return "Reseau [nomDuReseau=" + nomDuReseau + ", createur=" + createur + "]";
	}
	/**
	 * methode permettant d'ajouter un message à la liste des messages validés.
	 * @param mess le message à ajouter
	 */
	public void addListeMessage(final Message mess) {
		listeMessages.add(mess);
		assert invariant();
	}
	/**
	 * methode permettant d'ajouter un message à la liste des messages à modérer.
	 * @param mess le message à ajouter
	 */
	public void addListeModo(final Message mess) {
		listeModo.add(mess);
		assert invariant();
	}
	/**
	 * methode permettant d'actualiser la présence d'un nouveau membre côté réseau.
	 * @param m le membre à ajouter
	 */
	public void ajoutMembre(final Membre m) {
		membres.put(m.getUtilisateur().getPseudonyme(), m);
		assert invariant();
	}

	
	/**
	 * methode verifiant l'intégrité de l'invariant.
	 * @return boolean témoignant de cette intégrité
	 */
	public boolean invariant() {
		return nomDuReseau != null && !nomDuReseau.isBlank() && membres != null;
	}
	
	@Override
    public boolean equals(final Object obj) {
            if (this == obj) {
                    return true;
            }
            if (!(obj instanceof Reseau)) {
                    return false;
            }
            Reseau other = (Reseau) obj;
            return Objects.equals(nomDuReseau, other.nomDuReseau);
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(nomDuReseau);
	}
	
	
}
