package mx.com.intellego.nps.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import mx.com.intellego.nps.model.Actor;

/** 
 * DAO
 * @author aramirezg
 *
 */
public interface ActorDAO extends CrudRepository<Actor, Long> {

}
