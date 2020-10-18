package in.jk.springboot.jwt;

public class JwtTokenResponse {
	
	private String userName;
	private String token;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "JwtTokenResponse [userName=" + userName + ", token=" + token + "]";
	}
	
	
	

}
