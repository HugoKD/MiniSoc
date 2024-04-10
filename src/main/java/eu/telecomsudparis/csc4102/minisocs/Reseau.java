package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Reseau {
		/** Doit on ajouter celui qui a créer le réseau -> on le met directe admin ...**/
		/** c'est un utilisateur qui créer le réseau cf diagramme de classe **/
	private final Utilisateur createur;
	private final String nomDuReseau;
	private Map<String, Membre> membres;
	public Queue<Message> listeModo; /*liste des messages qu'il faut modérer*/
    public Queue<Message> listeMessage; /*liste des messages validés*/
	
	public Reseau(final String nomDuReseau, final Utilisateur createur,final String pseudoM) throws IllegalArgumentException {
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
        Membre m = new Membre(pseudoM, this, createur);
        m.donnerDroits();	
        membres.put(createur.getPseudonyme(), m);
        this.listeMessage = new LinkedList<>();
        this.listeModo = new LinkedList<>();
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
	
	public Queue<Message> getQueue() {
        return listeModo ;
	}

	public Queue<Message> getMessage() {
        return listeMessage ;
	}
	public void addListeMessage(Message mess) {
		listeMessage.add(mess);
	}
	public void addListeModo(Message mess) {
		listeModo.add(mess);
	}
	public void ajoutMembre(Membre m) {
		membres.put(m.getUtilisateur().getPseudonyme(), m);
	}
	
	public List<String> listerMembresString() {
		return membres.values().stream().map(Membre::toString).toList();
	}
	public List<Utilisateur> listerUtilisateur() {
		return membres.values().stream().map(Membre::getUtilisateur).toList();
	}
	public List<Membre> listerMembres() {
		return membres.values().stream().toList();
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
	
	public boolean invariant() {
		return nomDuReseau != null && !nomDuReseau.isBlank() && membres != null;
	}
	
}
