package com.daaje.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Activite;
import com.daaje.model.Apprenant;
import com.daaje.model.Campagne;
import com.daaje.model.Centre;
import com.daaje.model.Genre;
import com.daaje.model.Inscription;
import com.daaje.model.NiveauFormation;
import com.daaje.model.Responsable;
import com.daaje.model.ServiceResponsable;
import com.daaje.model.UserAuthentication;
import com.daaje.requetes.RequeteCentre;
import com.daaje.requetes.RequeteSeviceResponsable;
import com.daaje.requetes.RequeteUtilisateur;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ApprenantController {
	@Autowired
	private Iservice iservice;
	@Autowired
	private RequeteCentre requeteCentre;
	@Autowired 
	private RequeteSeviceResponsable requeteSeviceResponsable;
	@Autowired
	private RequeteUtilisateur requeteUtilisateur;
	
	private int idGenre;
	private int idActivite;
	private int idCentre;
	private Apprenant apprenant = new Apprenant();
	private Apprenant selectedObject = new Apprenant();
	private Campagne campagne = new Campagne();
	private Inscription inscription = new Inscription();
	private List listObject = new ArrayList();
	private List<Activite> listActivite = new ArrayList<Activite>();
	private List<Genre> listGenre = new ArrayList<Genre>();
	private List<Campagne> campagnes = new ArrayList<Campagne>();
	private List<Centre> listCentre = new ArrayList<Centre>();
	private ServiceResponsable serviceResponsable = new ServiceResponsable();
	private Responsable responsable = new Responsable();
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		//genererCode();
		recupererCampagneEncours();
		recuperationResponsable();
	}
	
	
	public void recuperationResponsable() {
		UserAuthentication userAuthentication = requeteUtilisateur.recuperUser();
		responsable = userAuthentication.getResponsable();
		//Recuperation du service responsable
		serviceResponsable = requeteSeviceResponsable.recupServiceRespoParRespo(responsable.getIdResponsable());
	}
	
	public void recupererCampagneEncours() {
		campagnes = iservice.getObjects("Campagne");
		for (Campagne var : campagnes) {
			if (var.getEtatCampagne()== false) {
				campagne = var;
				break;
			}
		}
	}
	
	public void genererCodeApprenant() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Apprenant").size();
		if(nbEnregistrement < 10)
			prefix = "AP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "AP0" ;
		if (nbEnregistrement > 100) 
			prefix = "AP" ;
		this.apprenant.setCodeApprenant(prefix+(nbEnregistrement+1));
	}	
	
	public void enregistrer(){
		apprenant.setActivite((Activite) iservice.getObjectById(idActivite, "Activite"));
		apprenant.setGenre((Genre) iservice.getObjectById(idGenre, "Genre"));
		genererCodeApprenant();
		iservice.addObject(this.apprenant);
		
		inscription.setApprenant(apprenant);
		inscription.setCampagne(campagne);
		inscription.setCentre((Centre) iservice.getObjectById(idCentre, "Centre"));
		inscription.setDateInscription(new Date());
		inscription.setNiveauFormation((NiveauFormation) iservice.getObjectById(1, "NiveauFormation"));
		iservice.addObject(this.inscription);
		
		annuler();
		info("Enregistrement effectué");
	}
	
	public void actualiserListe() {
		listObject = //Charger la liste en fonction de l'utilisateur
				listCentre = requeteCentre.recupCentresvalidesParIEP(serviceResponsable.getIep().getIdIep()); ;
	}
	
	public void modifier() {
		iservice.updateObject(apprenant);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		apprenant.setCodeApprenant(null);
		apprenant.setNomApprenant(null);
		apprenant.setPrenomApprenant(null);
		apprenant.setDateNaissApprenant(null);
		apprenant.setTelephoneApprenant(null);
		apprenant.setAdresseApprenant(null);
		apprenant.setMailApprenant(null);
		
		setIdGenre(0);
		setIdCentre(0);
		setIdActivite(0);
		
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCodeApprenant();
		
	}
	
	public void selectionnerLigne() {
		apprenant = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Apprenant");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Apprenant getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Apprenant selectedObject) {
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


	public Apprenant getApprenant() {
		return apprenant;
	}


	public void setApprenant(Apprenant apprenant) {
		this.apprenant = apprenant;
	}


	public int getIdGenre() {
		return idGenre;
	}


	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}


	public int getIdActivite() {
		return idActivite;
	}


	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}


	public List<Activite> getListActivite() {
		return listActivite = iservice.getObjects("Activite");
	}


	public void setListActivite(List<Activite> listActivite) {
		this.listActivite = listActivite;
	}


	public List<Genre> getListGenre() {
		return listGenre = iservice.getObjects("Genre");
	}


	public void setListGenre(List<Genre> listGenre) {
		this.listGenre = listGenre;
	}

	public Campagne getCampagne() {
		return campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}

	public int getIdCentre() {
		return idCentre;
	}

	public void setIdCentre(int idCentre) {
		this.idCentre = idCentre;
	}

	public List<Centre> getListCentre() {
		return listCentre;
	}

	public void setListCentre(List<Centre> listCentre) {
		this.listCentre = listCentre;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

}
