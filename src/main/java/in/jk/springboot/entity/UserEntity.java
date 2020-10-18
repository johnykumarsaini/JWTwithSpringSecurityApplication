package in.jk.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jwt_user")
public class UserEntity {
	
	@Id
	@Column(name="user_id")
	private Integer userId;
	@Column(name="user_name" ,unique = true)
	private String userName;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="email_id")
	private String emailId;
	@Column(name="role")
	private String  role;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName + ", name=" + name + ", password=" + password
				+ ", emailId=" + emailId + ", role=" + role + "]";
	}
	
	

}
