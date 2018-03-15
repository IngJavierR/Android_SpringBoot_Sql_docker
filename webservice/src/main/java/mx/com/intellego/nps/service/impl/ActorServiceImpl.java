package mx.com.intellego.nps.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.intellego.nps.model.Actor;
import mx.com.intellego.nps.persistence.dao.ActorDAO;
import mx.com.intellego.nps.service.ActorService;

@Service
public class ActorServiceImpl  implements ActorService{
	
	@Autowired
	private ActorDAO actorDAO;

	@Override
	public Boolean addActor(Actor newActor) {
		 Actor ac = actorDAO.save(newActor);
		return ac!= null ? true :false;
	}

	@Override
	public Iterable<Actor> listAll() {
		
		return actorDAO.findAll();
	}

}
