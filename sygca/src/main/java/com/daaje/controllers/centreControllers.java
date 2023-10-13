package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Animateur;
import com.daaje.model.Centre;
import com.daaje.model.Drena;
import com.daaje.model.Genre;
import com.daaje.model.Iep;
import com.daaje.model.LocaliteDImplantation;
import com.daaje.model.Ministere;
import com.daaje.model.Nature;
import com.daaje.model.NatureProjet;
import com.daaje.model.Ong;
import com.daaje.model.PersonnePhysique;
import com.daaje.model.Progamme;
import com.daaje.model.Promoteur;
import com.daaje.service.Iservice;

@Component
public class centreControllers {
	@Autowired
	public Iservice iservice;
	public Centre centre = new Centre();
	//public dep
	private int idLocalite;
	private int idIep;
	private int idNature;
	private int idNatureProjet;
	private int idDepartement;
	private int idDrena;
	private Animateur animateur = new Animateur();
	private Drena choosedDepartement = new Drena();
	private Drena choosedDrena = new Drena();
	private Centre selectedObject = new Centre();
	private Promoteur promoteur = new Promoteur();
	private Genre genre = new Genre();
	private  Ong ong = new Ong();
	private PersonnePhysique personnePhysique = new PersonnePhysique();
	private Progamme programme = new Progamme();
	private Ministere ministere = new Ministere();
	private String type_promoteur;
	private List listObject = new ArrayList<>();
	private List listLocalite = new ArrayList<>();
	private List listIep = new ArrayList<>();
	private List listDrena = new ArrayList<>();
	private List listDepartement = new ArrayList<>();
	private List listNatureProjet = new ArrayList<>();
	private List listNature = new ArrayList<>();
	private boolean skip;
	
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
	private SelectOneRadio radio_promo = new SelectOneRadio();
	private PanelGrid pGridOng = new PanelGrid();
	private PanelGrid pGridPh = new PanelGrid();
	private PanelGrid pGridMini = new PanelGrid();
	private PanelGrid pGridProg = new PanelGrid();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		this.pGridOng.setRendered(false);
		this.pGridMini.setRendered(false);
		this.pGridPh.setRendered(false);
		this.pGridProg.setRendered(false);
	}
	
	
	public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	
	public void genererCodePromoteur() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Promoteur").size();
		if(nbEnregistrement < 10)
			prefix = "PR00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "PR0" ;
		if (nbEnregistrement > 100) 
			prefix = "PR" ;
		this.promoteur.setCodePromoteur(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		centre.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
		centre.setLocaliteDImplantation((LocaliteDImplantation) iservice.getObjectById(idLocalite, "LocaliteDImplantation"));
		centre.setNature((Nature) iservice.getObjectById(idNature, "Nature"));
		centre.setNatureProjet((NatureProjet) iservice.getObjectById(idNatureProjet, "NatureProjet"));
		
		//Enregistrer du promoteur
		 genererCodePromoteur();
		iservice.addObject(this.promoteur);
		
		switch (type_promoteur) {
		case "personne_physique": {
			personnePhysique.setPromoteur(promoteur);
			personnePhysique.setCodePromoteur(promoteur.getCodePromoteur());
			iservice.addObject(personnePhysique);
			break;
		}
		
		case "ong": {
			ong.setPromoteur(promoteur);
			ong.setCodePromoteur(promoteur.getCodePromoteur());
			iservice.addObject(ong);
			break;
		}
		
		case "programme": {
			programme.setPromoteur(promoteur);
			programme.setCodePromoteur(promoteur.getCodePromoteur());
			iservice.addObject(programme);
			break;
		}
		
		
		case "ministere": {
			ministere.setPromoteur(promoteur);
			ministere.setCodePromoteur(promoteur.getCodePromoteur());
			iservice.addObject(ministere);
			break;
		}
		
		}

		centre.setPromoteur(promoteur);
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
	
	public void chargerDrena() {
		choosedDepartement = (Drena) iservice.getObjectById(idDepartement,"Departement");
		//listDrena = choosedDepartement.getdr
		
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

	public int getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}

	public List getListDepartement() {
		return listDepartement = iservice.getObjects("Departement");
	}

	public void setListDepartement(List listDepartement) {
		this.listDepartement = listDepartement;
	}

	public void setpGridProg(PanelGrid pGridProg) {
		this.pGridProg = pGridProg;
	}

	public List getListDrena() {
		return listDrena = iservice.getObjects("Drena");
	}

	public void setListDrena(List listDrena) {
		this.listDrena = listDrena;
	}

	public int getIdDrena() {
		return idDrena;
	}

	public void setIdDrena(int idDrena) {
		this.idDrena = idDrena;
	}

	public SelectOneRadio getRadio_promo() {
		return radio_promo;
	}

	public void setRadio_promo(SelectOneRadio radio_promo) {
		this.radio_promo = radio_promo;
	}

	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
	}

	public PersonnePhysique getPersonnePhysique() {
		return personnePhysique;
	}

	public void setPersonnePhysique(PersonnePhysique personnePhysique) {
		this.personnePhysique = personnePhysique;
	}

	

	public Ministere getMinistere() {
		return ministere;
	}

	public void setMinistere(Ministere ministere) {
		this.ministere = ministere;
	}

	public Progamme getProgramme() {
		return programme;
	}

	public void setProgramme(Progamme programme) {
		this.programme = programme;
	}


	public boolean isSkip() {
		return skip;
	}


	public void setSkip(boolean skip) {
		this.skip = skip;
	}


	public Animateur getAnimateur() {
		return animateur;
	}


	public void setAnimateur(Animateur animateur) {
		this.animateur = animateur;
	}
	
	
	
	
}
