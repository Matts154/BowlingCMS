package local.matt.bowlingcms.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import local.matt.bowlingcms.model.Person;
import local.matt.bowlingcms.repositories.PersonRepository;

@RestController
public class PersonRestController {

	private PersonRepository personRepository;
	
//	@RequestMapping(path = "/person", method = RequestMethod.GET)
	Collection<Person> person() {
		return this.personRepository.findAll();
	}
	
}
