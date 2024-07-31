package com.daaje.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Centre;
import com.daaje.model.Departement;
import com.daaje.model.Genre;
import com.daaje.model.Responsable;
import com.daaje.model.ServiceResponsable;
import com.daaje.model.UserAuthentication;
import com.daaje.requetes.RequeteCentre;
import com.daaje.requetes.RequeteSeviceResponsable;
import com.daaje.requetes.RequeteUtilisateur;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ConsultationCentreIEPController {
	@Autowired
	private Iservice iservice;
	@Autowired
	private RequeteCentre requeteCentre;
	@Autowired
	private RequeteUtilisateur requeteUtilisateur;
	@Autowired 
	private RequeteSeviceResponsable requeteSeviceResponsable;

	private List<Centre> listeCentreValide = new ArrayList<Centre>();
	private List<Centre> listCentreAttenteDrena = new ArrayList<Centre>();
	private List<Centre> listCentreAttenteIep = new ArrayList<Centre>();
	private Responsable responsable = new Responsable();
	private ServiceResponsable serviceResponsable = new ServiceResponsable();
	
	
	//Recuperation du responsable
	@PostConstruct
	public void recuperationResponsable() {
		UserAuthentication userAuthentication = requeteUtilisateur.recuperUser();
		responsable = userAuthentication.getResponsable();
		//Recuperation du service responsable
		serviceResponsable = requeteSeviceResponsable.recupServiceRespoParRespo(responsable.getIdResponsable());
		System.out.println("CODE IEP ===="+serviceResponsable.getIep().getIdIep() );
	}
	
	
	//Getter et Setters
	public List<Centre> getListeCentreValide() {
		return listeCentreValide = requeteCentre.recupCentresvalidesParIEP(serviceResponsable.getIep().getIdIep());
	}

	public void setListeCentreValide(List<Centre> listeCentreValide) {
		this.listeCentreValide = listeCentreValide;
	}

	public List<Centre> getListCentreAttenteDrena() {
		
		return listCentreAttenteDrena = requeteCentre.recupCentreNonValideDRENAParIEP(serviceResponsable.getIep().getIdIep());
	}

	public void setListCentreAttenteDrena(List<Centre> listCentreAttenteDrena) {
		this.listCentreAttenteDrena = listCentreAttenteDrena;
	}

	public List<Centre> getListCentreAttenteIep() {
		return listCentreAttenteIep = requeteCentre.recupCentreNonValideIEPParIEP(serviceResponsable.getIep().getIdIep());
	}

	
	public void setListCentreAttenteIep(List<Centre> listCentreAttenteIep) {
		this.listCentreAttenteIep = listCentreAttenteIep;
	}
	
}
