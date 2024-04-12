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

class TestAjouterMembre {
	/**
	 * interface pour nos tests.
	 */
	private MiniSocs miniSocs;
	/**
	 * utilisateur qui va créer le reseau.
	 */
	private Utilisateur utilisateur1 ; 
	/**
	 * utilisateur que l'on va ajouter au reseau.
	 */
	private Utilisateur utilisateur2 ;
	/**
	 * reseau créé par l'utilisateur1.
	 */
	private Reseau rs;
	/**
	 * membre correspondant à l'utilisateur1 dans le réseau rs.
	 */
	private Membre membre1 ;
	/**
	 * pseudo de membre de l'utilisateur2 dans rs.
	 */
	private String pseudoM;
	/**
	 * nom du réseau que l'on va créer.
	 */
	private String nomRs;
	

	@BeforeEach
	void setUp() {
		miniSocs = new MiniSocs("MiniSocs");
		utilisateur1 = new Utilisateur ("pseudo1","nom1","prenom1","bon1@courriel.fr");
		utilisateur2 = new Utilisateur ("pseudo2","nom2","prenom2","bon2@courriel.fr");
		nomRs = "rs";
		rs = new Reseau(nomRs,utilisateur1,"modem");
		membre1 = rs.listerMembres().get(0);
		pseudoM = "pseudoM2";
		
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		utilisateur1 = null;
		utilisateur2 = null;
		nomRs = null;
		rs = null;
		membre1 = null; 
		pseudoM = null;
	}

	@Test
	void ajouterMembreTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.ajouterMembre(null, pseudoM));
	}

	/**@Test
	void ajouterMembreTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.ajouterMembre("", pseudoM));
	}**/

	@Test
	void ajouterMembreTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.ajouterMembre(utilisateur2, null));
	}

	@Test
	void ajouterMembreTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.ajouterMembre(utilisateur2, ""));
	}

	@Test
	void ajouterMembreTest3() throws Exception {
		Assertions.assertEquals(1,rs.listerMembres().size());
		membre1.ajouterMembre(utilisateur2, pseudoM);
		Assertions.assertEquals(2,rs.listerMembres().size());
		Assertions.assertThrows(OperationImpossible.class,
				() -> membre1.ajouterMembre(utilisateur2, pseudoM));
	}
}
