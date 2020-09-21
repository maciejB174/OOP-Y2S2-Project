package application;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;
import javax.persistence.Persistence;

import org.hibernate.Session;

/**
 * Player class/entity used to create a Player object.
 * @author Maciej Becmer
 *
 */
@Entity
@Table (name = "Player")
public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "Id", unique = true)
	private int id;
	@Embedded
	@Column (name = "Name")
	private Name name;
	@Column (name = "phone")
	private String phone;
	@Column (name = "email")
	private String email;
	@Column (name = "goals")
	private int goals;
	@Column (name = "isGoalie")
	private boolean isGoalie;
	@Column(name= "teamID")
	private int teamID;
	
	public Player() {
		this(new Name(), "phone", "email", 0, false, -1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public boolean isGoalie() {
		return isGoalie;
	}

	public void setGoalie(boolean isGoalie) {
		this.isGoalie = isGoalie;
	}
	
	public int getTeamID() {
		return teamID;
	}
	
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	
	/**
	 * Saves the player to the database.
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
	 * Edits the players details in the database
	 * @author Maciej Becmer
	 */
	public boolean editPlayer(Name name, String phone, String email, int goals, boolean isGoalie, int teamID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		try {
			Session session = em.unwrap(Session.class);
			em.getTransaction().begin();
			this.setName(name);
			this.setPhone(phone);
			this.setEmail(email);
			this.setGoals(goals);
			this.setGoalie(isGoalie);
			this.setTeamID(teamID);
			session.saveOrUpdate(this);
			em.getTransaction().commit();
		}
		catch (Error error) {
			return false;
		}
		return true;

		
	}
	
	/**
	 * Transfers the player from a team to another
	 * @author Maciej Becmer
	 */
	public boolean transferPlayer (int teamID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		try {
			Session session = em.unwrap(Session.class);
			em.getTransaction().begin();
			this.setTeamID(teamID);
			session.saveOrUpdate(this);
			em.getTransaction().commit();
		}
		catch (Error error) {
			return false;
		}
		return true;


	}
	
	/**
	 * updates the player's goals
	 * @author Maciej Becmer
	 */
	public boolean updateGoals(int goals) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		try {
			Session session = em.unwrap(Session.class);
			em.getTransaction().begin();
			this.setGoals(goals);
			session.saveOrUpdate(this);
			em.getTransaction().commit();
		}
		catch (Error error) {
			return false;
		}
		return true;

	}
	
	/**
	 * class constructor
	 * @author Maciej Becmer
	 */
	public Player(Name name, String phone, String email, int goals, boolean isGoalie, int teamID) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.goals = goals;
		this.isGoalie = isGoalie;
		this.teamID = teamID;
	}

}
