// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.minisocs.Reseau;

class TestReseau {
	private Utilisateur utilisateur; 
	@BeforeEach
	void setUp() {
		utilisateur = new Utilisateur ("pseudo", "nom", "prénom", "bon@courriel.fr");
	}

	@AfterEach
	void tearDown() {
		utilisateur = null;
	}
;
	@Test
	void constructeurReseauTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Reseau(null, utilisateur,"pseudoM"));
	}

	@Test
	void constructeurReseauTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Reseau("", utilisateur,"pseudoM"));
	}

	@Test
	void constructeurReseauTest2Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Reseau("nomRS", null,"pseudoM"));
	}

	/**@Test
	void constructeurReseauTest2Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Reseau("nomRS", blank,"pseudoM"));
	}
	**/

	@Test
	void constructeurReseauTest3Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Reseau("nomRS", utilisateur,null));
	}

	@Test
	void constructeurReseauTest3Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Reseau("nomRS", utilisateur,""));
	}

	@Test
	void constructeurReseauTest4() {
		Reseau reseau = new Reseau("nomRS", utilisateur,"pseudoM");
		Assertions.assertNotNull(reseau);
		Assertions.assertEquals("nomRS", reseau.getNomRs());
		Assertions.assertTrue(reseau.listerUtilisateur().contains(utilisateur));
	}
	

}

