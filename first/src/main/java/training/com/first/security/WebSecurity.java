package training.com.first.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import training.com.first.model.UserModel;
import training.com.first.repository.iface.RoleRepositoryInterface;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTAuthenticationEntryPoint unauthorizeHandler;
	@Autowired
	private RoleRepositoryInterface RoleRepo;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(encoder());
	}
	@Bean
	public JWTAuthorizationFilter authenticationTokenFilterBean() throws Exception {
		return new JWTAuthorizationFilter(authenticationManager());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Iterable<UserModel> roleImpl= RoleRepo.findAll();
		String c = "hasAuthority('ADMIN') || hasAuthority('STAFF')";
		for (UserModel roleIterator : roleImpl) {
			c = c+" || hasAuthority('"+roleIterator.getUsername()+"')";
		}
//		String b = "hasAuthority('ADMIN') || hasAuthority('STAFF') || hasAuthority('CHL')";
        http.cors().and().csrf().disable().
        	authorizeRequests()
		        .antMatchers("/login").permitAll()
		        .antMatchers("/auth/**").access(c)
		        .antMatchers("/api/**").permitAll()
		        .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizeHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
	@Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}