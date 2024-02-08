package com.daaje.requetes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Ecole;
import com.daaje.model.Inscription;

@Transactional
@Component
public class RequeteInscription {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Inscription> recupInscriptionParCentreParCampagne(int idCentre, int idCampagne){
		
	String query = "SELECT `inscription`.* FROM `inscription` WHERE `centre`.`ID_CENTRE` = '"+idCentre+"' AND `campagne`.`ID_CAMPAGNE` = '"+idCampagne+"'";
	List listeInscription = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Inscription.class).list();
	return listeInscription;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
