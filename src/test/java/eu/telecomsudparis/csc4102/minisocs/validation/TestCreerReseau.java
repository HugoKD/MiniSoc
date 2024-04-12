// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestCreerReseau {
	/**
	 * interface pour nos tests.
	 */
	private MiniSocs miniSocs;
	/**
	 * pseudo de l'utilisateur qui va créerle reseau.
	 */
	private String pseudoU;
	/**
	 * pseudo membre de l'utilisateur ci-dessus, une fois le réseau créer.
	 */
	private String pseudoM;
	/**
	 * nom du réseau à créer.
	 */
	private String nomRs;
	

	@BeforeEach
	void setUp() {
		miniSocs = new MiniSocs("MiniSocs");
		pseudoU = "utilisateur1";
		pseudoM = "oeoe";
		nomRs = "fb";
		
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		pseudoU = null;
		pseudoM = null;
		nomRs = null;
	}

	@Test
	void creerReseauTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(null, pseudoM,pseudoU));
	}

	@Test
	void creerReseauTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau("", pseudoM,pseudoU));
	}

	@Test
	void creerReseauTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomRs, null, pseudoU));
	}

	@Test
	void creerReseauTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomRs, "", pseudoU));
	}

	@Test
	void creerReseauTest3Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomRs,pseudoM, null));
	}

	@Test
	void creerReseauTest3Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, 
				() ->miniSocs.creerReseau(nomRs,pseudoM, ""));
	}

	@Test
	void creerReseauTest4Puis4() throws Exception {
		Assertions.assertTrue(miniSocs.listerReseaux().isEmpty());
		miniSocs.ajouterUtilisateur(pseudoU,"nom","prenom","bon@courriel.fr");
		miniSocs.creerReseau(nomRs, pseudoM, pseudoU);
		Assertions.assertFalse(miniSocs.listerReseaux().isEmpty());
		Assertions.assertEquals(1, miniSocs.listerReseaux().size());
		Assertions.assertTrue(miniSocs.listerReseaux().get(0).contains(nomRs));
		Assertions.assertTrue(miniSocs.listerReseaux().get(0).contains(pseudoU));
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomRs,pseudoM,pseudoU));
	}
}
