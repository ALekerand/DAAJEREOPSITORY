package com.daaje.requetes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Campagne;
import com.daaje.model.Enseigner;
import com.daaje.model.Iep;

@Transactional
@Component
public class RequeteEnseigner {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List recupEnseignerParId(int idCampagne){
	String query = "SELECT `enseigner`.* FROM `enseigner` WHERE `enseigner`.`ID_CAMPAGNE` = '"+idCampagne+"'";
	List listEnseigner = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Enseigner.class).list();
	return listEnseigner;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
