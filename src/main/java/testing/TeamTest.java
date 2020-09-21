package testing;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import application.Team;

class TeamTest {

	@Test
	void testPersist() {
		Team newTeam = new Team(6,"Test","Test");
		boolean teamTest = newTeam.persist();
		assertEquals(true,teamTest);
	}

	@Test
	void testRemove() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	Team newTeam = em.find(Team.class, 6);
    	boolean teamTest = newTeam.remove();
    	assertEquals(true,teamTest);
	}

}
