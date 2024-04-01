package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/*jusqu'à la OK*/
public class Reseau {
		/** Doit on ajouter celui qui a créer le réseau -> on le met directe admin ...**/
		/** c'est un utilisateur qui créer le réseau cf diagramme de classe **/
	
	private final Utilisateur createur;
	private final String nomDuReseau;
	private Map<String, Membre> membres;
	public Queue<Message> listeModo;
	public Queue<Message> listeMessage;

	/*Reseau(nomRs, u, pseudoM)*/
	public Reseau(final String nomDuReseau, final Utilisateur createur, String pseudoM ) throws IllegalArgumentException {
        // Vérification du nom du réseau
        if (nomDuReseau == null || nomDuReseau.isBlank()) {
            throw new IllegalArgumentException("Le nom du réseau doit être non null et non vide");
        }

        // Vérification de l'état du créateur
        if (createur == null) {
            throw new IllegalArgumentException("Le créateur du réseau doit être spécifié");
        }
        if (createur.getEtatCompte().toString() == "bloqué") {
            throw new IllegalArgumentException("Le créateur du réseau est bloqué et ne peut pas créer de réseau");
        }

        this.nomDuReseau = nomDuReseau;
        this.createur = createur;
        this.membres = new HashMap<>(); /** clef/valeur**/
        this.listeModo = listeModo ;
        this.listeMessage = listeMessage ; 
        membres.put(createur.getPseudonyme(), new Membre(pseudoM, this, createur));
    }
	
	public Utilisateur getCreateur() {
        return createur;
    }
	
	public String getNomRs() {
        return nomDuReseau;
    }
	
	public String toString() {
		return "Reseau [nomDuReseau=" + nomDuReseau + ", createur=" + createur + "]";
	}
	
	public Queue getQueue() {
		return this.listeModo ;
	}
	
	public Queue getMessage() {
		return this.listeMessage ;
	}
	
	
	/* Définir une file d'attente correspondant au processus de modération 
	 * et une sorte de liste correspondant à l'ensemble des messages validés */
	
}
