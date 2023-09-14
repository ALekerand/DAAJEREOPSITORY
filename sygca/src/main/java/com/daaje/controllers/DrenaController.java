package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
	
//Methodes
		public void enregistrer(){
			iservice.addObject(drena);
			annuler();
			info("Enregistrement effectué");
		}
		
		public void annuler() {
			drena.setCodeDrena(null);
			drena.setNomDrena(null);
			drena.setMailDrena(null);
		}
		
		public void selectionnerLigne() {
			drena = selectedObject;
		}
		
		public void info(String message){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));	
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

}
