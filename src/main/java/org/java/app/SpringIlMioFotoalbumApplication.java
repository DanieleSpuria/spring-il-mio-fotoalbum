package org.java.app;

import java.util.Arrays;
import java.util.List;

import org.java.app.api.email.db.pojo.Email;
import org.java.app.api.email.db.serv.EmailServ;
import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.PhotoServ;
import org.java.app.mvc.auth.db.pojo.Role;
import org.java.app.mvc.auth.db.pojo.User;
import org.java.app.mvc.auth.db.serv.RoleServ;
import org.java.app.mvc.auth.db.serv.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoServ photoServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired 
	private RoleServ roleServ;
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private EmailServ emailServ;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role admin = new Role("ADMIN");
		roleServ.save(admin);
		
		final String passUser1 = new BCryptPasswordEncoder().encode("12345678");
		final String passUser2 = new BCryptPasswordEncoder().encode("12345678");
		final String passUser3 = new BCryptPasswordEncoder().encode("12345678");
		final String passUser4 = new BCryptPasswordEncoder().encode("12345678");
		
		User user1 = new User("user1", passUser1, admin);
		User user2 = new User("user2", passUser2, admin);
		User user3 = new User("user3", passUser3, admin);
		User user4 = new User("user4", passUser4, admin);
		userServ.save(user1);
		userServ.save(user2);
		userServ.save(user3);
		userServ.save(user4);
		
		
		
			
		Category natura       = new Category("Natura");
		Category astratte     = new Category("Astratte");
		Category animali      = new Category("Animali");
		Category astronomiche = new Category("Astronomiche");
		
		categoryServ.save(natura);
		categoryServ.save(astratte);
		categoryServ.save(animali);
		categoryServ.save(astronomiche);
		
		List<Photo> photos = Arrays.asList(
				
					new Photo("foto1", "descrizione1", "http://www.pixolo.it/wp-content/uploads/2012/08/mountainlakeandsunset2560x1440wallpaper5576.jpg", false, user1, natura),
					new Photo("foto2", "descrizione2", "https://sfondo.info/i/original/d/1/3/31058.jpg", true, user1, natura),
					new Photo("foto3", "descrizione3", "https://sfondo.info/i/original/9/e/5/14459.jpg", false, user2, natura),
					new Photo("foto4", "descrizione4", "https://sfondo.info/i/original/1/b/3/83540.jpg", false, user3, natura),
					new Photo("foto5", "descrizione5", "https://www.pomodorozen.com/wp-content/uploads/2016/06/Sfondi-desktop-natura-79.jpg", true, user4, natura),
					new Photo("foto6", "descrizione6", "http://www.pixolo.it/wp-content/uploads/2012/08/superbnaturecorner2560x1440wallpaper6773.jpg", true, user4, natura),
					new Photo("foto7", "descrizione7", "https://www.pixelstalk.net/wp-content/uploads/2016/03/Animal-HD-wallpaper-background-1920x1080.jpg", true, user1, animali,  natura),
					new Photo("foto8", "descrizione8", "https://www.pixelstalk.net/wp-content/uploads/2016/03/Wild-Animals-Wallpaper-HD.jpg", true, user2, animali, natura),
					new Photo("foto9", "descrizione9", "https://wallup.net/wp-content/uploads/2016/01/202352-animals-macaws-nature-closeup-birds-parrot.jpg", false, user2, animali, natura),
					new Photo("foto10", "descrizione10", "https://wallup.net/wp-content/uploads/2016/01/262485-animals-nature-fox-wildlife-snow.jpg", true, user4, animali, natura),
					new Photo("foto11", "descrizione11", "http://www.sfondifree.it/sfondihd/wp-content/uploads/2013/01/Sfondi-3D-per-desktop-immagini-astratte.jpg", false, user2, astratte),
					new Photo("foto12", "descrizione12", "https://sfondo.info/i/original/9/f/3/67231.jpg", true, user3, astratte),
					new Photo("foto13", "descrizione13", "https://sfondo.info/i/original/4/4/b/87728.jpg", false, user1, astratte),
					new Photo("foto14", "descrizione14", "https://sfondo.info/i/original/b/4/4/5808.jpg", true, user3, astratte),
					new Photo("foto15", "descrizione15", "https://www.wallpaperup.com/uploads/wallpapers/2015/04/24/670127/5df39eb0052b6aaf6b5934523d3c4a04.jpg", true, user2, astronomiche, natura),
					new Photo("foto16", "descrizione16", "https://www.passioneastronomia.it/wp-content/uploads/2020/08/helix_24X720_8_agosto_big_dec-scaled.jpg", true, user4, astronomiche, natura),
					new Photo("foto17", "descrizione17", "https://www.wallpaperup.com/uploads/wallpapers/2015/04/24/670141/ff0f8b15383aeca6a94c3f503b30e51b.jpg", true, user1, astronomiche, natura)
					
		);
				
		for (Photo photo : photos) 
			photoServ.save(photo);
	
		
		
		
		Email email1 = new Email("filippo@filippo.fil", "ciao complimenti alla mamma");
		Email email2 = new Email("maria@maria.mar", "salve e buonasera");
		emailServ.save(email1);
		emailServ.save(email2);

	}
}
