package in.jk.springboot.service;

import in.jk.springboot.request.UserRequest;
import in.jk.springboot.response.Response;

public interface UserService {
	
	public Response findUserByUserId(Integer userId);
	public Response addUser(UserRequest userRequest);
	public Response findUserByUserName(String userName);

}
