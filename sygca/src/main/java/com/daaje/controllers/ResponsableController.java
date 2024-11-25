package com.daaje.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Drena;
import com.daaje.model.Fonction;
import com.daaje.model.Iep;
import com.daaje.model.Responsable;
import com.daaje.model.ServiceResponsable;
import com.daaje.model.UserAuthentication;
import com.daaje.model.UserAuthorization;
import com.daaje.requetes.RequeteUtilisateur;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ResponsableController {
	
	@Autowired
	private Iservice iservice;
	
	@Autowired
	private RequeteUtilisateur requeteUtilisateur;
	
	
	private int idFonction;
	private int idResponsable;
	private int idDrena;
	private int idIep;
	
	private Responsable responsable = new Responsable();
	private ServiceResponsable serviceResponsable = new ServiceResponsable();
	private Responsable selectedObject = new Responsable();
	private List listObject = new ArrayList();
	private List<Fonction> listFonction = new ArrayList<Fonction>();
	private UserAuthentication userAuthentication = new UserAuthentication();
	private UserAuthorization userAuthorization = new UserAuthorization() ;
	private String userRole;
	private boolean etatActif_1, etatActif_2;
	
	private Drena choosedDrena = new Drena();
	private List<Drena> listDrena = new ArrayList<Drena>();
	private List<Iep> listIep = new ArrayList<Iep>();
	
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
	private SelectOneMenu oneMenuDrena = new SelectOneMenu();
	private SelectOneMenu oneMenuIep = new SelectOneMenu();
	private SelectOneMenu oneMenuFonction = new SelectOneMenu();
	
	private SelectOneRadio OneRadioProf = new SelectOneRadio();
	private Calendar  datecalendar = new Calendar();
	
	private SelectBooleanCheckbox checkboxMembre = new SelectBooleanCheckbox();
	
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	public void genererCodeResponsable() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Responsable").size();
		if(nbEnregistrement < 10)
			prefix = "RP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "RP0" ;
		if (nbEnregistrement > 100) 
			prefix = "RP" ;
		
		this.responsable.setCodeResponsable(prefix+(nbEnregistrement+1));
	}
	
	
	public void genererCodeServiceResponsable() {
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
	
	
	public void activerChamps() {
		//Si le champ de membre est activé
		if (checkboxMembre.isSelected()== true) {
			setEtatActif_1(true);
			setEtatActif_2(false);
			oneMenuDrena.setDisabled(true);
			oneMenuIep.setDisabled(true);
			//oneMenuFonction.setDisabled(true);
			datecalendar.setDisabled(true);
		}else {
			setEtatActif_1(false);
			setEtatActif_2(true);
			oneMenuDrena.setDisabled(false);
			oneMenuIep.setDisabled(false);
			//oneMenuFonction.setDisabled(false);
			datecalendar.setDisabled(false);
		}
	}
	
		
	public void enregistrer(){
		
		//Vérifier le login avant enregistrement
		if(requeteUtilisateur.recupererUserParLogin(userAuthentication.getUsername())== null) {
			genererCodeResponsable();
			
			responsable.setFonction((Fonction) iservice.getObjectById(4, "Fonction"));
				
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
			
			//Enregistrement de service responsable
			serviceResponsable.setResponsable(responsable);
			serviceResponsable.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
			serviceResponsable.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
			iservice.addObject(this.serviceResponsable);

			annuler();
			info("Enregistrement effectué");
			genererCodeResponsable();
		}else {
			error("Le login est déjà enrigistré. Veuillez en choisir un autre");
		}
	}
	
	
	
	public void enregistrerServiceResponsable(){
		serviceResponsable.setResponsable((Responsable) iservice.getObjectById(idResponsable, "Responsable"));
		serviceResponsable.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
		serviceResponsable.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
		iservice.addObject(this.serviceResponsable);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(responsable);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		this.responsable.setCodeResponsable(null);
		this.responsable.setMatriculeResponsable(null);
		this.responsable.setNomResponsable(null);
		this.responsable.setPrenomResponsable(null);
		this.responsable.setTelephoneResponsable(null);
		this.responsable.setAdresseResponsable(null);
		this.responsable.setDatePriseService(null);
		this.responsable.setDateRetraite(null);
		this.responsable.setDateNaissance(null);
		this.responsable.setMailResponsable(null);
		
		userAuthentication.setUsername(null);
		userAuthentication.setPassword(null);
		
		serviceResponsable.setCodeService(null);
		serviceResponsable.setDateArrivee(null);
		serviceResponsable.setDateDepart(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
		
		setIdFonction(0);
		setIdIep(0);
		setIdDrena(0);
		setIdResponsable(0);
		
		OneRadioProf.clearInitialState();
		
		
		genererCodeResponsable();
		genererCodeServiceResponsable();
		
		this.cmdBEnregistrer.setDisabled(false);
		this.cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		responsable = selectedObject;
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
	
	
	public void error(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message,null));	
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


	public SelectBooleanCheckbox getCheckboxMembre() {
		return checkboxMembre;
	}


	public void setCheckboxMembre(SelectBooleanCheckbox checkboxMembre) {
		this.checkboxMembre = checkboxMembre;
	}




	public boolean isEtatActif_1() {
		return etatActif_1;
	}




	public void setEtatActif_1(boolean etatActif_1) {
		this.etatActif_1 = etatActif_1;
	}




	public boolean isEtatActif_2() {
		return etatActif_2;
	}




	public void setEtatActif_2(boolean etatActif_2) {
		this.etatActif_2 = etatActif_2;
	}

	public int getIdDrena() {
		return idDrena;
	}

	public void setIdDrena(int idDrena) {
		this.idDrena = idDrena;
	}
	
	public List<Drena> getListDrena() {
		return listDrena = iservice.getObjects("Drena");
	}


	public void setListDrena(List<Drena> listDrena) {
		this.listDrena = listDrena;
	}

	public int getIdIep() {
		return idIep;
	}

	public void setIdIep(int idIep) {
		this.idIep = idIep;
	}
	
	public List<Iep> getListIep() {
		return listIep;
	}


	public void setListIep(List<Iep> listIep) {
		this.listIep = listIep;
	}

	public SelectOneMenu getOneMenuDrena() {
		return oneMenuDrena;
	}

	public void setOneMenuDrena(SelectOneMenu oneMenuDrena) {
		this.oneMenuDrena = oneMenuDrena;
	}

	public SelectOneMenu getOneMenuIep() {
		return oneMenuIep;
	}

	public void setOneMenuIep(SelectOneMenu oneMenuIep) {
		this.oneMenuIep = oneMenuIep;
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

	public Calendar getDatecalendar() {
		return datecalendar;
	}

	public void setDatecalendar(Calendar datecalendar) {
		this.datecalendar = datecalendar;
	}

	public SelectOneMenu getOneMenuFonction() {
		return oneMenuFonction;
	}

	public void setOneMenuFonction(SelectOneMenu oneMenuFonction) {
		this.oneMenuFonction = oneMenuFonction;
	}

	public SelectOneRadio getOneRadioProf() {
		return OneRadioProf;
	}

	public void setOneRadioProf(SelectOneRadio oneRadioProf) {
		OneRadioProf = oneRadioProf;
	}

}
