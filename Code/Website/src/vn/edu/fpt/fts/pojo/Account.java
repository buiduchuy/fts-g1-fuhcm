package vn.edu.fpt.fts.pojo;

/**
 * @author Huy
 *
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8903802688006955800L;
	private int accountID;
	private String email;
	private String password;
	private int roleID;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public Account(int accountID, String email, String password, int roleID) {
		super();
		this.accountID = accountID;
		this.email = email;
		this.password = password;
		this.roleID = roleID;
	}

}
