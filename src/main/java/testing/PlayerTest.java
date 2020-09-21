package testing;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import application.Name;
import application.Player;

class PlayerTest {

	@Test
	void testPersist() {
		Name newName = new Name("Test","Test","Test");
		Player newPlayer = new Player (newName,"Test","Test",0,true,-1);
		boolean playerTest = newPlayer.persist();
		assertEquals(true,playerTest);
	}

	@Test
	void testEditPlayer() {
		Name newName = new Name("Test","Test","Test");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	Player newPlayer = em.find(Player.class, 1);
		boolean playerTest = newPlayer.editPlayer(newName,"Test","Test",0,true,-1);
		assertEquals(true,playerTest);
	}

	@Test
	void testTransferPlayer() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	Player newPlayer = em.find(Player.class,1);
		boolean playerTest = newPlayer.transferPlayer(-1);
		assertEquals(true,playerTest);
	}

	@Test
	void testUpdateGoals() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	Player newPlayer = em.find(Player.class,1);
		boolean playerTest = newPlayer.updateGoals(0);
		assertEquals(true,playerTest);
	}

}
