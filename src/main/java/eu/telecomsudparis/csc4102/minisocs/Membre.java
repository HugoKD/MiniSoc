package eu.telecomsudparis.csc4102.minisocs;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Cette classe réalise le concept de membre appartenant à un réseau.
 *
 * @author Alex Aïdan
 */
public class Membre {
	private String pseudo ; 
	private boolean moderateur ;
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
}
