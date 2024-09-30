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
import com.daaje.requetes.RequeteCentre;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ConsultationCentreController {
	@Autowired
	private Iservice iservice;
	@Autowired
	private RequeteCentre requeteCentre;

	private List<Centre> listeCentreValide = new ArrayList<Centre>();
	private List<Centre> listCentreAttenteDrena = new ArrayList<Centre>();
	private List<Centre> listCentreAttenteIep = new ArrayList<Centre>();
	
	
	//Getter et Setters
	public List<Centre> getListeCentreValide() {
		return listeCentreValide = requeteCentre.recupCentresvalides();
	}

	public void setListeCentreValide(List<Centre> listeCentreValide) {
		this.listeCentreValide = listeCentreValide;
	}

	public List<Centre> getListCentreAttenteDrena() {
		return listCentreAttenteDrena = requeteCentre.recupCentreNonValideDRENA();
	}

	public void setListCentreAttenteDrena(List<Centre> listCentreAttenteDrena) {
		this.listCentreAttenteDrena = listCentreAttenteDrena;
	}

	public List<Centre> getListCentreAttenteIep() {
		return listCentreAttenteIep = requeteCentre.recupCentreNonValideIEP();
	}

	public void setListCentreAttenteIep(List<Centre> listCentreAttenteIep) {
		this.listCentreAttenteIep = listCentreAttenteIep;
	}
	

	

}
