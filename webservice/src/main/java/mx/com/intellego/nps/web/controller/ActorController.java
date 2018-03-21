package mx.com.intellego.nps.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.intellego.nps.model.Actor;
import mx.com.intellego.nps.service.ActorService;

@Controller
@RequestMapping(path = "/demo")
public class ActorController {
	
	@Autowired
	private ActorService actorService;
	
	@GetMapping(path="/add/{name}/{lastName}")
	public @ResponseBody String addActor(@RequestParam String name ,@RequestParam String lastName){
		
		Actor  actor = new Actor();
		actor.setLastName(name);
		actor.setName(lastName);
		actor.setLastUpdate(new Date());
		actorService.addActor(actor);
		
		return "Guardado";
		
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Actor> getAllActors() {
		// This returns a JSON or XML with the users
		return actorService.listAll();
	}

}
