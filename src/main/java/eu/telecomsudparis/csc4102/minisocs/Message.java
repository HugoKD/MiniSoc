package eu.telecomsudparis.csc4102.minisocs;

/**
 * Cette classe réalise le concept de message publié sur un RS
 *
 * @author Cadet Hugo 
 */

public class Message {
  
    private final String contenu;

    private Membre auteur; 
    
    private Reseau reseau;
    /** c est un membre qui publie un message, non un utilisateur, meme si un membre est un utilisateur**/

    /**
     * Construit un message avec son contenu et son auteur.
     *
     * @param contenu Le contenu du message.
     * @param auteur  L'auteur du message.
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
        this.contenu = contenu;
        this.auteur = auteur;
        this.reseau = reseau ; 
        assert invariant();
    }

  
    private boolean invariant() {
        return contenu != null && !contenu.isBlank() && auteur != null;
    }

    // Getters pour accéder aux attributs

    public String getContenu() {
        return contenu;
    }

    public Membre getAuteur() {
        return auteur;
    }
}
