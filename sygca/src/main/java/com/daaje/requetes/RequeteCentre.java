package com.daaje.requetes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Centre;
import com.daaje.model.Ecole;

@Transactional
@Component
public class RequeteCentre {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List recupCentreNonValideIEP(){
	String query = "SELECT `centre`.* FROM `centre` WHERE `centre`.`ETAT_VALIDATION_IEP` IS NULL";
	List listeCentre = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Centre.class).list();
	return listeCentre;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
