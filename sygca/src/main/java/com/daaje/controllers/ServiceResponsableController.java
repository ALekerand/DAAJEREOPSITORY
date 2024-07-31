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

import com.daaje.model.Drena;
import com.daaje.model.Iep;
import com.daaje.model.Responsable;
import com.daaje.model.ServiceResponsable;
import com.daaje.requetes.RequeteIEP;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ServiceResponsableController {
	@Autowired
	private Iservice iservice;
	
	@Autowired
	private RequeteIEP requeteIEP;
	
	
	private int idResponsable;
	private int idDrena;
	private int idIep;
	private ServiceResponsable serviceResponsable = new ServiceResponsable();
	private ServiceResponsable selectedObject = new ServiceResponsable();
	private Drena choosedDrena = new Drena();
	private List listObject = new ArrayList();
	private List<Responsable> listResponsable = new ArrayList<Responsable>();
	private List<Drena> listDrena = new ArrayList<Drena>();
	private List<Iep> listIep = new ArrayList<Iep>();
	
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
		int nbEnregistrement = this.iservice.getObjects("ServiceResponsable").size();
		if(nbEnregistrement < 10)
			prefix = "SR00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "SR0" ;
		if (nbEnregistrement > 100) 
			prefix = "SR" ;
		this.serviceResponsable.setCodeService(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		serviceResponsable.setResponsable((Responsable) iservice.getObjectById(idResponsable, "Responsable"));
		serviceResponsable.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
		serviceResponsable.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
		iservice.addObject(this.serviceResponsable);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(serviceResponsable);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		serviceResponsable.setCodeService(null);
		serviceResponsable.setDateArrivee(null);
		serviceResponsable.setDateDepart(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
	}
	
	public void selectionnerLigne() {
		serviceResponsable = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void chargerIep() {
		listIep.clear();
		choosedDrena = (Drena) iservice.getObjectById(idDrena, "Drena");
		for (Iep var : choosedDrena.getIeps()) {
			listIep.add(var);
		}
		//=======Pour le rangement par ordre alphabétique======
				Collections.sort(listIep, new Comparator<Iep>() {
			        @Override
			        public int compare(Iep ob1, Iep ob2)
			        {
			 
			            return  ob1.getNomIep().compareTo(ob2.getNomIep());
			        }
			    });
				//========================  Fin  =======================
	}
	
	
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("ServiceResponsable");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public ServiceResponsable getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(ServiceResponsable selectedObject) {
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


	public ServiceResponsable getServiceResponsable() {
		return serviceResponsable;
	}


	public void setServiceResponsable(ServiceResponsable serviceResponsable) {
		this.serviceResponsable = serviceResponsable;
	}


	public int getIdResponsable() {
		return idResponsable;
	}


	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}


	public int getIdDrena() {
		return idDrena;
	}


	public void setIdDrena(int idDrena) {
		this.idDrena = idDrena;
	}


	public int getIdIep() {
		return idIep;
	}


	public void setIdIep(int idIep) {
		this.idIep = idIep;
	}


	public List<Responsable> getListResponsable() {
		listResponsable = iservice.getObjects("Responsable");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listResponsable, new Comparator<Responsable>() {
	        @Override
	        public int compare(Responsable ob1, Responsable ob2)
	        {
	 
	            return  ob1.getNomResponsable().compareTo(ob2.getNomResponsable());
	        }
	    });
		//========================  Fin  =======================
		
		return listResponsable = iservice.getObjects("Responsable");
	}


	public void setListResponsable(List<Responsable> listResponsable) {
		this.listResponsable = listResponsable;
	}


	public List<Drena> getListDrena() {
		return listDrena = iservice.getObjects("Drena");
	}


	public void setListDrena(List<Drena> listDrena) {
		this.listDrena = listDrena;
	}


	public List<Iep> getListIep() {
		return listIep;
	}


	public void setListIep(List<Iep> listIep) {
		this.listIep = listIep;
	}
}
