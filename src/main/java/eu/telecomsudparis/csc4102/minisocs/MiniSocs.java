package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Denis Conan
 */
public class MiniSocs {
	/**
	 * le nom du système.
	 */
	private final String nomDuSysteme;
	/**
	 * les utilisateurs.
	 * permet de stocker sous forme clé-valeur
	 */
	private final Map<String, Utilisateur> utilisateurs; 
	/**
	 * les réseaux.
	 * permet de stocker sous forme clé-valeur
	 */
	private final Map<String, Reseau> reseaux;
	/**
	 * construit une instance du système.
	 * 
	 * @param nomDuSysteme le nom.
	 */
	public MiniSocs(final String nomDuSysteme) throws IllegalArgumentException {
		if (nomDuSysteme == null || nomDuSysteme.isBlank()) {
			throw new IllegalArgumentException("Le nom du systeme doit être non null et non vide");
		}
		this.nomDuSysteme = nomDuSysteme;
		this.utilisateurs = new HashMap<>();
		this.reseaux = new HashMap<>();
	}

	/**
	 * l'invariant de la façade.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return nomDuSysteme != null && !nomDuSysteme.isBlank() && utilisateurs != null;
	}

	/**
	 * ajoute un utilisateur.
	 * 
	 * @param pseudo   le pseudo de l'utilisateur.
	 * @param nom      le nom de l'utilisateur.
	 * @param prenom   le prénom de l'utilisateur.
	 * @param courriel le courriel de l'utilisateur.
	 * @throws OperationImpossible en cas de problème sur les pré-conditions.
	 */
	public void ajouterUtilisateur(final String pseudo, final String nom, final String prenom, final String courriel)
			throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new OperationImpossible("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new OperationImpossible("prenom ne peut pas être null ou vide");
		}
		if (courriel == null || courriel.isBlank()) {
			throw new OperationImpossible("courriel ne peut pas être null ou vide");
		}
		if (!EmailValidator.getInstance().isValid(courriel)) {
			throw new OperationImpossible("courriel ne respecte pas le standard RFC822");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u != null) {
			throw new OperationImpossible(pseudo + "déjà un utilisateur");
		}
		utilisateurs.put(pseudo, new Utilisateur(pseudo, nom, prenom, courriel));
		assert invariant();
	}

	/**
	 * liste les utilisateurs.
	 * 
	 * @return la liste des pseudonymes des utilisateurs.
	 */
	public List<String> listerUtilisateurs() {
		return utilisateurs.values().stream().map(Utilisateur::toString).toList();
	}
	/**
	 * liste les reseaux.
	 * 
	 * @return la liste des versions string des réseaux.
	 */
	public List<String> listerReseaux() {
		return reseaux.values().stream().map(Reseau::toString).toList();
	}
	

	/**
	 * désactiver son compte utilisateur.
	 * 
	 * @param pseudo le pseudo de l'utilisateur.
	 * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
	 */
	public void desactiverCompteUtilisateur(final String pseudo) throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo ne peut pas être null ou vide");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u == null) {
			throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudo + ")");
		}
		if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
			throw new OperationImpossible("le compte est bloqué");
		}
		u.desactiverCompte();
		assert invariant();
	}

	/**
	 * obtient le nom du projet.
	 * 
	 * @return le nom du projet.
	 */
	public String getNomDeProjet() {
		return nomDuSysteme;
	}

	@Override
	public String toString() {
		return "MiniSocs [nomDuSysteme=" + nomDuSysteme + ", utilisateurs=" + utilisateurs + "]";
	}
	
	/**
	 * Methode pour creer un reseau.
	 * 
	 * @param nomRs   le nom du reseau que l'on veut creer
	 * @param pseudoM le pseudo que le créateur du reseau veut adopter en tant que membre
	 * @param pseudoU le pseudo de l'utilisateur qui veut créer le reseau
	 */
	public void creerReseau(final String nomRs, final String pseudoM, final String pseudoU)/*celui qui créer un réseau est
    automatiquement mis modérateur ! Donc dès que l'on créer un réseau on demande un pseudo*/
			throws OperationImpossible {
		if (pseudoM == null || pseudoM.isBlank()) {
			throw new OperationImpossible("pseudoM ne peut pas être null ou vide");
		}
		if (pseudoU == null || pseudoU.isBlank()) {
			throw new OperationImpossible("pseudoU ne peut pas être null ou vide");
		}
		if (nomRs == null || nomRs.isBlank()) {
			throw new OperationImpossible("nomRs ne peut pas être null ou vide");
		}
		Utilisateur u = utilisateurs.get(pseudoU);
		if (u == null) {
			throw new OperationImpossible(pseudoU + "n'existe pas");
		}
		Reseau rs = reseaux.get(nomRs);
		if (rs != null) {
			throw new OperationImpossible(nomRs + "déjà un réseau");
		}
		reseaux.put(nomRs, new Reseau(nomRs, u, pseudoM));
		assert invariant();
	}
	
}
