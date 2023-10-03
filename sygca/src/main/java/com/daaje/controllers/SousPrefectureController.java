package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Departement;
import com.daaje.model.SousPrefecture;
import com.daaje.service.Iservice;

@Component
public class SousPrefectureController {
	@Autowired
	public Iservice iservice;
	public int idDepartement;
	public SousPrefecture sousPrefecture = new SousPrefecture();
	public SousPrefecture selectedObject = new SousPrefecture();
	public List listObject = new ArrayList();
	public List<Departement> listDepartement = new ArrayList<Departement>();
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	public void enregistrer(){
		sousPrefecture.setDepartement((Departement) iservice.getObjectById(idDepartement, "Departement"));
		iservice.addObject(this.sousPrefecture);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(sousPrefecture);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		sousPrefecture.setCodeSousPrefecture(null);
		sousPrefecture.setNomSousPrefecture(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		sousPrefecture = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public SousPrefecture getSousPrefecture() {
		return sousPrefecture;
	}

	public void setSousPrefecture(SousPrefecture sousPrefecture) {
		this.sousPrefecture = sousPrefecture;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("SousPrefecture");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public SousPrefecture getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(SousPrefecture selectedObject) {
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


	public int getIdDepartement() {
		return idDepartement;
	}


	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}


	public List<Departement> getListDepartement() {
		return listDepartement = iservice.getObjects("Departement");
	}


	public void setListDepartement(List<Departement> listDepartement) {
		this.listDepartement = listDepartement;
	}

}
