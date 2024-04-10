// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;

class TestMiniSocs {
	private String nom = "nomDuSysteme" ;

	@BeforeEach
	void setUp() {
		
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void constructeurMiniSocsTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new MiniSocs(""));
	}

	@Test
	void constructeurMiniSocsTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new MiniSocs(null));
	}

	@Test
	void constructeurMiniSocsTest3() {
		MiniSocs ms = new MiniSocs(nom);
		Assertions.assertEquals(ms.getNomDeProjet(),nom );
	}

	
}
