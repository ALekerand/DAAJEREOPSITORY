package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Commune;
import com.daaje.model.LocaliteDImplantation;
import com.daaje.model.SousPrefecture;
import com.daaje.service.Iservice;

@Component
public class LocaliteDImplantationController {
	@Autowired
	public Iservice iservice;
	public int idCommune;
	public int idSousPrefecture;
	public LocaliteDImplantation localiteDImplantation = new LocaliteDImplantation();
	public LocaliteDImplantation selectedObject = new LocaliteDImplantation();
	public List listObject = new ArrayList();
	public List<Commune> listCommune = new ArrayList<Commune>();
	public List<SousPrefecture> listSousPrefecture = new ArrayList<SousPrefecture>();
	
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	
	public void enregistrer(){
		localiteDImplantation.setCommune((Commune)iservice.getObjectById(idCommune, "Commune"));
		localiteDImplantation.setSousPrefecture((SousPrefecture)iservice.getObjectById(idSousPrefecture, "SousPrefecture"));
		iservice.addObject(this.localiteDImplantation);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(localiteDImplantation);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		localiteDImplantation.setCodeLocalite(null);
		localiteDImplantation.setNomLocalite(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		localiteDImplantation = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public LocaliteDImplantation getLocaliteDImplantation() {
		return localiteDImplantation;
	}

	public void setLocaliteDImplantation(LocaliteDImplantation localiteDImplantation) {
		this.localiteDImplantation = localiteDImplantation;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("LocaliteDImplantation");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public LocaliteDImplantation getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(LocaliteDImplantation selectedObject) {
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


	public int getIdCommune() {
		return idCommune;
	}


	public void setIdCommune(int idCommune) {
		this.idCommune = idCommune;
	}


	public List<Commune> getListCommune() {
		return listCommune = iservice.getObjects("Commune");
	}


	public void setListCommune(List<Commune> listCommune) {
		this.listCommune = listCommune;
	}


	public int getIdSousPrefecture() {
		return idSousPrefecture;
	}


	public void setIdSousPrefecture(int idSousPrefecture) {
		this.idSousPrefecture = idSousPrefecture;
	}


	public List<SousPrefecture> getListSousPrefecture() {
		return listSousPrefecture = iservice.getObjects("SousPrefecture");
	}


	public void setListSousPrefecture(List<SousPrefecture> listSousPrefecture) {
		this.listSousPrefecture = listSousPrefecture;
	}

}
