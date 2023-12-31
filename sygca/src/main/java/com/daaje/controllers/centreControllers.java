package com.daaje.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Activite;
import com.daaje.model.Animateur;
import com.daaje.model.Campagne;
import com.daaje.model.Centre;
import com.daaje.model.Drena;
import com.daaje.model.Enseigner;
import com.daaje.model.Genre;
import com.daaje.model.Iep;
import com.daaje.model.LocaliteDImplantation;
import com.daaje.model.Ministere;
import com.daaje.model.Nature;
import com.daaje.model.NatureProjet;
import com.daaje.model.NiveauAnimateur;
import com.daaje.model.NiveauFormation;
import com.daaje.model.Ong;
import com.daaje.model.PersonnePhysique;
import com.daaje.model.Profession;
import com.daaje.model.Programme;
import com.daaje.model.Programme;
import com.daaje.model.Promoteur;
import com.daaje.model.TypeActivite;
import com.daaje.service.Iservice;

@Component
public class centreControllers {
	@Autowired
	private Iservice iservice;

	public Centre centre = new Centre();
	private int idLocalite;
	private int idIep;
	private int idNature;
	private int idNatureProjet;
	private int idDepartement;
	private int idDrena;
	private int idNiveau;
	private int idGenre;
	private int idActivitePrimaire;
	private int idActiviteSecondaire;
	private String value1, value2, value3;
	private Animateur animateur = new Animateur();
	private Drena choosedDepartement = new Drena();
	private Drena choosedDrena = new Drena();
	private Centre selectedObject = new Centre();
	private Promoteur promoteur = new Promoteur();
	private Campagne campagneEnCours = new Campagne();
	private TypeActivite typeActivitePrincipale = new TypeActivite();
	private TypeActivite typeActiviteSecondaire = new TypeActivite();
	private Genre genre = new Genre();
	private Profession profession = new Profession();
	private Activite activitePrimaire = new Activite();
	private Activite activiteSecondaire = new Activite();
	private Enseigner enseigner = new Enseigner();
	private  Ong ong = new Ong();
	private PersonnePhysique personnePhysique = new PersonnePhysique();
	private Programme programme = new Programme();
	private Ministere ministere = new Ministere();
	private String type_promoteur;
	private List listObject = new ArrayList<>();
	private List listLocalite = new ArrayList<>();
	private List listIep = new ArrayList<>();
	private List listDrena = new ArrayList<>();
	private List listDepartement = new ArrayList<>();
	private List listNatureProjet = new ArrayList<>();
	private List listNature = new ArrayList<>();
	private List listActivite = new ArrayList<>();
	private List listGenre = new ArrayList<>();
	private List listNiveauAnimateur = new ArrayList<>();
	private List<Campagne> campagnes = new ArrayList<Campagne>();
	private boolean skip;
	
	private UploadedFile fichier;
	private String chemin = "C:\\SYGCA\\AUTORISATION";
	
	
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
		recupererCampagneEncours();
		genererCodePromoteur();
		genererCodeAnimateur();
	}
	
	public void recupererCampagneEncours() {
		campagnes = iservice.getObjects("Campagne");
		for (Campagne var : campagnes) {
			if (var.getEtatCampagne()== false) {
				campagneEnCours = var;
			}
		}
	}
	
	
	public void upload() {
		String extValidate;
		if(getFichier() != null) {
			String ext = getFichier().getFileName();
			if(ext != null) {
				extValidate = ext.substring(ext.indexOf(".")+1);
			}else {
				extValidate = "null";
			}
			if(extValidate.equals("docx") || extValidate.equals("pdf") || extValidate.equals("pptx") || extValidate.equals("xlsx")) {
				try {
					transfererFile(getFichier().getFileName(), getFichier().getInputstream());
				}catch (IOException ex) {
					System.out.println(ex);
				}
			}
		}
	}
	
	public void transfererFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(chemin + fileName));
			int reader = 0;
			byte[] bytes = new byte[(int)getFichier().getSize()];
			while ((reader = in.read(bytes))!= -1) {
				out.write(bytes,0,reader);
			}
			in.close();
			out.flush();
			out.close();
		}catch (IOException e) {
			System.out.println(e);
		}
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
	
	public void genererCodeAnimateur() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Animateur").size();
		if(nbEnregistrement < 10)
			prefix = "AN00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "AN0" ;
		if (nbEnregistrement > 100) 
			prefix = "AN" ;
		this.animateur.setCodeAnimateur(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		//Enregistrer le promoteur
		 genererCodePromoteur();
		 iservice.addObject(this.promoteur);
		 
			switch (type_promoteur){
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
			
			//Enregistrer le centre
			centre.setIep((Iep) iservice.getObjectById(idIep, "Iep"));
			centre.setLocaliteDImplantation((LocaliteDImplantation) iservice.getObjectById(idLocalite, "LocaliteDImplantation"));
			centre.setNature((Nature) iservice.getObjectById(idNature, "Nature"));
			centre.setNatureProjet((NatureProjet) iservice.getObjectById(idNatureProjet, "NatureProjet"));
			centre.setPromoteur(promoteur);
			centre.setDroitOuvertureCentre(chemin);
			upload();
			iservice.addObject(this.centre);
		
			//Gestion de l'animateur
			animateur.setGenre((Genre)iservice.getObjectById(idGenre, "Genre"));
			animateur.setNiveauAnimateur((NiveauAnimateur) iservice.getObjectById(idNiveau, "NiveauAnimateur"));
			iservice.addObject(animateur);
			
			//Gestion de la profession
			profession.setActivite((Activite) iservice.getObjectById(idActivitePrimaire, "Activite"));
			profession.setTypeActivite((TypeActivite) iservice.getObjectById(1, "TypeActivite"));
			profession.setAnimateur(animateur);
			iservice.addObject(profession);
			if ((activiteSecondaire.getNomActivite()!= null) && (typeActiviteSecondaire.getLibelleTypeactivite() != null)) {
			profession.setActivite((Activite) iservice.getObjectById(getIdActiviteSecondaire(), "Activite"));
			profession.setTypeActivite((TypeActivite) iservice.getObjectById(2, "TypeActivite"));
			profession.setAnimateur(animateur);
			iservice.addObject(profession);
			}
			
			
			//Gestion de la table Enseigner
			enseigner.setCampagne(campagneEnCours);
			if (value1 !="") {
				enseigner.setNiveauFormation((NiveauFormation) iservice.getObjectById(1, "NiveauFormation"));
				enseigner.setAnimateur(animateur);
				enseigner.setCampagne(campagneEnCours);
				enseigner.setCentre(centre);
				iservice.addObject(enseigner);
			}
			
			if (value2 !="") {
				enseigner.setNiveauFormation((NiveauFormation) iservice.getObjectById(2, "NiveauFormation"));
				enseigner.setAnimateur(animateur);
				enseigner.setCampagne(campagneEnCours);
				enseigner.setCentre(centre);
				iservice.addObject(enseigner);
			}
			
			if (value3 !="") {
				enseigner.setNiveauFormation((NiveauFormation) iservice.getObjectById(3, "NiveauFormation"));
				enseigner.setAnimateur(animateur);
				enseigner.setCampagne(campagneEnCours);
				enseigner.setCentre(centre);
				iservice.addObject(enseigner);
			}
			
		annuler();
		genererCodePromoteur();
		genererCodeAnimateur();;
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(centre);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		//Personne Physique
		personnePhysique.setNomPersonne(null);
		personnePhysique.setPrenomsPersonne(null);
		personnePhysique.setTelephonePersonne(null);
		personnePhysique.setMailPersonne(null);
		//ONG
		ong.setNomOng(null);
		ong.setTelephoneOng(null);
		ong.setMailOng(null);
		//Ministère
		ministere.setNomMinistere(null);
		ministere.setTelephoneMinistere(null);
		//Centre
		centre.setNomCentre(null);
		centre.setAbreviationNomCentre(null);
		centre.setAdresseCentre(null);
		centre.setTelephoneCentre(null);
		centre.setMailCentre(null);
		centre.setDroitOuvertureCentre(null);
		//les combos
		setIdDepartement(0);
		setIdDrena(0);
		setIdIep(0);
		setIdLocalite(0);
		setIdNatureProjet(0);
		setIdNature(0);
		setIdGenre(0);
		setIdNiveau(0);
		setIdActivitePrimaire(0);
		setIdActiviteSecondaire(0);
		//Animateur
		animateur.setCodeAnimateur(null);
		animateur.setNomAnimateur(null);
		animateur.setPrenomAnimateur(null);
		animateur.setAdresseAnimateur(null);
		
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
	}
	
	
	public void activiverChamp() {
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
		return listObject = iservice.getObjects("Centre");
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

	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
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


	public List getListActivite() {
		return listActivite = iservice.getObjects("Activite");
	}


	public void setListActivite(List listActivite) {
		this.listActivite = listActivite;
	}


	public Activite getActiviteSecondaire() {
		return activiteSecondaire;
	}


	public void setActiviteSecondaire(Activite activiteSecondaire) {
		this.activiteSecondaire = activiteSecondaire;
	}


	public Activite getActivitePrimaire() {
		return activitePrimaire;
	}


	public void setActivitePrimaire(Activite activitePrimaire) {
		this.activitePrimaire = activitePrimaire;
	}


	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public List getListGenre() {
		return listGenre = iservice.getObjects("Genre");
	}


	public void setListGenre(List listGenre) {
		this.listGenre = listGenre;
	}


	public List getListNiveauAnimateur() {
		return listNiveauAnimateur = iservice.getObjects("NiveauAnimateur");
	}


	public void setListNiveauAnimateur(List listNiveauAnimateur) {
		this.listNiveauAnimateur = listNiveauAnimateur;
	}


	public int getIdNiveau() {
		return idNiveau;
	}


	public void setIdNiveau(int idNiveau) {
		this.idNiveau = idNiveau;
	}


	public int getIdActivitePrimaire() {
		return idActivitePrimaire;
	}


	public void setIdActivitePrimaire(int idActivitePrimaire) {
		this.idActivitePrimaire = idActivitePrimaire;
	}


	public int getIdActiviteSecondaire() {
		return idActiviteSecondaire;
	}


	public void setIdActiviteSecondaire(int idActiviteSecondaire) {
		this.idActiviteSecondaire = idActiviteSecondaire;
	}


	public int getIdGenre() {
		return idGenre;
	}


	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}


	public String getValue1() {
		return value1;
	}


	public void setValue1(String value1) {
		this.value1 = value1;
	}


	public String getValue2() {
		return value2;
	}


	public void setValue2(String value2) {
		this.value2 = value2;
	}


	public String getValue3() {
		return value3;
	}


	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public UploadedFile getFichier() {
		return fichier;
	}

	public void setFichier(UploadedFile fichier) {
		this.fichier = fichier;
	}
}
