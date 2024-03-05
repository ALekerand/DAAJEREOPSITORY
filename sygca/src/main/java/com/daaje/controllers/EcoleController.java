package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Ecole;
import com.daaje.model.Iep;
import com.daaje.service.Iservice;

@Component
public class EcoleController {
	@Autowired
	private Iservice iservice;
	private int idIep;
	private Ecole ecole = new Ecole();
	private Ecole selectedObject = new Ecole();
	private List listObject = new ArrayList();
	private List<Iep> listIep = new ArrayList<Iep>();

//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();	
	
//Methodes
@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		//genererCode();
	}
		
	/*
	 * public void genererCode() { String prefix=""; int nbEnregistrement =
	 * this.iservice.getObjects("Ecole").size(); if(nbEnregistrement < 10) prefix =
	 * "ECOLE00" ; if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) prefix
	 * = "ECOLE0" ; if (nbEnregistrement > 100) prefix = "ECOLE" ;
	 * this.ecole.setCodeEcole(prefix+(nbEnregistrement+1)); }
	 */
		
	public void enregistrer(){
		ecole.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
		iservice.addObject(this.ecole);
		annuler();
			info("Enregistrement effectué");
	}
		
	public void modifier() {
		iservice.updateObject(ecole);
		annuler();
		info("Modification effectuée");
	}
		
	public void annuler() {
		ecole.setCodeEcole(null);
		ecole.setNomEcole(null);
		ecole.setAdresseEcole(null);
		ecole.setTelphoneEcole(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		//genererCode();
	}
		
	public void selectionnerLigne() {
		ecole = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
		}
			
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Ecole");
	}
	
	public void setListObject(List listObject) {
		this.listObject = listObject;
	}
	
	public Ecole getSelectedObject() {
		return selectedObject;
	}


	public void setSelectedObject(Ecole selectedObject) {
		this.selectedObject = selectedObject;
	}
		
	public Ecole getEcole() {
		return ecole;
	}

	public void setDepartement(Ecole ecole) {
		this.ecole = ecole;
	}

	public int getIdIep() {
		return idIep;
	}

	public void setIdIep(int idIep) {
		this.idIep = idIep;
	}

	public List<Iep> getListIep() {
		return listIep = iservice.getObjects("Iep");
		}


		public void setListIep(List<Iep> listIep) {
			this.listIep = listIep;
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
