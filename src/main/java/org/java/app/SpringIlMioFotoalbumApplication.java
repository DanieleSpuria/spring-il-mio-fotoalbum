package org.java.app;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoServ photoServ;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Photo photo1 = new Photo("foto1", "descrizione1", "http://www.pixolo.it/wp-content/uploads/2012/08/mountainlakeandsunset2560x1440wallpaper5576.jpg", true);
		Photo photo2 = new Photo("foto2", "descrizione2", "https://sfondo.info/i/original/d/1/3/31058.jpg", true);
		Photo photo3 = new Photo("foto3", "descrizione3", "https://sfondo.info/i/original/9/e/5/14459.jpg", true);
		Photo photo4 = new Photo("foto4", "descrizione4", "https://sfondo.info/i/original/1/b/3/83540.jpg", true);
		Photo photo5 = new Photo("foto5", "descrizione5", "https://www.pomodorozen.com/wp-content/uploads/2016/06/Sfondi-desktop-natura-79.jpg", true);
		Photo photo6 = new Photo("foto6", "descrizione6", "http://www.pixolo.it/wp-content/uploads/2012/08/superbnaturecorner2560x1440wallpaper6773.jpg", true);
		
		photoServ.save(photo1);
		photoServ.save(photo2);
		photoServ.save(photo3);
		photoServ.save(photo4);
		photoServ.save(photo5);
		photoServ.save(photo6);
		
	}

}
