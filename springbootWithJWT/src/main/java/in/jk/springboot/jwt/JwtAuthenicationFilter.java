
package in.jk.springboot.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import in.jk.springboot.configuaration.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenicationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailServiceImpl UserDetailService;
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String userName = null;
		String jwtToken = null;
		TokenError tokenError =null;
	    String jwtTokenHeader = request.getHeader("Authorization");
		String isAuthorizationRequired = request.getHeader("IsAuthorizationRequired");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/errorapi/error");
		StringBuffer url =request.getRequestURL();
		System.out.println("Request URL :: "+url);
		System.out.println("Token :: "+jwtTokenHeader);
		System.out.println("IsAuthorizationRequired :: "+isAuthorizationRequired);
		
	     if (jwtTokenHeader != null && jwtTokenHeader.startsWith("jwt ")) {
            jwtToken = jwtTokenHeader.replace("jwt ", "");
			System.out.println("Token :: "+jwtToken);
            try {
				userName = jwtUtils.getUsernameFromToken(jwtToken);

			}

			catch (ExpiredJwtException e) {
				System.out.println("Token Expired :: " + e);
				tokenError =new TokenError();
				tokenError.setToken(jwtToken);
				tokenError.setUserName(userName);
				tokenError.setTokenErrorType("Token Expire :: "+e.getMessage());
				request.setAttribute("tokenError", tokenError);
				dispatcher.forward(request, response);
				return;

			} catch (Exception e) {

				System.out.println("Error in Token :: " + e);
				tokenError =new TokenError();
				tokenError.setToken(jwtToken);
				tokenError.setUserName(userName);
				tokenError.setTokenErrorType("Error in Token  :: "+e.getMessage());
				request.setAttribute("tokenError", tokenError);
				dispatcher.forward(request, response);
				return;
			}

		} else {
			    if(isAuthorizationRequired==null || "Y".equals(isAuthorizationRequired) ) {
				System.out.println("Request Without Token  :: Token Required for Authorization");
				
	            }
			else {
				System.out.println("Request Without Token :: Token Not Required ");
				}
			
		}
		
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null )
		{
			UserDetails userDetails = UserDetailService.loadUserByUsername(userName);
			System.out.println("UserDetails ::  "+userDetails);
			if(jwtUtils.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=null;
				usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
		}
		
		try {
		
		filterChain.doFilter(request, response);
	    }catch(Exception e) {
	    	System.out.println(e);
	    	
	    }

	}
	
	

}

