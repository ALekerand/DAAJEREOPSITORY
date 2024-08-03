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
import com.daaje.model.Drena;
import com.daaje.model.Genre;
import com.daaje.model.Iep;
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
	
	
	//Getter et Setters
	/*
	 * public List<Centre> getListeCentreValide() { listeCentreValide.clear(); Drena
	 * drena = serviceResponsable.getDrena();
	 * System.out.println("===== DRENA ===="+drena.getNomDrena()); List<Iep>
	 * listeIep = new ArrayList<Iep>(); List<Centre> listCentreDesIEP = new
	 * ArrayList<Centre>();
	 * 
	 * for (Iep varIep : drena.getIeps()) { //Charger les centres for ( Centre
	 * varCentres : varIep.getCentres() ) { listCentreDesIEP.add(varCentres); } }
	 * 
	 * for (Centre varCentre : listCentreDesIEP) { if
	 * ((varCentre.getEtatValidationIep()!= null)&&
	 * (varCentre.getEtatValidationDrena()!= null)){
	 * listeCentreValide.add(varCentre); } }
	 * 
	 * 
	 * return listeCentreValide; }
	 * 
	 * public void setListeCentreValide(List<Centre> listeCentreValide) {
	 * this.listeCentreValide = listeCentreValide; }
	 */

	/*
	 * public List<Centre> getListCentreAttenteDrena(){
	 * 
	 * listCentreAttenteDrena.clear(); Drena drena = serviceResponsable.getDrena();
	 * System.out.println("===== DRENA ===="+drena.getNomDrena()); List<Iep>
	 * listeIep = new ArrayList<Iep>(); List<Centre> listCentreDesIEP = new
	 * ArrayList<Centre>();
	 * 
	 * for (Iep varIep : drena.getIeps()) { //Charger les centres for ( Centre
	 * varCentres : varIep.getCentres() ) { listCentreDesIEP.add(varCentres); } }
	 * 
	 * for (Centre varCentre : listCentreDesIEP) { if
	 * ((varCentre.getEtatValidationIep()!= null)&&
	 * (varCentre.getEtatValidationDrena() == null)){
	 * listeCentreValide.add(varCentre); } }
	 * 
	 * return listCentreAttenteDrena; }
	 */

//	public void setListCentreAttenteDrena(List<Centre> listCentreAttenteDrena) {
//		this.listCentreAttenteDrena = listCentreAttenteDrena;
//	}

//	public List<Centre> getListCentreAttenteIep() {
//		
//		listCentreAttenteIep.clear();
//		Drena drena = serviceResponsable.getDrena();
//		System.out.println("===== DRENA ===="+drena.getNomDrena());
//		List<Iep> listeIep = new ArrayList<Iep>();
//		List<Centre> listCentreDesIEP = new ArrayList<Centre>();
//		
//		for (Iep varIep : drena.getIeps()) {
//			//Charger les centres
//			for ( Centre varCentres : varIep.getCentres() ) {
//				listCentreDesIEP.add(varCentres);
//			}
//		}
//		
//		for (Centre varCentre : listCentreDesIEP) {
//			if (varCentre.getEtatValidationDrena()!= null){
//				listeCentreValide.add(varCentre);
//			}
//		}
//		
//		return listCentreAttenteIep;
//	}

	/*
	 * public void setListCentreAttenteIep(List<Centre> listCentreAttenteIep) {
	 * this.listCentreAttenteIep = listCentreAttenteIep; }
	 */


	public List<Centre> getListeCentreValideDrena() {
		listeCentreValideDrena = requeteCentre.recupCentreValideParDRENA(serviceResponsable.getDrena().getIdDrena());
		return listeCentreValideDrena;
	}


	public void setListeCentreValideDrena(List<Centre> listeCentreValideDrena) {
		this.listeCentreValideDrena = listeCentreValideDrena;
	}


	public List<Centre> getListCentreAttenteDrena() {
		listCentreAttenteDrena = requeteCentre.recupCentreValideIEPParDRENA(serviceResponsable.getDrena().getIdDrena());
		return listCentreAttenteDrena;
	}


	public void setListCentreAttenteDrena(List<Centre> listCentreAttenteDrena) {
		this.listCentreAttenteDrena = listCentreAttenteDrena;
	}


	public List<Centre> getListCentreAttenteIep() {
		listCentreAttenteIep = requeteCentre.recupCentreNonValideParDRENA(serviceResponsable.getDrena().getIdDrena());
		return listCentreAttenteIep;
	}


	public void setListCentreAttenteIep(List<Centre> listCentreAttenteIep) {
		this.listCentreAttenteIep = listCentreAttenteIep;
	}
	

	

}
