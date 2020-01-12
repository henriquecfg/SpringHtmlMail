package com.henrique;

import com.henrique.models.Person;
import com.henrique.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWithEmailServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithEmailServiceApplication.class, args);
	}

	@Autowired
	private PersonService personService;
	@Override
	public void run(String... args) throws Exception {

		Person person = new Person();
		person.setName("Henrique");
		person.setEmail("henriquefa.eng@gmail.com");
		personService.save(person);


		Person person2 = new Person();
		person2.setEmail("pedro@gmail.com");
		person2.setName("Pedro");
		personService.save(person2);

		Person person3 = new Person();
		person3.setEmail("joao@gmail.com");
		person3.setName("Joao");
		personService.save(person3);
	}

}
