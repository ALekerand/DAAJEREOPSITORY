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
import com.daaje.service.Iservice;

@Component
public class DepartementControllers {
	@Autowired
	public Iservice iservice;
	
	//public int idDrena;
	private Departement departement = new Departement();
	private Departement selectedObject = new Departement();
	private List listObject = new ArrayList();
	private List<Drena> listDrena = new ArrayList<Drena>();

//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();	
	
//Methodes
@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		genererCode();
	}
		
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Departement").size();
		if(nbEnregistrement < 10)
			prefix = "DEP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "DEP0" ;
		if (nbEnregistrement > 100) 
			prefix = "DEP" ;
		this.departement.setCodeDepartement(prefix+(nbEnregistrement+1));
	}
		
	public void enregistrer(){
		//departement.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
		iservice.addObject(this.departement);
		annuler();
		info("Enregistrement effectué");
	}
		
	public void modifier() {
		iservice.updateObject(departement);
		annuler();
		info("Modification effectuée");
	}
		
	public void annuler() {
		departement.setCodeDepartement(null);
		departement.setNomDepartement(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
	}
		
	public void selectionnerLigne() {
		departement = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
		}
			
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Departement");
	}
	
	public void setListObject(List listObject) {
		this.listObject = listObject;
	}
	
	public Departement getSelectedObject() {
		return selectedObject;
	}


	public void setSelectedObject(Departement selectedObject) {
		this.selectedObject = selectedObject;
	}
		
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	/*
	 * public int getIdDrena() { return idDrena; }
	 * 
	 * public void setIdDrena(int idDrena) { this.idDrena = idDrena; }
	 */

	public List<Drena> getListDrena() {
		return listDrena = iservice.getObjects("Drena");
		}


		public void setListDrena(List<Drena> listDrena) {
			this.listDrena = listDrena;
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
