package pojoFiles;

public class userRequests {
	
	int aptno;
	String req;
	public userRequests(int aptno, String req) {
		super();
		this.aptno = aptno;
		this.req = req;
	}
	public int getAptno() {
		return aptno;
	}
	public void setAptno(int aptno) {
		this.aptno = aptno;
	}
	public String getReq() {
		return req;
	}
	@Override
	public String toString() {
		return "userRequests [aptno=" + aptno + ", req=" + req + "]";
	}
	public void setReq(String req) {
		this.req = req;
	}
	

}
