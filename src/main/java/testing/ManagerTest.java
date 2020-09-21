package testing;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import application.Manager;
import application.Name;

class ManagerTest {

	@Test
	void testPersist() {
		Name newName = new Name("Test","Test","Test");
		Manager newManager = new Manager (newName,"Test","Test","01/01/2001",1,-1);
		boolean managerTest = newManager.persist();
		assertEquals(true,managerTest);
	}

	@Test
	void testTransferManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	Manager newManager = em.find(Manager.class,1);
		boolean managerTest = newManager.transferManager(-1);
		assertEquals(true,managerTest);
	}

}
