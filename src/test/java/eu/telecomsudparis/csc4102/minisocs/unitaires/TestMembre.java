// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.minisocs.Reseau;
import eu.telecomsudparis.csc4102.minisocs.Membre;

class TestMembre {
	private Utilisateur createur; 
	private Utilisateur utilisateur; 
	private Reseau reseau ; 
	@BeforeEach
	void setUp() {
		createur = new Utilisateur ("createur", "nom", "prénom", "bon@courriel.fr");
		reseau = new Reseau("NomRs",createur,"pseudoM");
		utilisateur = new Utilisateur ("utilisateur", "nom", "prénom", "bon@courriel.fr");
	}

	@AfterEach
	void tearDown() {
		createur = null;
		reseau = null;
		utilisateur = null;
	}
;
	@Test
	void constructeurMembreTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre("", reseau,utilisateur));
	}

	@Test
	void constructeurMembreTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre(null, reseau,utilisateur));
	}

	@Test
	void constructeurMembreTest2Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre("pseudoM", null, utilisateur));
	}

	/**@Test
	void constructeurMembreTest2Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre("pseudoM", blank, utilisateur));
	}
	**/

	@Test
	void constructeurMembreTest3Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre("pseudoM", reseau, null));
	}

	/**@Test
	void constructeurMembreTest3Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre("pseudoM", reseau,blank));
	}**/

	@Test
	void constructeurReseauTest4() {
		Membre membre = new Membre("pseudoM", reseau,utilisateur);
		Assertions.assertNotNull(membre);
		Assertions.assertEquals(2, reseau.listerMembres().size());
		Assertions.assertTrue(reseau.listerUtilisateur().contains(utilisateur));
	}
	

}

