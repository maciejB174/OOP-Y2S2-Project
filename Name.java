package application;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Name class which is used to create Name objects and is embeddable in the Manager and Player entities
 * @author Maciej Becmer
 *
 */
@Embeddable
public class Name {
	
	@Column(name = "fname")
	private String fname;
	@Column(name = "mname")
	private String mname;
	@Column(name = "lname")
	private String lname;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/**
	 * Default constructor
	 * @author Maciej Becmer
	 */
	public Name() {
		this("fname", "mname", "lname");
	}

	/**
	 * class constructor
	 * @author Maciej Becmer
	 */
	public Name(String f, String m, String l) {
		this.fname = f;
		this.mname = m;
		this.lname = l;
	}
	
}