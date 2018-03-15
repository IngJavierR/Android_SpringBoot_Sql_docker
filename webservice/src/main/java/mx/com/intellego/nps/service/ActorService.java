package mx.com.intellego.nps.service;

import java.util.List;

import mx.com.intellego.nps.model.Actor;

public interface ActorService {
	
	
	public Boolean addActor(Actor newActor);
	
	public Iterable<Actor> listAll();

}
