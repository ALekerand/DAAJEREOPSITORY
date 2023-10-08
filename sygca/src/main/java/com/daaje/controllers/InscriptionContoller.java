package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Apprenant;
import com.daaje.model.Campagne;
import com.daaje.model.Centre;
import com.daaje.model.NiveauFormation;
import com.daaje.model.Inscription;
import com.daaje.service.Iservice;

@Component
public class InscriptionContoller {
	@Autowired
	public Iservice iservice;
	public int idApprenant;
	public int idCampagne;
	public int idCentre;
	public int idNiveauFormation;	
	public Inscription inscription = new Inscription();
	public Inscription selectedObject = new Inscription();
	public List listObject = new ArrayList();
	public List<Apprenant> listApprenant = new ArrayList<Apprenant>();
	public List<Campagne> listCampagne = new ArrayList<Campagne>();
	public List<Centre> listCentre = new ArrayList<Centre>();
	public List<NiveauFormation> listNiveauFormation = new ArrayList<NiveauFormation>();
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	
	public void enregistrer(){
		inscription.setApprenant((Apprenant) iservice.getObjectById(idApprenant, "Apprenant"));
		inscription.setCampagne((Campagne) (iservice.getObjectById(idCampagne, "Campagne")));
		inscription.setCentre((Centre) iservice.getObjectById(idCentre, "Centre"));
		inscription.setNiveauFormation((NiveauFormation) iservice.getObjectById(idNiveauFormation, "NiveauFormation"));
		iservice.addObject(this.inscription);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(inscription);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		inscription.setCodeInscription(null);
		inscription.setDateInscription(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		inscription = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Inscription");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Inscription getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Inscription selectedObject) {
		this.selectedObject = selectedObject;
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


	public Inscription getInscription() {
		return inscription;
	}


	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	
	public int getIdApprenant() {
		return idApprenant;
	}


	public void setIdApprenant(int idApprenant) {
		this.idApprenant = idApprenant;
	}


	public int getIdCampagne() {
		return idCampagne;
	}


	public void setIdCampagne(int idCampagne) {
		this.idCampagne = idCampagne;
	}


	public int getIdCentre() {
		return idCentre;
	}


	public void setIdCentre(int idCentre) {
		this.idCentre = idCentre;
	}


	public int getIdNiveauFormation() {
		return idNiveauFormation;
	}


	public void setIdNiveauFormation(int idNiveauFormation) {
		this.idNiveauFormation = idNiveauFormation;
	}


	public List<Apprenant> getListApprenant() {
		return listApprenant = iservice.getObjects("Apprenant");
	}


	public void setListApprenant(List<Apprenant> listApprenant) {
		this.listApprenant = listApprenant;
	}


	public List<Campagne> getListCampagne() {
		return listCampagne = iservice.getObjects("Campagne");
	}


	public void setListCampagne(List<Campagne> listCampagne) {
		this.listCampagne = listCampagne;
	}


	public List<Centre> getListCentre() {
		return listCentre = iservice.getObjects("Centre");
	}


	public void setListCentre(List<Centre> listCentre) {
		this.listCentre = listCentre;
	}


	public List<NiveauFormation> getListNiveauFormation() {
		return listNiveauFormation = iservice.getObjects("NiveauFormation");
	}


	public void setListNiveauFormation(List<NiveauFormation> listNiveauFormation) {
		this.listNiveauFormation = listNiveauFormation;
	}

}
