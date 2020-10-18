package in.jk.springboot.serivce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.jk.springboot.entity.UserEntity;
import in.jk.springboot.repository.UserRepository;
import in.jk.springboot.request.UserRequest;
import in.jk.springboot.response.Response;
import in.jk.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Response findUserByUserId(Integer userId) {
		Response response = null;
		Object responseData = null;

		response = new Response();
		try {
			UserEntity userEntity = userRepository.getOne(userId);
			System.out.println(userEntity);
			responseData = userEntity;
			response.setResponse("200", "Request Successfull.", responseData, null);
		} catch (Exception e) {

			response.setResponse("400", "Request UnSuccessfull.", null, e.getMessage());
		}

		return response;
	}

	@Override
	public Response addUser(UserRequest userRequest) {

		Response response = null;
		Object responseData = null;

		response = new Response();
		try {
			UserEntity userEntity = new UserEntity();
			userEntity.setUserId(userRequest.getUserId());
			userEntity.setName(userRequest.getName());
			userEntity.setUserName(userRequest.getUserName());
			userEntity.setRole(userRequest.getRole());

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(userRequest.getPassword());
            userEntity.setPassword(password);

			userEntity = userRepository.save(userEntity);
			System.out.println(userEntity);
			responseData = userEntity;
			response.setResponse("200", "User Saved Successfull.", responseData, null);
		} catch (Exception e) {

			response.setResponse("400", "User Saved  Request Unsuccessfull.", null, e.getMessage());
		}

		return response;
	}

	@Override
	public Response findUserByUserName(String userName) {
		Response response = null;
		Object responseData = null;
		List<UserEntity> userList = null;

		response = new Response();
		try {

			userList = userRepository.findUserByUserName(userName);
			if (!userList.isEmpty()) {

				UserEntity userEntity = userList.get(0);
				System.out.println(userEntity);
				responseData = userEntity;

			}

			response.setResponse("200", "Request Successfull.", responseData, null);
		} catch (Exception e) {

			response.setResponse("400", "Request UnSuccessfull.", null, e.getMessage());
		}

		return response;

	}

}
