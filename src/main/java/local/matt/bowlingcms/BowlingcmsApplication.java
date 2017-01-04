package local.matt.bowlingcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BowlingcmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowlingcmsApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner runner(EventRepository er) {
//		
//		return args -> {
//			Collection<Event> list = new ArrayList<>();
//			
//			list.add(new Event(new Date(), "Granada VS Cloverleaf"));
//			list.add(new Event(new Date(), "Alibi 2016"));
//			
//			er.save(list);
//		};
//	}
}
