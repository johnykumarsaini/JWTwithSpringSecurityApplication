package in.jk.springboot.configuaration;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import in.jk.springboot.entity.UserEntity;
import in.jk.springboot.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	private UserDetails userDetails;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		List<UserEntity> userEntityList = userRepository.findUserByUserName(userName);
		if (!userEntityList.isEmpty()) {
            UserEntity userEntity = userEntityList.get(0);
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userEntity.getRole());
			userDetails = new User(userEntity.getUserName(), userEntity.getPassword(),
					          Arrays.asList(grantedAuthority));
			System.out.println("grantedAuthorizeUser :: " + userDetails);
		} else {
			System.out.println("User Not Present ");
		}

		return userDetails;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//		System.out.println(userName);
//		String role =null;
//		String password=null;
//		if ("john".equals(userName)) {
//
//			 role = "ADMIN";
//			 password = "$2a$10$MGkYyCANxT.Hid01Qn/u/.QqEpjRFkBaZI/lv3HQjoTSYuItUfiiu";
//
//		}
//
//		if ("jk123".equals(userName)) {
//
//			 role = "USER";
//			 password = "$2a$10$MGkYyCANxT.Hid01Qn/u/.QqEpjRFkBaZI/lv3HQjoTSYuItUfiiu";
//
//		}
//
//		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
//		userDetails = new org.springframework.security.core.userdetails.User(userName, password,
//				Arrays.asList(grantedAuthority));
//		System.out.println("grantedAuthorizeUser :: " + userDetails);
//
//		return userDetails;
//	}



}
