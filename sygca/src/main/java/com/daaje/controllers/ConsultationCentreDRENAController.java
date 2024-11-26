package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Centre;
import com.daaje.model.Responsable;
import com.daaje.model.ServiceResponsable;
import com.daaje.model.UserAuthentication;
import com.daaje.requetes.RequeteCentre;
import com.daaje.requetes.RequeteSeviceResponsable;
import com.daaje.requetes.RequeteUtilisateur;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ConsultationCentreDRENAController {
	@Autowired
	private Iservice iservice;
	@Autowired
	private RequeteCentre requeteCentre;
	@Autowired
	private RequeteUtilisateur requeteUtilisateur;
	@Autowired 
	private RequeteSeviceResponsable requeteSeviceResponsable;

	private List<Centre> listeCentreValideDrena = new ArrayList<Centre>();
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
	}
	
	
	
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage, null));
	}

	public void error() {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
	}
	
	
	//Getter et Setters
	
	public List<Centre> getListeCentreValideDrena() {
		try {
			listeCentreValideDrena = requeteCentre.recupCentreValideParDRENA(serviceResponsable.getDrena().getIdDrena());
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			info("Ce compte n'est pas rattacher à une DRENA. Veuillez contacter l'administrateur");
		}
		return listeCentreValideDrena;
	}


	public void setListeCentreValideDrena(List<Centre> listeCentreValideDrena) {
		this.listeCentreValideDrena = listeCentreValideDrena;
	}


	public List<Centre> getListCentreAttenteDrena() {
		System.out.println("======= le code drena: "+serviceResponsable.getDrena().getIdDrena());
		try {
			listCentreAttenteDrena = requeteCentre.recupCentreValideIEPParDRENA(serviceResponsable.getDrena().getIdDrena());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			info("Ce compte n'est pas rattacher à une DRENA. Veuillez contacter l'administrateur");
		}
		return listCentreAttenteDrena;
	}


	public void setListCentreAttenteDrena(List<Centre> listCentreAttenteDrena) {
		this.listCentreAttenteDrena = listCentreAttenteDrena;
	}


	public List<Centre> getListCentreAttenteIep() {
		try {
			listCentreAttenteIep = requeteCentre.recupCentreNonValideParIEP_et_DRENA(serviceResponsable.getDrena().getIdDrena());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			info("Ce compte n'est pas rattaché à une DRENA. Veuillez contacter l'administrateur");
		}
		return listCentreAttenteIep;
	}


	public void setListCentreAttenteIep(List<Centre> listCentreAttenteIep) {
		this.listCentreAttenteIep = listCentreAttenteIep;
	}

}
