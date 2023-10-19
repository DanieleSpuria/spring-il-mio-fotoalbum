package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.api.email.db.pojo.Email;
import org.java.app.api.email.db.serv.EmailServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailServ emailServ;
	
	@GetMapping
	public String index(
				Model model
			) {
		
		List<Email> emails = emailServ.findAll();
		model.addAttribute("emails", emails);
		
		return "email/index";
	}
}
