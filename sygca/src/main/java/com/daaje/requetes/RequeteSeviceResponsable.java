package com.daaje.requetes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daaje.model.Campagne;
import com.daaje.model.Iep;
import com.daaje.model.ServiceResponsable;

@Transactional
@Component
public class RequeteSeviceResponsable {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ServiceResponsable recupServiceRespoParRespo(int id_responsable){	
	String query = "SELECT `service_responsable`.* FROM `service_responsable` WHERE `service_responsable`.`ID_RESPONSABLE` = '"+id_responsable+"' AND `service_responsable`.`DATE_DEPART` IS NULL";
	ServiceResponsable serviceResponsable = (ServiceResponsable) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ServiceResponsable.class).uniqueResult();
	return serviceResponsable;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
