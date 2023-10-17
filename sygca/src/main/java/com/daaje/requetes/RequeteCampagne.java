package com.daaje.requetes;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Campagne;

@Transactional
@Component
@Scope("session")
public class RequeteCampagne {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Campagne recupCampagneEncours(){
	String query = "SELECT `campagne`.* FROM `campagne` WHERE `campagne`.`ETAT_CAMPAGNE` = '=0'";
	Campagne campagne = (Campagne) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Campagne.class).uniqueResult();
	return campagne;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
