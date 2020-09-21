package application;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.hibernate.Session;

/**
 * The Team class/entity used to create Team objects with ID, name and jersey colour. 
 * @author Maciej Becmer
 *
 */
@Table (name= "Team")
@Entity
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * teamID which identifies the team.
	 * @author Maciej Becmer
	 */
	@Id
	private int teamID;
	/**
	 * Name of the team
	 * @author Maciej Becmer
	 */
	@Column
	private String name;
	/**
	 * jersey colour of the team
	 * @author Maciej Becmer
	 */
	@Column
	private String jersey;
	
	
	/**
	 * Default constructor
	 * @author Maciej Becmer
	 */
	public Team() {
		this(0,"Name","Jersey");
	}
	
	/**
	 * Class constructor
	 * @author Maciej Becmer
	 */
	public Team(int ID,String n, String j) {
		this.teamID = ID;
		this.name = n;
		this.jersey = j;
	}
	
	/**
	 * Used to save the team to the database
	 * @author Maciej Becmer
	 */
	public boolean persist() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	try {
	    	em.getTransaction().begin();
	    	em.persist(this);
	    	em.getTransaction().commit();
    	}
    	catch (Error error) {
    		return false;
    	}
    	return true;
	}
	
	/**
	 * Used to remove a team from the database
	 * @author Maciej Becmer
	 */
	@SuppressWarnings("null")
	public boolean remove() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	int thisTeamID = this.teamID;
    	int j = 1;
    	Manager newManager = em.find(Manager.class, j);
    	while (newManager != null) {
    		if (newManager.getTeamID() == this.teamID) {
    			newManager.transferManager(-1);
    		}
    		j ++;
    		newManager = em.find(Manager.class, j);
    	}
    	int i = 1;
    	Player newPlayer = em.find(Player.class, i);
    	while (newPlayer != null) {
    		if (newPlayer.getTeamID() == this.teamID) {
    			newPlayer.transferPlayer(-1);
    		}
    		i ++;
    		newPlayer = em.find(Player.class, i);
    	}
    	
    	try {
	    	Team newTeam = em.merge(this);
	    	em.getTransaction().begin();
	    	em.remove(newTeam);
	    	em.getTransaction().commit();
    	}
    	catch (Error error) {
    		return false;
    	}

    	return true;
    	
	}
	
	
	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJersey() {
		return jersey;
	}
	public void setJersey(String jersey) {
		this.jersey = jersey;
	}
	
}
