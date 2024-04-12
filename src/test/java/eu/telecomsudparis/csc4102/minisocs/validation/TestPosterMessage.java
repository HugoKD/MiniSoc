package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.Reseau;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestPosterMessage {
	/** 
	 * Utilisateur qui sera ajouter en modérateur du réseau.
	 */
    private Utilisateur utilisateurModo;
    /** 
	 * Membre modérateur, qui va correspondre à l'utilisateur ci-dessus.
	 */
    private Membre modo;
    /** 
	 * Utilisateur qui sera bloqué.
	 */
    private Utilisateur utilisateurbloque;
    /** 
	 * Membre qui va correspondre à l'utilisateur ci-dessus.
	 */
    private Membre membrebloque;
    /** 
	 * Membre qui va correspondre au créateur du réseau.
	 */
    private Membre membre;
    /** 
	 * Utilisateur créateur du réseau.
	 */
    private Utilisateur createur;
    /** 
	 * Interface MiniSocs.
	 */
    private MiniSocs miniSocs;
    /** 
	 * pseudo de utilisateur.
	 */
    private String utilisateur;
    /** 
	 * contenu du message.
	 */
    private String message;
    /** 
	 * Reseau.
	 */
    private Reseau reseau;

    @BeforeEach
    void setUp() {
        createur = new Utilisateur("createur_pseudo", "nom_createur", "prenom_createur", "nom.prenom@gmail.com");
        reseau = new Reseau("nomReseau", createur, "pseudo");
        utilisateurbloque = new Utilisateur("utilisateurbloque", "nom", "prenom", "utilisateurbloque@test.com");
        membrebloque = new Membre("pseudo", reseau, utilisateurbloque);
        utilisateurbloque.bloquerCompte();
        miniSocs = new MiniSocs("MiniSocs");
        utilisateur = createur.getPseudonyme(); // Utilisation du pseudo de l'utilisateur créé
        message = "Ceci est un message de test.";
        
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        utilisateur = null;
        message = null;
    }

    @Test
    void posterMessageUtilisateurNull() {
        Assertions.assertThrows(OperationImpossible.class,
                () -> membrebloque.posterMessage(null));
    }
    
    // Test pour vérifier qu'un message non valide aux normes RFC822 ne passe pas 
    @Test
    void testValidMessage() throws Exception {
    	miniSocs.ajouterUtilisateur(utilisateur, "nom", "prenom", "test@test.com");
        membre = new Membre("membre", reseau, createur);
        membre.posterMessage(message);
        String validMessage = "From: sender@example.com \n"
                            + "To: recipient@example.com \n"
                            + "Subject: Test Message \n"
                            + "Date: Mon, 11 Apr 2024 12:00:00 GMT \n"
                            + "\n"
                            + "This is a test message.";

        Assertions.assertTrue(membre.validatorWithRegexRFC822(validMessage));
    }

    @Test
    void testInvalidMessage() throws Exception {
    	miniSocs.ajouterUtilisateur(utilisateur, "nom", "prenom", "test@test.com");
        membre = new Membre("membre", reseau, createur);
        membre.posterMessage(message);
        String invalidMessage = "From: sender@example.com\n"
                              + "To: recipient@example.com\n"
                              + "\n"
                              + "This is a test message.";

        Assertions.assertFalse(membre.validatorWithRegexRFC822(invalidMessage));
    }

        
    // Test pour vérifier le cas où le message est posté avec succès par un membre non modo
    @Test
    void posterMessageSuccesNonModo() throws Exception {
        // Vérification que le message est bien posté en vérifiant s'il est présent dans les messages de l'utilisateur
        Assertions.assertTrue(reseau.getQueue().peek().getContenu().equals(message));
        Assertions.assertFalse(reseau.getMessages().peek().getContenu().equals(message));
    }

    // Test pour vérifier que le message est bien dans listeMess direct si le membre est un modérateur
    @Test
    void posterMessageSuccesModo() throws Exception {
        miniSocs.ajouterUtilisateur(utilisateur, "nom", "prenom", "test@test.com");
        modo = new Membre("modo", reseau, createur);
        modo.donnerDroits();
        modo.posterMessage(message);
        // Vérification que le message est bien posté en vérifiant s'il est présent dans les messages de l'utilisateur
        Assertions.assertTrue(reseau.getMessages().peek().getContenu().equals(message));
        Assertions.assertFalse(reseau.getQueue().peek().getContenu().equals(message));

    }
    
}