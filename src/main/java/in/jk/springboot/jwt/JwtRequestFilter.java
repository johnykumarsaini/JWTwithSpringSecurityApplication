package in.jk.springboot.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import in.jk.springboot.configuaration.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;


public class JwtRequestFilter extends OncePerRequestFilter {

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
		String requestUrl = url.toString();
		
	    System.out.println("Request URL :: "+url);
		System.out.println("Token :: "+jwtTokenHeader);
		System.out.println("IsAuthorizationRequired :: "+isAuthorizationRequired);
		
	     if (jwtTokenHeader != null && jwtTokenHeader.startsWith("jk ")) {
            jwtToken = jwtTokenHeader.replace("jk ", "");
			System.out.println("Token ::::::"+jwtToken);
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

			} catch (Exception e) {

				System.out.println("Error in Token :: " + e);
				tokenError =new TokenError();
				tokenError.setToken(jwtToken);
				tokenError.setUserName(userName);
				tokenError.setTokenErrorType("Error in Token  :: "+e.getMessage());
				request.setAttribute("tokenError", tokenError);
				dispatcher.forward(request, response);
				

			}

		} else {
			
	       boolean isEqulas = JwtRequestFilter.isSwaggerBaseURL(requestUrl);
			
			if(!isEqulas) {
				
			if(isAuthorizationRequired==null || "Y".equals(isAuthorizationRequired) ) {
				System.out.println("Request Without Token  :: Token Required ");
				tokenError =new TokenError();
				tokenError.setToken(jwtToken);
				tokenError.setUserName(userName);
				tokenError.setTokenErrorType("Request Without Token Send Token to Authorization of Request");
				request.setAttribute("tokenError", tokenError);
				dispatcher.forward(request, response);
				
			   }
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
	
	private static boolean isSwaggerBaseURL(String url) {

		
		boolean status = Boolean.FALSE;
        int length = url.length();
        
        System.out.println("URL Length :: "+length);
      
			StringTokenizer stringTokenizer = new StringTokenizer(url, "/");
			while (stringTokenizer.hasMoreTokens()) {
				String token = (String) stringTokenizer.nextToken();
				System.out.println(token);
				String [] swaggerUrlKeyWords = {"swagger-ui.html","swagger-resources","v2","webjars"};
				List<String> swaggerList = Arrays.asList(swaggerUrlKeyWords);
				boolean isEqulas = swaggerList.contains(token);
				if (isEqulas) {
                    System.out.println(isEqulas);
					status = Boolean.TRUE;
					break;

				}

			}
		

		return status;

	}

}
