package pojoFiles;



public class Tenant {
	
	String firstName;
	String lastName;
	int aptNo;
	String emailid;
	public Tenant(String firstName, String lastName, int aptNo, String emailid) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.aptNo = aptNo;
		this.emailid = emailid;
	}
	public Tenant(String firstName, String lastName, String emailid) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailid = emailid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAptNo() {
		return aptNo;
	}
	public void setAptNo(int aptNo) {
		this.aptNo = aptNo;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	@Override
	public String toString() {
		return "Tenant [FirstName=" + firstName + ", LastName=" + lastName + ", aptNo=" + aptNo + ", Emailid=" + emailid
				+ "]";
	}
	
	
	

}

