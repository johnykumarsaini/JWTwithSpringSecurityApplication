package in.jk.springboot.configuaration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.jk.springboot.jwt.JwtAuthenicationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpingAPISecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenicationFilter jwtAuthenicationFilter;

	protected void configure(HttpSecurity httpSecurity) throws Exception {


		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/jwt/authenticate/**")
				.hasAnyAuthority("USER","ADMIN").and().authorizeRequests().antMatchers("/productapi/**")
				.hasAnyAuthority("ADMIN").and().httpBasic();

		httpSecurity.addFilterBefore(jwtAuthenicationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();

	}

}
