package in.jk.springboot.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.jk.springboot.configuaration.UserDetailServiceImpl;
import in.jk.springboot.response.Response;

@RestController
@RequestMapping("/jwt")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserDetailServiceImpl userDetailService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public Response getAuthenticationToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
		
		System.out.println("Authenicate Controller ...");

		Response response = null;
		JwtTokenResponse jwtTokenResponse = null;

		response = new Response();
		jwtTokenResponse = new JwtTokenResponse();
		try {

			if (authenticate(jwtTokenRequest.getUserName(), jwtTokenRequest.getPassword())) {

				UserDetails userDetails = userDetailService.loadUserByUsername(jwtTokenRequest.getUserName());

				final String token = jwtUtils.generateToken(userDetails);
				System.out.println("Generated Token ::"+token);

				jwtTokenResponse = new JwtTokenResponse();
				jwtTokenResponse.setUserName(jwtTokenRequest.getUserName());
				jwtTokenResponse.setToken(token);

				response.setResponse("200", "Authentication Token", jwtTokenResponse, "");

			}

		} catch (Exception e) {

			jwtTokenResponse = new JwtTokenResponse();
			jwtTokenResponse.setUserName(jwtTokenRequest.getUserName());
			jwtTokenResponse.setToken("Error in Token Generation ");

			response.setResponse("500", "Error in Token Generation", jwtTokenResponse, e.getMessage());
		}

		return response;

	}

	private boolean authenticate(String userName, String password) throws Exception {

		boolean status = Boolean.FALSE;

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,
				password);

		try {
			Authentication auth = authenticationManager.authenticate(authentication);
			status = auth.isAuthenticated();
			
			System.out.println("Authentication Status :: "+status);

		}

		catch (DisabledException e) {

			throw new Exception("User Disabled ", e);

		}

		catch (BadCredentialsException e) {
			throw new Exception("User Disabled ", e);

		} catch (Exception e) {
			throw new Exception(e);

		}
		return status;

	}

}
