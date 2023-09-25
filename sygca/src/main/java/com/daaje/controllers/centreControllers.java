package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.panelgrid.PanelGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Centre;
import com.daaje.model.Iep;
import com.daaje.model.LocaliteDImplantation;
import com.daaje.model.Nature;
import com.daaje.model.NatureProjet;
import com.daaje.model.NiveauAnimateur;
import com.daaje.service.Iservice;

@Component
public class centreControllers {
	@Autowired
	public Iservice iservice;
	public Centre centre = new Centre();
	public Centre selectedObject = new Centre();
	public int idLocalite;
	public int idIep;
	public int idNature;
	public int idNatureProjet;
	public String type_promoteur;
	public List listObject = new ArrayList<>();
	public List listLocalite = new ArrayList<>();
	public List listIep = new ArrayList<>();
	public List listNatureProjet = new ArrayList<>();
	public List listNature = new ArrayList<>();
	
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
	public PanelGrid pGridOng = new PanelGrid();
	public PanelGrid pGridPh = new PanelGrid();
	public PanelGrid pGridMini = new PanelGrid();
	public PanelGrid pGridProg = new PanelGrid();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		this.pGridOng.setRendered(false);
		this.pGridMini.setRendered(false);
		this.pGridPh.setRendered(false);
		this.pGridProg.setRendered(false);
	}
	
	public void enregistrer(){
		centre.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
		centre.setLocaliteDImplantation((LocaliteDImplantation) iservice.getObjectById(idLocalite, "LocaliteDImplantation"));
		centre.setNature((Nature) iservice.getObjectById(idNature, "Nature"));
		centre.setNatureProjet((NatureProjet) iservice.getObjectById(idNatureProjet, "NatureProjet"));
		iservice.addObject(this.centre);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(centre);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		centre.setCodeCentre(null);
		centre.setAbreviationNomCentre(null);
		centre.setAdresseCentre(null);
		centre.setDroitOuvertureCentre(null);
		centre.setIep(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
	}
	
	
	public void activiverChamp() {
		System.out.println("===== Déclenchement de la méthode=====");
		switch (type_promoteur) {
		case "personne_physique": {
			this.pGridOng.setRendered(false);
			this.pGridMini.setRendered(false);
			this.pGridPh.setRendered(true);
			this.pGridProg.setRendered(false);
			break;
		}
		
		case "ong": {
			this.pGridOng.setRendered(true);
			this.pGridMini.setRendered(false);
			this.pGridPh.setRendered(false);
			this.pGridProg.setRendered(false);
			break;
		}
		
		case "programme": {
			this.pGridOng.setRendered(false);
			this.pGridMini.setRendered(false);
			this.pGridPh.setRendered(false);
			this.pGridProg.setRendered(true);
			break;
		}
		
		
		case "ministere": {
			this.pGridOng.setRendered(false);
			this.pGridMini.setRendered(true);
			this.pGridPh.setRendered(false);
			this.pGridProg.setRendered(false);
			break;
		}
		
		}
	}
	
	public void selectionnerLigne() {
		centre = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	

	public List getListObject() {
		return listObject = iservice.getObjects("NiveauAnimateur");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
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

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public Centre getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Centre selectedObject) {
		this.selectedObject = selectedObject;
	}

	public int getIdLocalite() {
		return idLocalite;
	}

	public void setIdLocalite(int idLocalite) {
		this.idLocalite = idLocalite;
	}

	public int getIdIep() {
		return idIep;
	}

	public void setIdIep(int idIep) {
		this.idIep = idIep;
	}

	public List getListLocalite() {
		return listLocalite = iservice.getObjects("LocaliteDImplantation");
	}

	public void setListLocalite(List listLocalite) {
		this.listLocalite = listLocalite;
	}

	public List getListIep() {
		return listIep = iservice.getObjects("Iep");
	}

	public void setListIep(List listIep) {
		this.listIep = listIep;
	}

	public int getIdNatureProjet() {
		return idNatureProjet;
	}

	public void setIdNatureProjet(int idNatureProjet) {
		this.idNatureProjet = idNatureProjet;
	}

	public List getListNatureProjet() {
		return listNatureProjet = iservice.getObjects("NatureProjet");
	}

	public void setListNatureProjet(List listNatureProjet) {
		this.listNatureProjet = listNatureProjet;
	}

	public int getIdNature() {
		return idNature;
	}

	public void setIdNature(int idNature) {
		this.idNature = idNature;
	}

	public List getListNature() {
		return listNature = iservice.getObjects("Nature");
	}

	public void setListNature(List listNature) {
		this.listNature = listNature;
	}

	public PanelGrid getpGridOng() {
		return pGridOng;
	}

	public void setpGridOng(PanelGrid pGridOng) {
		this.pGridOng = pGridOng;
	}

	public PanelGrid getpGridPh() {
		return pGridPh;
	}

	public void setpGridPh(PanelGrid pGridPh) {
		this.pGridPh = pGridPh;
	}

	public PanelGrid getpGridMini() {
		return pGridMini;
	}

	public void setpGridMini(PanelGrid pGridMini) {
		this.pGridMini = pGridMini;
	}

	public PanelGrid getpGridProg() {
		return pGridProg;
	}

	public void setpGridPhProg(PanelGrid pGridProg) {
		this.pGridProg = pGridProg;
	}

	public String getType_promoteur() {
		return type_promoteur;
	}

	public void setType_promoteur(String type_promoteur) {
		this.type_promoteur = type_promoteur;
	}
	
	

}
