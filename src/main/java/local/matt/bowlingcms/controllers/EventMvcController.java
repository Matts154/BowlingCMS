package local.matt.bowlingcms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventMvcController {
	
	@RequestMapping("/mvctest")
	String events (Model model){
		model.addAttribute("username", "Matt");
		return "test";
	}

}
