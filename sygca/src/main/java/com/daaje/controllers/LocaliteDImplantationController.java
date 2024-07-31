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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Commune;
import com.daaje.model.Departement;
import com.daaje.model.LocaliteDImplantation;
import com.daaje.model.SousPrefecture;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class LocaliteDImplantationController {
	@Autowired
	private Iservice iservice;
	private int idCommune;
	private int idSousPrefecture;
	private int idDepartement;
	private Departement departement = new Departement();
	private LocaliteDImplantation localiteDImplantation = new LocaliteDImplantation();
	private LocaliteDImplantation selectedObject = new LocaliteDImplantation();
	private List listObject = new ArrayList();
	private List<Commune> listCommune = new ArrayList<Commune>();
	private List<SousPrefecture> listSousPrefecture = new ArrayList<SousPrefecture>();
	private List<Departement> listDepartement = new ArrayList<Departement>();
	
	
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
		int nbEnregistrement = this.iservice.getObjects("LocaliteDImplantation").size();
		if(nbEnregistrement < 10)
			prefix = "LI00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "LI0" ;
		if (nbEnregistrement > 100) 
			prefix = "LI" ;
		this.localiteDImplantation.setCodeLocalite(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		localiteDImplantation.setCommune((Commune)iservice.getObjectById(idCommune, "Commune"));
		localiteDImplantation.setSousPrefecture((SousPrefecture)iservice.getObjectById(idSousPrefecture, "SousPrefecture"));
		iservice.addObject(this.localiteDImplantation);
		annuler();
		info("Enregistrement effectué");
	}
	
	
	public void chargerSousPrefecture() {
		listSousPrefecture.clear();
		departement = (Departement) iservice.getObjectById(idDepartement, "Departement");
		for (SousPrefecture var : departement.getSousPrefectures()) {
			listSousPrefecture.add(var);
		}
		
		//=======Pour le rangement par ordre alphabétique======
				Collections.sort(listSousPrefecture, new Comparator<SousPrefecture>() {
			        @Override
			        public int compare(SousPrefecture ob1, SousPrefecture ob2){
			            return  ob1.getNomSousPrefecture().compareTo(ob2.getNomSousPrefecture());
			        }
			    });
		//========================  Fin  =======================
	}
	
	public void modifier() {
		iservice.updateObject(localiteDImplantation);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		localiteDImplantation.setCodeLocalite(null);
		localiteDImplantation.setNomLocalite(null);
		setIdCommune(0);
		setIdSousPrefecture(0);
		setIdDepartement(0);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
		
	}
	
	public void selectionnerLigne() {
		localiteDImplantation = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public LocaliteDImplantation getLocaliteDImplantation() {
		return localiteDImplantation;
	}

	public void setLocaliteDImplantation(LocaliteDImplantation localiteDImplantation) {
		this.localiteDImplantation = localiteDImplantation;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("LocaliteDImplantation");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public LocaliteDImplantation getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(LocaliteDImplantation selectedObject) {
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


	public int getIdCommune() {
		return idCommune;
	}


	public void setIdCommune(int idCommune) {
		this.idCommune = idCommune;
	}


	public List<Commune> getListCommune() {
		return listCommune = iservice.getObjects("Commune");
	}


	public void setListCommune(List<Commune> listCommune) {
		this.listCommune = listCommune;
	}


	public int getIdSousPrefecture() {
		return idSousPrefecture;
	}


	public void setIdSousPrefecture(int idSousPrefecture) {
		this.idSousPrefecture = idSousPrefecture;
	}


	public List<SousPrefecture> getListSousPrefecture() {
		return listSousPrefecture;
	}


	public void setListSousPrefecture(List<SousPrefecture> listSousPrefecture) {
		this.listSousPrefecture = listSousPrefecture;
	}

	public List<Departement> getListDepartement() {
		listDepartement = iservice.getObjects("Departement");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listDepartement, new Comparator<Departement>() {
	        @Override
	        public int compare(Departement ob1, Departement ob2)
	        {
	 
	            return  ob1.getNomDepartement().compareTo(ob2.getNomDepartement());
	        }
	    });
		//========================  Fin  =======================
		return listDepartement;
	}

	public void setListDepartement(List<Departement> listDepartement) {
		this.listDepartement = listDepartement;
	}

	public int getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}

}
