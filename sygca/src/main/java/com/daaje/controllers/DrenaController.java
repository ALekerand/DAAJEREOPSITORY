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
import com.daaje.service.Iservice;

@Component
public class DrenaController {
	@Autowired
	public Iservice iservice;
	public Drena drena = new Drena();
	public Drena selectedObject = new Drena();
	public List listObject = new ArrayList();
	
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
		int nbEnregistrement = this.iservice.getObjects("Drena").size();
		if(nbEnregistrement < 10)
			prefix = "DR00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "DR0" ;
		if (nbEnregistrement > 100) 
			prefix = "DR" ;
		this.drena.setCodeDrena(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		iservice.addObject(this.drena);
		annuler();
		info("Enregistrement effectué");
	}
		
	public void modifier () {
		iservice.updateObject(drena);
		annuler();
		info("Modification effectuée");
	}
		
	public void annuler() {
		drena.setCodeDrena(null);
		drena.setNomDrena(null);
		drena.setMailDrena(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
	}
		
	public void selectionnerLigne() {
		drena = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);	
	}
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
			
//Getters and setters
	public Drena getDrena() {
		return drena;
	}

	public void setDrena(Drena drena) {
		this.drena = drena;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("Drena");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Drena getSelectedObject() {
			return selectedObject;
		}

		public void setSelectedObject(Drena selectedObject) {
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

}
