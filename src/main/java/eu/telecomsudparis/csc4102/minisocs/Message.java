package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Cette classe réalise le concept de message publié sur un RS.
 *
 * @author Cadet Hugo 
 */

public class Message {
	/**
	 * contenu du message.
	 */
    private final String contenu;
    /**
	 * membre qui poste le message.
	 */
    private Membre auteur; 
    /**
	 * reseau sur lequel le message est posté.
	 */
    private Reseau reseau;
    /**
	 * visibilité du message sur le reseau.
	 */
    private Boolean visible; 
    /**
	 * horaire auquel le message est posté.
	 */
    private String horaire;
    
    //c est un membre qui publie un message, non un utilisateur, meme si un membre est un utilisateur

    /**
     * Construit un message avec son contenu et son auteur.
     *
     * @param contenu Le contenu du message.
     * @param auteur  L'auteur du message.
     * @param reseau  Le reseau sur lequel le message est posté.
     */
    public Message(final String contenu, final Membre auteur, final Reseau reseau) {
        if (contenu == null || contenu.isBlank()) {
            throw new IllegalArgumentException("Le contenu ne peut pas être null ou vide");
        }
        if (auteur == null) {
            throw new IllegalArgumentException("L'auteur ne peut pas être null");
        }
        if (auteur.isBloque() == "bloqué") {
        	throw new IllegalArgumentException("L'auteur est bloqué et ne peut pas publier de message");
        }
        if (reseau == null) {
            throw new IllegalArgumentException("Le reseau doit exister");
        }
        this.visible = false; 
        this.contenu = contenu;
        this.auteur = auteur;
        this.reseau = reseau; 
        
        /*Récupération de la date et heure du systeme pour identifier le message*/
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormatte = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.horaire = date.format(dateFormatte);
        
        if (auteur.estModerateur()) {
        	this.visible = true;
        }
        
        assert invariant();
    }

  
    private boolean invariant() {
        return contenu != null && !contenu.isBlank() && auteur != null;
    }

    /**
	 * getter pour l'attribut contenu.
	 * @return contenu
	 */
    public String getContenu() {
        return contenu;
    }
    /**
	 * getter pour l'attribut reseau.
	 * @return reseau
	 */
    public Reseau getReseau() {
        return reseau;
    }
    /**
	 * getter pour l'attribut auteur.
	 * @return auteur
	 */
    public Membre getAuteur() {
        return auteur;
    }
    /**
	 * getter pour l'attribut visible.
	 * @return visible
	 */
    public boolean estVisible() {
        return visible;
    }
    /**
	 * methode permettant de rendre visible le message (mettre l'attribut à true).
	 */
    public void rendreVisible() {
        this.visible = true;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(auteur, horaire);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
		return Objects.equals(auteur, other.auteur) && Objects.equals(horaire, other.horaire);
	}

	@Override
	public String toString() {
		return "Message [contenu=" + contenu + ", auteur=" + auteur.toString() + ", reseau=" + reseau.getNomRs() + ", horaire=" + horaire + ", visible=" + visible.toString() + "]";
	}
}
