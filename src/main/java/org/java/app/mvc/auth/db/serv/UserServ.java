package org.java.app.mvc.auth.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.app.mvc.auth.db.pojo.User;
import org.java.app.mvc.auth.db.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class UserServ implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userRepo.findById(id);
	}
	
	public void save(User user) {
		userRepo.save(user);
	}
	
	public void delete(User user) {
		userRepo.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}
}
