package com.sati.requetes;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Campagne;



@Component
@Scope("session")
@Transactional
public class RequeteCampagne {



	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Campagne recupCampagneEncore(){
	String query = "SELECT `campagne`.* FROM `campagne` WHERE `campagne`.`ETAT_CAMPAGNE` = '=0'";
	Campagne campagne = (Campagne) sessionFactory.getCurrentSession().createSQLQuery(query).addEntity(Campagne.class).uniqueResult();
	return campagne;
	}
}
