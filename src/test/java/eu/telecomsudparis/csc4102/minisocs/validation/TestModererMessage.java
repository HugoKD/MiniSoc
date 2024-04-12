// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.Reseau;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur ;

class TestModererMessage {
	/** 
	 * Interface pour nos tests.
	 */
	private MiniSocs miniSocs;
	/** 
	 * reseau où l'on souhaite modérer le message.
	 */
	private Reseau rs;
	/** 
	 * deuxième réseau permettant de faire des tests.
	 */
	private Reseau rs2;
	/** 
	 * utilisateur1 qui sera créteur du réseau rs.
	 */
	private Utilisateur utilisateur1;
	/** 
	 * utilisateur2 qui sera ajouter en membre non modérateur du réseau.
	 */
	private Utilisateur utilisateur2;
	/** 
	 * membre correspondant à l'utilisateur1 sur le réseau rs.
	 */
	private Membre membre1;
	/** 
	 * membre correspondant à l'utilisateur2 sur le réseau rs.
	 */
	private Membre membre2;
	/** 
	 * membre correspondant à l'utilisateur2 sur le réseau rs2 où il est modérateur.
	 */
	private Membre membre3;
	/** 
	 * message que l'on souhaite modérer.
	 */
	private Message message;
	

	@BeforeEach
	void setUp() throws OperationImpossible {
		miniSocs = new MiniSocs("MiniSocs");
		utilisateur1 = new Utilisateur ("pseudo1","nom1","prenom1","bon1@courriel.fr");
		utilisateur2 = new Utilisateur ("pseudo2","nom2","prenom2","bon1@courriel.fr");
		rs = new Reseau("nomRs",utilisateur1,"modem");
		membre1 = rs.listerMembres().get(0);
		membre1.ajouterMembre(utilisateur2, "pasModem");
		membre2 = rs.listerMembres().get(0);
		rs2 = new Reseau("nomRs2",utilisateur2,"modem");
		membre3 = rs2.listerMembres().get(0);
		message = new Message("(^From: .+\\r?\\nTo: .+\\r?\\nSubject: .+\\r?\\n\\r?\\n.+)", membre2, rs);
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		utilisateur1 = null;
		utilisateur2 = null;
		rs = null;
		rs2=null;
		membre1 = null; 
		membre2 = null; 
		membre3 = null ; 
		message = null;
	}

	@Test
	void modererMessageTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.modererMessage(null));
	}
	@Test
	void modererMessageTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre3.modererMessage(message));
	}
	@Test
	void modererMessageTest3Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre2.modererMessage(message));
	}
	@Test
	void modererMessage4Jeu1() throws Exception {
		message.rendreVisible();
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.modererMessage(message));
	}

	@Test
	void modererMessageTest5() throws Exception {
		membre1.modererMessage(message);
		Assertions.assertTrue(message.estVisible());
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.modererMessage(message));
	}
}