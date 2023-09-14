package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Commune;
import com.daaje.service.Iservice;

@Component
public class CommuneController {
	
	@Autowired
	public Iservice iservice;
	public Commune commune = new Commune();
	public Commune selectedObject = new Commune();
	public List listObject = new ArrayList();
	
//Methodes
		public void enregistrer(){
			iservice.addObject(commune);
			annuler();
			info("Enregistrement effectué");
		}
		
		public void annuler() {
			commune.setCodeCommune(null);
			commune.setNomCommune(null);
		}
		
		public void selectionnerLigne() {
			commune = selectedObject;
		}
		
		public void info(String message){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));	
		}
			
	//Getters and setters
		public Commune getCommune() {
			return commune;
		}

		public void setCommune(Commune commune) {
			this.commune = commune;
		}

		public List getListObject() {
			return listObject = iservice.getObjects("Commune");
		}

		public void setListObject(List listObject) {
			this.listObject = listObject;
		}

		public Commune getSelectedObject() {
			return selectedObject;
		}

		public void setSelectedObject(Commune selectedObject) {
			this.selectedObject = selectedObject;
		}	

}
