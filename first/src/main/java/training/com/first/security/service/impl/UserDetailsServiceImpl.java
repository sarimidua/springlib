package training.com.first.security.service.impl;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import training.com.first.model.UserModel;
import training.com.first.repository.iface.RoleRepositoryInterface;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

	private RoleRepositoryInterface repository;
	
	public UserDetailsServiceImpl(RoleRepositoryInterface repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel applicationUser = repository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        
        List<GrantedAuthority> authorityList = AuthorityUtils
        		.commaSeparatedStringToAuthorityList(applicationUser.getUsername());
        		
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorityList);
	}
	
	public UserModel findOne(String username) {
		return repository.findByUsername(username);
	}
	
	public UserModel findUser(String username) {
		return repository.findByUsername(username);
	}
}
