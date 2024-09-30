package com.daaje.requetes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Ecole;

@Transactional
@Component
public class RequeteIEP {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List recupEcoleParIEP(int idIEP){
	String query =	"SELECT `ecole`.* FROM `ecole` WHERE `ecole`.`ID_IEP` = '"+idIEP+"'";
	List listeEcole = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Ecole.class).list();
	return listeEcole;
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
