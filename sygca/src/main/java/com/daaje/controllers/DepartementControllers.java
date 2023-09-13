package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Departement;
import com.daaje.service.Iservice;

@Component
public class DepartementControllers {
	@Autowired
	public Iservice iservice;
	public Departement departement = new Departement();
	public Departement selectedObject = new Departement();
	public List listObject = new ArrayList();
	
//Methodes
		public void enregistrer(){
			iservice.addObject(departement);
			annuler();
			info("Enregistrement effectué");
		}
		
		public void annuler() {
			departement.setCodeDepartement(null);
			departement.setNomDepartement(null);
			departement.setMailDepartement(null);
		}
		
		public void selectionnerLigne() {
			departement = selectedObject;
		}
		
		public void info(String message){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));	
		}
			
	//Getters and setters
		public Departement getDepartement() {
			return departement;
		}

		public void setDepartement(Departement departement) {
			this.departement = departement;
		}

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

}
