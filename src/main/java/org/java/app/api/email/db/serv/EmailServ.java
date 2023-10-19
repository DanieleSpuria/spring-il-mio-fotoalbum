package org.java.app.api.email.db.serv;

import java.util.List;

import org.java.app.api.email.db.pojo.Email;
import org.java.app.api.email.db.repo.EmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServ {
	
	@Autowired
	private EmailRepo emailRepo;

	public List<Email> findAll(){
		return emailRepo.findAll();
	}
	
	public void save(Email email) {
		emailRepo.save(email);
	}
	
	public void delete(Email email) {
		emailRepo.delete(email);
	}
}
