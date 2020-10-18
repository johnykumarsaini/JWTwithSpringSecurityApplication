package in.jk.springboot.request;

public class UserRequest {
	
	private int userId;
	private String  name;
	private String userName;
	private String password;
	private String role;
	
	//Getters and Setters
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserRequest [userId=" + userId + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	 

}
