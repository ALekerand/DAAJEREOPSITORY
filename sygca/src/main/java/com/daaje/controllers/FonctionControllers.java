package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Fonction;
import com.daaje.service.Iservice;

@Component
public class FonctionControllers {
	@Autowired
	public Iservice iservice;
	public Fonction fonction = new Fonction();
	public Fonction selectedObject = new Fonction();
	public List listObject = new ArrayList();
	
//Methodes
		public void enregistrer(){
			iservice.addObject(fonction);
			annuler();
			info("Enregistrement effectué");
		}
		
		public void annuler() {
			fonction.setCodeFonction(null);
			fonction.setLibelleFonction(null);
		}
		
		public void selectionnerLigne() {
			fonction = selectedObject;
		}
		
		public void info(String message){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));	
		}

//Getters and setters
		public Fonction getFonction() {
			return fonction;
		}

		public void setFonction(Fonction fonction) {
			this.fonction = fonction;
		}

		public List getListObject() {
			return listObject = iservice.getObjects("Fonction");
		}

		public void setListObject(List listObject) {
			this.listObject = listObject;
		}

		public Fonction getSelectedObject() {
			return selectedObject;
		}

		public void setSelectedObject(Fonction selectedObject) {
			this.selectedObject = selectedObject;
		}
		
}
