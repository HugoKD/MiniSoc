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
    private Utilisateur utilisateurModo;
    private Membre modo ;
    private Utilisateur utilisateurbloque ;
    private Membre membrebloque ;
    private Membre membre ;
    private Utilisateur createur;
    private MiniSocs miniSocs;
    private String utilisateur;
    private String message;
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

    // Test pour vérifier le cas où le message est posté avec succès par un membre non modo
    @Test
    void posterMessageSuccesNonModo() throws Exception {
        miniSocs.ajouterUtilisateur(utilisateur, "nom", "prenom", "test@test.com");
        membre = new Membre("membre", reseau, createur);
        membre.posterMessage(message);
        // Vérification que le message est bien posté en vérifiant s'il est présent dans les messages de l'utilisateur
        Assertions.assertTrue(reseau.listeModo.peek().getContenu().equals(message));
    }

    // Test pour vérifier que le message est bien dans listeMess direct si le membre est un modérateur
    @Test
    void posterMessageSuccesModo() throws Exception {
        miniSocs.ajouterUtilisateur(utilisateur, "nom", "prenom", "test@test.com");
        modo = new Membre("modo", reseau, createur);
        modo.donnerDroits();
        modo.posterMessage(message);
        // Vérification que le message est bien posté en vérifiant s'il est présent dans les messages de l'utilisateur
        Assertions.assertTrue(reseau.listeMessage.peek().getContenu().equals(message));
    }
}