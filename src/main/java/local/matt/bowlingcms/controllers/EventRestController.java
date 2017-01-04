package local.matt.bowlingcms.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import local.matt.bowlingcms.model.Event;
import local.matt.bowlingcms.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventRestController {

	private EventRepository eventRepository;

//	@RequestMapping(path = "/fuck", method = RequestMethod.GET)
	@ResponseBody
	Collection<Event> event() {
            return this.eventRepository.findAll();
        }
}
