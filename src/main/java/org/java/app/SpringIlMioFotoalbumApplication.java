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
			
		Category category1 = new Category("Natura");
		Category category2 = new Category("Astratte");
		Category category3 = new Category("Animali");
		Category category4 = new Category("Astronomiche");
		
		List<Photo> photos = Arrays.asList(
				
					new Photo("foto1", "descrizione1", "http://www.pixolo.it/wp-content/uploads/2012/08/mountainlakeandsunset2560x1440wallpaper5576.jpg", true),
					new Photo("foto2", "descrizione2", "https://sfondo.info/i/original/d/1/3/31058.jpg", true),
					new Photo("foto3", "descrizione3", "https://sfondo.info/i/original/9/e/5/14459.jpg", true),
					new Photo("foto4", "descrizione4", "https://sfondo.info/i/original/1/b/3/83540.jpg", true),
					new Photo("foto5", "descrizione5", "https://www.pomodorozen.com/wp-content/uploads/2016/06/Sfondi-desktop-natura-79.jpg", true),
					new Photo("foto6", "descrizione6", "http://www.pixolo.it/wp-content/uploads/2012/08/superbnaturecorner2560x1440wallpaper6773.jpg", true),
					new Photo("foto7", "descrizione7", "https://www.pixelstalk.net/wp-content/uploads/2016/03/Animal-HD-wallpaper-background-1920x1080.jpg", true),
					new Photo("foto8", "descrizione8", "https://www.pixelstalk.net/wp-content/uploads/2016/03/Wild-Animals-Wallpaper-HD.jpg", true),
					new Photo("foto9", "descrizione9", "https://wallup.net/wp-content/uploads/2016/01/202352-animals-macaws-nature-closeup-birds-parrot.jpg", true),
					new Photo("foto10", "descrizione10", "https://wallup.net/wp-content/uploads/2016/01/262485-animals-nature-fox-wildlife-snow.jpg", true),
					new Photo("foto11", "descrizione11", "http://www.sfondifree.it/sfondihd/wp-content/uploads/2013/01/Sfondi-3D-per-desktop-immagini-astratte.jpg", true),
					new Photo("foto12", "descrizione12", "https://sfondo.info/i/original/9/f/3/67231.jpg", true),
					new Photo("foto13", "descrizione13", "https://sfondo.info/i/original/4/4/b/87728.jpg", true),
					new Photo("foto14", "descrizione14", "https://sfondo.info/i/original/b/4/4/5808.jpg", true),
					new Photo("foto15", "descrizione15", "https://www.wallpaperup.com/uploads/wallpapers/2015/04/24/670127/5df39eb0052b6aaf6b5934523d3c4a04.jpg", true),
					new Photo("foto16", "descrizione16", "https://www.passioneastronomia.it/wp-content/uploads/2020/08/helix_24X720_8_agosto_big_dec-scaled.jpg", true),
					new Photo("foto17", "descrizione17", "https://www.wallpaperup.com/uploads/wallpapers/2015/04/24/670141/ff0f8b15383aeca6a94c3f503b30e51b.jpg", true)
					
		);
		
		categoryServ.save(category1);
		categoryServ.save(category2);
		categoryServ.save(category3);
		categoryServ.save(category4);
				
		for (Photo photo : photos) 
			photoServ.save(photo);
	
	}
}
