package org.java.app.mvc.auth.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.app.mvc.auth.db.pojo.Role;
import org.java.app.mvc.auth.db.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class RoleServ {

	@Autowired
	RoleRepo roleRepo;
	
	public List<Role> findAll() {
		return roleRepo.findAll();
	}
	
	public Optional<Role> findById(int id) {
		return roleRepo.findById(id);
	}
	
	public void save(Role role) {
		roleRepo.save(role);
	}
	
	public void delete(Role role) {
		roleRepo.delete(role);
	}
} 
