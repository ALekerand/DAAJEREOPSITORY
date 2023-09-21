package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Drena;
import com.daaje.model.Iep;
import com.daaje.service.Iservice;

@Component
public class IepController {
	@Autowired
	public Iservice iservice;
	public int idDrena;
	public Iep iep = new Iep();
	public Iep selectedObject = new Iep();
	public List listObject = new ArrayList();
	public List<Drena> listDrena = new ArrayList<Drena>();
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	
	public void enregistrer(){
		iep.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
		iservice.addObject(this.iep);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(iep);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		iep.setCodeIep(null);
		iep.setNomIep(null);
		iep.setMailIep(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		iep = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Iep");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Iep getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Iep selectedObject) {
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


	public Iep getIep() {
		return iep;
	}


	public void setIep(Iep iep) {
		this.iep = iep;
	}


	public List<Drena> getListDrena() {
		return listDrena = iservice.getObjects("Drena");
		
	}


	public void setListDrena(List<Drena> listDrena) {
		this.listDrena = listDrena;
	}


	public int getIdDrena() {
		return idDrena;
	}


	public void setIdDrena(int idDrena) {
		this.idDrena = idDrena;
	}

}
