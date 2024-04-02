// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.Reseau;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.minisocs.Message;

class TestMessage {
	private String contenu;
	private Utilisateur utilisateur;
	private Reseau reseau;
	private Membre membre;
	@BeforeEach
	void setUp() {
		 contenu = "message de test";
		 utilisateur = new Utilisateur ("pseudo", "nom", "prénom", "bon@courriel.fr");
		 reseau = new Reseau("nomRS", utilisateur,"pseudoM");
		 membre = reseau.listerMembres().get(0);
		
	}

	@AfterEach
	void tearDown() {
		contenu = null;
		utilisateur =null;
		reseau = null ;
		membre = null ;
	}

	@Test
	void constructeurMessageTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Message("", membre, reseau));
	}

	@Test
	void constructeurMessageTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Message(null, membre, reseau));
	}

	@Test
	void constructeurMessageTest2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Message(contenu, null, reseau));
	}

	@Test
	void constructeurMessageTest3() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Message(contenu, membre, null));
	}

	
	@Test
	void constructeurMessageTest4() {
		Message message = new Message(contenu, membre, reseau);
		Assertions.assertEquals(contenu, message.getContenu());
		Assertions.assertEquals(membre, message.getAuteur());
	}
	
}
