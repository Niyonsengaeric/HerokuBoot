package com.example.herokuboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class HerokubootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerokubootApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(GreetingRepository greetingRepository){
		return args -> {
			greetingRepository.save(new Greeting("Hello"));
			greetingRepository.save(new Greeting("Bonjour"));
			greetingRepository.save(new Greeting("Mwaramutse \uD83E\uDD26\uD83C\uDFFE\u200D♂️\uD83D\uDC4B\uD83C\uDFFE"));


		};
	}

}


@RestController
class HelloController{

	private final GreetingRepository greetingRepository;
	@GetMapping("/")
	String hello(){
		return "Heroku Spring Boot APP";
	}

	@GetMapping("/greetings")
	Iterable<Greeting> greetings(){
		return greetingRepository.findAll();
	}
	HelloController(GreetingRepository greetingRepository){
		this.greetingRepository = greetingRepository;
	}
}

@Entity(name = "users")
class Greeting{

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Greeting(String message) {
		this.message = message;
	}

	public Greeting() {
	}
}

interface GreetingRepository extends CrudRepository<Greeting, Long> {
}
