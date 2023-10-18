package org.java.app;

import java.util.Arrays;
import java.util.List;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoServ photoServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			
		Category natura       = new Category("Natura");
		Category astratte     = new Category("Astratte");
		Category animali      = new Category("Animali");
		Category astronomiche = new Category("Astronomiche");
		
		categoryServ.save(natura);
		categoryServ.save(astratte);
		categoryServ.save(animali);
		categoryServ.save(astronomiche);
		
		List<Photo> photos = Arrays.asList(
				
					new Photo("foto1", "descrizione1", "http://www.pixolo.it/wp-content/uploads/2012/08/mountainlakeandsunset2560x1440wallpaper5576.jpg", true, natura),
					new Photo("foto2", "descrizione2", "https://sfondo.info/i/original/d/1/3/31058.jpg", true, natura),
					new Photo("foto3", "descrizione3", "https://sfondo.info/i/original/9/e/5/14459.jpg", true, natura),
					new Photo("foto4", "descrizione4", "https://sfondo.info/i/original/1/b/3/83540.jpg", true, natura),
					new Photo("foto5", "descrizione5", "https://www.pomodorozen.com/wp-content/uploads/2016/06/Sfondi-desktop-natura-79.jpg", true, natura),
					new Photo("foto6", "descrizione6", "http://www.pixolo.it/wp-content/uploads/2012/08/superbnaturecorner2560x1440wallpaper6773.jpg", true, natura),
					new Photo("foto7", "descrizione7", "https://www.pixelstalk.net/wp-content/uploads/2016/03/Animal-HD-wallpaper-background-1920x1080.jpg", true, animali, natura),
					new Photo("foto8", "descrizione8", "https://www.pixelstalk.net/wp-content/uploads/2016/03/Wild-Animals-Wallpaper-HD.jpg", true, animali, natura),
					new Photo("foto9", "descrizione9", "https://wallup.net/wp-content/uploads/2016/01/202352-animals-macaws-nature-closeup-birds-parrot.jpg", true, animali, natura),
					new Photo("foto10", "descrizione10", "https://wallup.net/wp-content/uploads/2016/01/262485-animals-nature-fox-wildlife-snow.jpg", true, animali, natura),
					new Photo("foto11", "descrizione11", "http://www.sfondifree.it/sfondihd/wp-content/uploads/2013/01/Sfondi-3D-per-desktop-immagini-astratte.jpg", true, astratte),
					new Photo("foto12", "descrizione12", "https://sfondo.info/i/original/9/f/3/67231.jpg", true, astratte),
					new Photo("foto13", "descrizione13", "https://sfondo.info/i/original/4/4/b/87728.jpg", true, astratte),
					new Photo("foto14", "descrizione14", "https://sfondo.info/i/original/b/4/4/5808.jpg", true, astratte),
					new Photo("foto15", "descrizione15", "https://www.wallpaperup.com/uploads/wallpapers/2015/04/24/670127/5df39eb0052b6aaf6b5934523d3c4a04.jpg", true, astronomiche, natura),
					new Photo("foto16", "descrizione16", "https://www.passioneastronomia.it/wp-content/uploads/2020/08/helix_24X720_8_agosto_big_dec-scaled.jpg", true, astronomiche, natura),
					new Photo("foto17", "descrizione17", "https://www.wallpaperup.com/uploads/wallpapers/2015/04/24/670141/ff0f8b15383aeca6a94c3f503b30e51b.jpg", true, astronomiche, natura)
					
		);
				
		for (Photo photo : photos) 
			photoServ.save(photo);
	
	}
}
