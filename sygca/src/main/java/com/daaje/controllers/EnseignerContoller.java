package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Animateur;
import com.daaje.model.Campagne;
import com.daaje.model.Centre;
import com.daaje.model.NiveauFormation;
import com.daaje.model.Enseigner;
import com.daaje.service.Iservice;

@Component
public class EnseignerContoller {
	@Autowired
	public Iservice iservice;
	public int idAnimateur;
	public int idCampagne;
	public int idCentre;
	public int idNiveauFormation;	
	public Enseigner enseigner = new Enseigner();
	public Enseigner selectedObject = new Enseigner();
	public List listObject = new ArrayList();
	public List<Animateur> listAnimateur = new ArrayList<Animateur>();
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
		enseigner.setAnimateur((Animateur) iservice.getObjectById(idAnimateur, "Animateur"));
		enseigner.setCampagne((Campagne) (iservice.getObjectById(idCampagne, "Campagne")));
		enseigner.setCentre((Centre) iservice.getObjectById(idCentre, "Centre"));
		enseigner.setNiveauFormation((NiveauFormation) iservice.getObjectById(idNiveauFormation, "NiveauFormation"));
		iservice.addObject(this.enseigner);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(enseigner);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		enseigner.setCodeEnseigner(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		enseigner = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Enseigner");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Enseigner getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Enseigner selectedObject) {
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


	public Enseigner getEnseigner() {
		return enseigner;
	}


	public void setEnseigner(Enseigner enseigner) {
		this.enseigner = enseigner;
	}

	
	public int getIdAnimateur() {
		return idAnimateur;
	}


	public void setIdAnimateur(int idAnimateur) {
		this.idAnimateur = idAnimateur;
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


	public List<Animateur> getListAnimateur() {
		return listAnimateur = iservice.getObjects("Animateur");
	}


	public void setListAnimateur(List<Animateur> listAnimateur) {
		this.listAnimateur = listAnimateur;
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
