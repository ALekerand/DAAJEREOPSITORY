package com.daaje.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Departement;
import com.daaje.model.Fonction;
import com.daaje.model.Responsable;
import com.daaje.model.UserAuthentication;
import com.daaje.model.UserAuthorization;
import com.daaje.service.Iservice;

@Component
public class ResponsableController {
	@Autowired
	private Iservice iservice;
	private int idFonction;
	private Responsable responsable = new Responsable();
	private Responsable selectedObject = new Responsable();
	private List listObject = new ArrayList();
	private List<Fonction> listFonction = new ArrayList<Fonction>();
	private UserAuthentication userAuthentication = new UserAuthentication();
	private UserAuthorization userAuthorization = new UserAuthorization() ;
	private String userRole;
	//private	boolean valueDC;
	
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
		
	public void enregistrer(){
		responsable.setFonction((Fonction) iservice.getObjectById(idFonction, "Fonction"));
		
		//Enregistrer les parametres de connection
		this.userAuthorization.setRole(this.userRole);
		this.iservice.addObject(this.userAuthorization);
		
		this.iservice.addObject(this.userAuthentication);
		
		this.userAuthorization.setUserAuthentication(userAuthentication);
		this.iservice.updateObject(this.userAuthorization);
		
		this.responsable.setUserAuthentication(this.userAuthentication);
		iservice.addObject(this.responsable);
		
		this.userAuthentication.setResponsable(responsable);
		this.userAuthentication.setEnabled(true);
		this.iservice.updateObject(userAuthentication);
		
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(responsable);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		responsable.setMatriculeResponsable(null);
		responsable.setNomResponsable(null);
		responsable.setPrenomResponsable(null);
		responsable.setTelephoneResponsable(null);
		responsable.setAdresseResponsable(null);
		responsable.setDatePriseService(null);
		responsable.setDateRetraite(null);
		responsable.setDateNaissance(null);
		responsable.setMailResponsable(null);		
		cmdBEnregistrer.setDisabled(false);
		setIdFonction(0);
		cmdBModifier.setDisabled(true);
	}
	
	public void selectionnerLigne() {
		responsable = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public Responsable getResponsable() {
		return responsable;
	}
	
	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	
	public List<Responsable> getListObject() {
		listObject = iservice.getObjects("Responsable");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Responsable>() {
	        @Override
	        public int compare(Responsable ob1, Responsable ob2)
	        {
	 
	            return  ob1.getNomResponsable().compareTo(ob2.getNomResponsable());
	        }
	    });
		//========================  Fin  =======================

return listObject;

	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Responsable getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Responsable selectedObject) {
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

	public List<Fonction> getListFonction() {
		return listFonction = iservice.getObjects("Fonction");
		
	}


	public void setListFonction(List<Fonction> listFonction) {
		this.listFonction = listFonction;
	}


	public int getIdFonction() {
		return idFonction;
	}


	public void setIdFonction(int idFonction) {
		this.idFonction = idFonction;
	}

	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}

	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public UserAuthorization getUserAuthorization() {
		return userAuthorization;
	}

	public void setUserAuthorization(UserAuthorization userAuthorization) {
		this.userAuthorization = userAuthorization;
	}

	/*
	 * public boolean isValueDC() { return valueDC; }
	 * 
	 * public void setValueDC(boolean valueDC) { this.valueDC = valueDC; }
	 */
}
