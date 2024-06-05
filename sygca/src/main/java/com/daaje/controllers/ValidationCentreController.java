package com.daaje.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Centre;
import com.daaje.model.UserAuthentication;
import com.daaje.requetes.RequeteCentre;
import com.daaje.requetes.RequeteUtilisateur;
import com.daaje.service.Iservice;

@Component
public class ValidationCentreController {
	@Autowired
	private Iservice service;
	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	
	@Autowired
	private RequeteCentre requeteCentre;
	
	
	private List<Centre> listeCentre = new ArrayList<Centre>();
	private List<Centre> listeCentreDRENA = new ArrayList<Centre>();
	private Centre centre = new Centre();
	private Centre selectedObject = new Centre();
	private UserAuthentication userAuthentication = new UserAuthentication();
	
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
	private boolean etatBtnEnregistrer;
	private boolean etatBtnModifier;
		


	public UserAuthentication chagerUtilisateur() {
		return userAuthentication = requeteUtilisateur.recuperUser();
	}
	
	public void modifier() {

	}
	
	public void annuler() {
		
	}
	
	public void selectionnerLigne() {
		this.centre = this.selectedObject;
	}
	
	public void validerCentreIEP() {
		chagerUtilisateur();
		selectedObject.setResponsableByResIdResponsable(userAuthentication.getResponsable());
		selectedObject.setEtatValidationIep(true);
		selectedObject.setDateValidationIep(new Date());
		service.updateObject(selectedObject);
		info("Centre validé en IEP");
		annuler();
	}
	
	public void validerCentreDRENA() {
		chagerUtilisateur();
		selectedObject.setResponsableByResIdResponsable2(userAuthentication.getResponsable());
		selectedObject.setEtatValidationDrena(true);
		selectedObject.setDateValidationDrena(new Date());
		service.updateObject(selectedObject);
		info("Centre validé en DRENA");
		annuler();
	}

	public void rejeterDemande() {
		selectedObject.setEtatValidationIep(false);
		service.updateObject(selectedObject);
		info("Centre rejetée");
        
        annuler();
	}
	
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
	
	
	public CommandButton getCmdBModifier() {
		return cmdBModifier;
	}

	public void setCmdBModifier(CommandButton cmdBModifier) {
		this.cmdBModifier = cmdBModifier;
	}

	public CommandButton getCmdBEnregistrer() {
		return cmdBEnregistrer;
	}

	public void setCmdBEnregistrer(CommandButton cmdBEnregistrer) {
		this.cmdBEnregistrer = cmdBEnregistrer;
	}

	public boolean isEtatBtnModifier() {
		return etatBtnModifier;
	}

	public void setEtatBtnModifier(boolean etatBtnModifier) {
		this.etatBtnModifier = etatBtnModifier;
	}

	public boolean isEtatBtnEnregistrer() {
		return etatBtnEnregistrer;
	}

	public void setEtatBtnEnregistrer(boolean etatBtnEnregistrer) {
		this.etatBtnEnregistrer = etatBtnEnregistrer;
	}

	public List<Centre> getListeCentre() {
		return listeCentre = requeteCentre.recupCentreNonValideIEP();
	}

	public void setListeCentre(List<Centre> listeCentre) {
		this.listeCentre = listeCentre;
	}

	public Centre getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Centre selectedObject) {
		this.selectedObject = selectedObject;
	}

	public List<Centre> getListeCentreDRENA() {
		return listeCentreDRENA = requeteCentre.recupCentreNonValideDRENA();
	}

	public void setListeCentreDRENA(List<Centre> listeCentreDRENA) {
		this.listeCentreDRENA = listeCentreDRENA;
	}

}
