package in.jk.springboot.jwt;

public class TokenError {
	
	private String userName;
	private String token;
	private String tokenErrorType;
	
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
	public String getTokenErrorType() {
		return tokenErrorType;
	}
	public void setTokenErrorType(String tokenErrorType) {
		this.tokenErrorType = tokenErrorType;
	}
	@Override
	public String toString() {
		return "TokenError [userName=" + userName + ", token=" + token + ", tokenErrorType=" + tokenErrorType + "]";
	}
	
	
	

}
