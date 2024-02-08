package com.daaje.requetes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Campagne;
import com.daaje.model.Iep;

@Transactional
@Component
public class RequeteCampagne {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List recupIeparDrena(int idDrena){
	String query = "SELECT `iep`.* FROM `iep` WHERE `iep`.`ID_DRENA` = '"+idDrena+"'";
	List listeIep = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Iep.class).list();
	return listeIep;
	}
	
	public Campagne recupLastCampagne(){
	String query = "SELECT `campagne`.*FROM `campagne`WHERE `campagne`.`ETAT_CAMPAGNE` = '0'";
	Campagne campagne = (Campagne) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Campagne.class).uniqueResult();
	return campagne;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
