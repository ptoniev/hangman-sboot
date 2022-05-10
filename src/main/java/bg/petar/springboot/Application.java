package bg.petar.springboot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableAutoConfiguration()
//@ComponentScan(basePackages = "bg.petar.springboot")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner (GamesWonRepository gamesWonRepository, RankingRepository rankingRepository) {
//		return args -> {
//			GamesWon completedGames = new GamesWon(2);
//			GamesWon completedGames2 = new GamesWon(3);
//			GamesWon completedGames3 = new GamesWon(4);
//
//			Ranking myRankings = new Ranking("Maria",
//					new ArrayList<>(List.of(completedGames, completedGames2, completedGames3)));
//			rankingRepository.save(myRankings);
//		};
//	}
}
