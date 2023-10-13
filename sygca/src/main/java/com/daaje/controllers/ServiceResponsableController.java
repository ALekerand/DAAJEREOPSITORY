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
import com.daaje.model.Drena;
import com.daaje.model.ServiceResponsable;
import com.daaje.model.Iep;
import com.daaje.model.Responsable;
import com.daaje.service.Iservice;

@Component
public class ServiceResponsableController {
	@Autowired
	public Iservice iservice;
	public int idResponsable;
	public int idDrena;
	public int idIep;
	public ServiceResponsable serviceResponsable = new ServiceResponsable();
	public ServiceResponsable selectedObject = new ServiceResponsable();
	public List listObject = new ArrayList();
	public List<Responsable> listResponsable = new ArrayList<Responsable>();
	public List<Drena> listDrena = new ArrayList<Drena>();
	public List<Iep> listIep = new ArrayList<Iep>();
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		genererCode();
	}
	
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("ServiceResponsable").size();
		if(nbEnregistrement < 10)
			prefix = "SR00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "SR0" ;
		if (nbEnregistrement > 100) 
			prefix = "SR" ;
		this.serviceResponsable.setCodeService(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		serviceResponsable.setResponsable((Responsable) iservice.getObjectById(idResponsable, "Responsable"));
		serviceResponsable.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
		serviceResponsable.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
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
		genererCode();
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


	public int getIdResponsable() {
		return idResponsable;
	}


	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}


	public int getIdDrena() {
		return idDrena;
	}


	public void setIdDrena(int idDrena) {
		this.idDrena = idDrena;
	}


	public int getIdIep() {
		return idIep;
	}


	public void setIdIep(int idIep) {
		this.idIep = idIep;
	}


	public List<Responsable> getListResponsable() {
		return listResponsable = iservice.getObjects("Responsable");
	}


	public void setListResponsable(List<Responsable> listResponsable) {
		this.listResponsable = listResponsable;
	}


	public List<Drena> getListDrena() {
		return listDrena = iservice.getObjects("Drena");
	}


	public void setListDrena(List<Drena> listDrena) {
		this.listDrena = listDrena;
	}


	public List<Iep> getListIep() {
		return listIep = iservice.getObjects("Iep");
	}


	public void setListIep(List<Iep> listIep) {
		this.listIep = listIep;
	}
}
