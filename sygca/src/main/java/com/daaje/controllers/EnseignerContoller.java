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
import com.daaje.model.Iepp;
import com.daaje.model.Responsable;
import com.daaje.model.Enseigner;
import com.daaje.service.Iservice;

@Component
public class EnseignerContoller {
	@Autowired
	public Iservice iservice;
	public int idDrena;
	public int attributIdIepp16;
	public int idResponsable;
	public ServiceResponsable serviceResponsable = new ServiceResponsable();
	public ServiceResponsable selectedObject = new ServiceResponsable();
	public List listObject = new ArrayList();
	public List<Drena> listDrena = new ArrayList<Drena>();
	public List<Iepp> listIep = new ArrayList<Iepp>();
	public List<Responsable> listResponsable = new ArrayList<Responsable>();
	public List<Responsable> listResponsable = new ArrayList<Responsable>();
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	
	public void enregistrer(){
		serviceResponsable.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
		serviceResponsable.setIepp((Iepp) iservice.getObjectById(attributIdIepp16, "Iepp"));
		serviceResponsable.setResponsable((Responsable) iservice.getObjectById(idResponsable, "Responsable"));
		iservice.addObject(this.serviceResponsable);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(serviceResponsable);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		serviceResponsable.setCodeService(null);
		serviceResponsable.setDateArrivee(null);
		serviceResponsable.setDateDepart(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		serviceResponsable = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("ServiceResponsable");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public ServiceResponsable getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(ServiceResponsable selectedObject) {
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


	public ServiceResponsable getServiceResponsable() {
		return serviceResponsable;
	}


	public void setServiceResponsable(ServiceResponsable serviceResponsable) {
		this.serviceResponsable = serviceResponsable;
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


	public int getAttributIdIepp16() {
		return attributIdIepp16;
	}


	public void setAttributIdIepp16(int attributIdIepp16) {
		this.attributIdIepp16 = attributIdIepp16;
	}


	public int getIdResponsable() {
		return idResponsable;
	}


	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}


	public List<Iepp> getListIep() {
		return listIep;
	}


	public void setListIep(List<Iepp> listIep) {
		this.listIep = listIep;
	}


	public List<Responsable> getListResponsable() {
		return listResponsable;
	}


	public void setListResponsable(List<Responsable> listResponsable) {
		this.listResponsable = listResponsable;
	}

}
