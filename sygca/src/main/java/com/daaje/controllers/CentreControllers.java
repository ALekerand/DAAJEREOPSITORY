package com.daaje.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Activite;
import com.daaje.model.Animateur;
import com.daaje.model.Campagne;
import com.daaje.model.Centre;
import com.daaje.model.Departement;
import com.daaje.model.Drena;
import com.daaje.model.DrenaDepartement;
import com.daaje.model.Ecole;
import com.daaje.model.Enseigner;
import com.daaje.model.Genre;
import com.daaje.model.Iep;
import com.daaje.model.Langue;
import com.daaje.model.LocaliteDImplantation;
import com.daaje.model.Ministere;
import com.daaje.model.Nature;
import com.daaje.model.NatureProjet;
import com.daaje.model.NiveauAnimateur;
import com.daaje.model.NiveauFormation;
import com.daaje.model.Ong;
import com.daaje.model.PersonneMorale;
import com.daaje.model.PersonnePhysique;
import com.daaje.model.Profession;
import com.daaje.model.Programme;
import com.daaje.model.Promoteur;
import com.daaje.model.Responsable;
import com.daaje.model.ServiceResponsable;
import com.daaje.model.SousPrefecture;
import com.daaje.model.TypeActivite;
import com.daaje.model.TypeAlphabetisation;
import com.daaje.model.UserAuthentication;
import com.daaje.requetes.RequeteCentre;
import com.daaje.requetes.RequeteEcole;
import com.daaje.requetes.RequeteSeviceResponsable;
import com.daaje.requetes.RequeteUtilisateur;
import com.daaje.service.Iservice;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Component
@Scope("session")
public class CentreControllers {
	@Autowired
	private Iservice iservice;
	@Autowired
	private RequeteEcole requeteEcole;
	@Autowired 
	private RequeteSeviceResponsable requeteSeviceResponsable;
	@Autowired
	private RequeteCentre requeteCentre;
	@Autowired
	private RequeteUtilisateur requeteUtilisateur;
	
	private Centre centre = new Centre();
	private int idLocalite;
	private int idIep;
	private int idNature;
	private int idNatureProjet;
	private int idDepartement;
	private int idSousPrefecture;
	private int idDrena;
	private int idNiveau;
	private int idGenre;
	private int idEcole;
	private int idActivitePrimaire;
	private int idActiviteSecondaire;
	private int idTypeAlpha;
	private int idLangue;
	private String value1, value2, value3;
	private String lieu;
	private boolean skip;
	private String etatPermanence;
	private String etatLieu;
	private LocaliteDImplantation localite = new LocaliteDImplantation();
	private TypeAlphabetisation typeAlphabetisation = new TypeAlphabetisation();
	private UserAuthentication userAuthentication = new UserAuthentication();
	private Animateur animateur = new Animateur();
	private Nature natureCentre = new Nature();
	private Langue langue = new Langue();
	private Departement choosedDepartement = new Departement();
	private SousPrefecture choosedSousPrefecture = new SousPrefecture();
	private NatureProjet natureProjet = new NatureProjet();
	private NiveauAnimateur niveauAnimateur = new NiveauAnimateur();
	private ServiceResponsable serviceResponsable = new ServiceResponsable();
	
	private Drena choosedDrena = new Drena();
	private Iep choosedIep = new Iep();
	private LocaliteDImplantation choosedLocalite = new LocaliteDImplantation();
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
	private Ong ong = new Ong();
	private PersonnePhysique personnePhysique = new PersonnePhysique();
	private PersonneMorale personneMorale = new PersonneMorale();
	private Programme programme = new Programme();
	private Ministere ministere = new Ministere();
	private String type_promoteur;
	private List listObject = new ArrayList<>();
	private List listLocalite = new ArrayList<>();
	private List listIep = new ArrayList<>();
	private List<Drena> listDrena = new ArrayList<Drena>();
	private List listDepartement = new ArrayList<>();
	private List<SousPrefecture> listSousPrefecture = new ArrayList<SousPrefecture>();
	private List listNatureProjet = new ArrayList<>();
	private List listNature = new ArrayList<>();
	private List listActivite = new ArrayList<>();
	private List listGenre = new ArrayList<>();
	private List listNiveauAnimateur = new ArrayList<>();
	private List listEcole = new ArrayList<>();
	private List<Campagne> campagnes = new ArrayList<Campagne>();
	private List<Langue> listLangue = new ArrayList<Langue>();
	private List<TypeAlphabetisation> listTypeAlpha = new ArrayList<TypeAlphabetisation>();
	private String naturePromoteur;
	
	//private UploadedFile fichier;
	private String chemin = "C:\\SYGCA\\AUTORISATION";
	
	private	StreamedContent content = new DefaultStreamedContent();
	private String cheminFinal ="";
	private String destination = "C:/SYGCA/";
	
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
	private SelectOneRadio radio_promo = new SelectOneRadio();
	private SelectOneMenu natureProOneMenu = new SelectOneMenu();
	private boolean etatGraphicImage;
	private boolean etatFileUpload;
	private SelectOneMenu oneMenuEcole = new SelectOneMenu();
	private InputText inputLieu = new InputText();
	private SelectOneRadio oneRadioNatureLieu = new SelectOneRadio();
	private SelectBooleanCheckbox checkbox1 = new SelectBooleanCheckbox();
	private SelectBooleanCheckbox checkbox2 = new SelectBooleanCheckbox();
	private SelectBooleanCheckbox checkbox3 = new SelectBooleanCheckbox();
	private SelectOneRadio oneRadioPermanent = new SelectOneRadio();
	
	private boolean pGridOng;
	private boolean pGridPh;
	private boolean pGridMini;
	private boolean pGridProg;
	private boolean pGridEntrep;
	
	//Pour le recap
	private boolean recap_grid_ph;
	private boolean recap_grid_pm;
	private boolean recap_grid_ong;
	private boolean recap_grid_prog;
	private boolean recap_grid_mini;
	

		
	//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		this.setpGridOng(false);
		this.setpGridMini(false);
		this.setpGridPh(false);
		this.setpGridProg(false);
		this.setpGridEntrep(false);
		this.natureProOneMenu.setDisabled(true);
		this.oneMenuEcole.setDisabled(true);
		this.setEtatFileUpload(false);
		this.setEtatGraphicImage(false);
		this.inputLieu.setDisabled(true);
		recupererCampagneEncours();
		genererCodePromoteur();
		genererCodeAnimateur();
		recupererResponsable();
	}
	
	public void recupererResponsable() {
		userAuthentication = requeteUtilisateur.recuperUser();
		serviceResponsable = requeteSeviceResponsable.recupServiceRespoParRespo(userAuthentication.getResponsable().getIdResponsable());
	}
	
	
	public void genererCodeEnseigner() {
		// A terminer
	}
	
	
	public void genererCodeCentre() {
		int nbEnregistrement = this.iservice.getObjects("Centre").size()+1;
		//choosedIep = (Iep) iservice.getObjectById(idIep, "Iep");
		String code = "D"+choosedDrena.getCodeDrena().substring(4, 6)+"I"+choosedIep.getCodeIep().substring(3, 6)+"-"+nbEnregistrement;
		centre.setCodeCentre(code);
	}
	
	
	public void genererFicheEnreg() throws JRException, ServletException, IOException {
		//Localisation du fichier
		JasperDesign jasperDesign = JRXmlLoader.load("C:/rapport_sygca/Fiche_enregistrement.jrxml");
		//Compilation du fichier
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nom_prom", "GBANE");
		parameters.put("prenom_prom", "MAHAMA");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/rapport_sygca/"+centre.getCodeCentre()+".pdf");
		
		//Ouvrir le fichier
		ouvrirPDF();
	}
	
	
	public void ouvrirPDF()throws ServletException, IOException {
        // Chemin du fichier PDF
		
		//if ((selectedObject == null) ||(selectedObject.getCodeMateriel().equals("")) ) {
		//	error("Veuillez selectionner le matériel avnt l'impression du QR CODE");
	//	}else {
			try {
				String pdfFilePath = "C:\\rapport_sygca\\null.pdf";
				File pdfFile = new File(pdfFilePath);
				
				// Configuration de la réponse
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				response.setContentType("application/pdf");
				response.setContentLength((int) pdfFile.length());
				response.setHeader("Content-Disposition", "inline; filename=\"" + pdfFile.getName() + "\"");

				// Lecture et envoi du fichier PDF
				FileInputStream fis = new FileInputStream(pdfFile);
				OutputStream os = response.getOutputStream(); 
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
				        os.write(buffer, 0, bytesRead);
				    }
		
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				//error("Veuillez selectionner le matériel avnt l'impression du QR CODE");
			} 
        }
	
	
	public UserAuthentication chagerUtilisateur() {
		return userAuthentication;
	}
	
	public void recupererCampagneEncours() {
		campagnes = iservice.getObjects("Campagne");
		for (Campagne var : campagnes) {
			if (var.getEtatCampagne()== false) {
				campagneEnCours = var;
			}
		}
	}
	
	
	
	public void choisirLocalite() {
		localite = (LocaliteDImplantation) iservice.getObjectById(idLocalite, "LocaliteDImplantation");
	}
	
	public void choisirNatureProjet() {
		natureProjet = (NatureProjet) iservice.getObjectById(idNatureProjet, "NatureProjet");
	}
	
	
	public void choisirTypeAlpha() {
		typeAlphabetisation = (TypeAlphabetisation) iservice.getObjectById(idTypeAlpha, "TypeAlphabetisation");
	}
	
	
	public void choisirLangue() {
		setLangue((Langue) iservice.getObjectById(idLangue, "Langue"));
	}
	
	public void choisirNiveauFormateur() {
		setNiveauAnimateur((NiveauAnimateur) iservice.getObjectById(idNiveau, "NiveauAnimateur"));
		}
	
	public void choisirActivitePricipale() {
		activitePrimaire = (Activite) iservice.getObjectById(idActivitePrimaire, "Activite");
	}
	
	public void choisirActiviteSecondaire() {
		activiteSecondaire = (Activite) iservice.getObjectById(getIdActiviteSecondaire(), "Activite");
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
	
	public void enregistrer() throws JRException, ServletException, IOException{
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
			
			case "personne_morale": {
				personneMorale.setPromoteur(promoteur);
				personneMorale.setCodePromoteur(promoteur.getCodePromoteur());
				iservice.addObject(personneMorale);
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
			//choosedIep = (Iep) iservice.getObjectById(idIep, "Iep");
			centre.setIep(choosedIep);
			//localite = (LocaliteDImplantation) iservice.getObjectById(idLocalite, "LocaliteDImplantation");
			centre.setLocaliteDImplantation(localite);
			centre.setNature((Nature) iservice.getObjectById(idNature, "Nature"));
			if (idNatureProjet!=0) {
				centre.setNatureProjet(natureProjet);
			}
			centre.setPromoteur(promoteur);
			centre.setDroitOuvertureCentre(chemin);
			
				//Gestion de la permanence du centre
			if (etatPermanence.equals("OUI")) {
				centre.setPermanent(true);
			}else {
				centre.setPermanent(false);
			}
			
			//upload();
			chagerUtilisateur();
			
			//L'enregistreur du centre
	
			centre.setResponsableByIdResponsable(userAuthentication.getResponsable());
			centre.setDateCreation(new Date());
			genererCodeCentre();
			iservice.addObject(this.centre);
		
			//Gestion de l'animateur
			animateur.setGenre((Genre)iservice.getObjectById(idGenre, "Genre"));
			//setNiveauAnimateur((NiveauAnimateur) iservice.getObjectById(idNiveau, "NiveauAnimateur"));
			animateur.setNiveauAnimateur(niveauAnimateur);
			iservice.addObject(animateur);
			
			//Gestion de la profession
		//	activitePrimaire = (Activite) iservice.getObjectById(idActivitePrimaire, "Activite");
			profession.setActivite(activitePrimaire);
			profession.setTypeActivite((TypeActivite) iservice.getObjectById(1, "TypeActivite"));
			profession.setAnimateur(animateur);
			iservice.addObject(profession);
			if ((activiteSecondaire.getNomActivite()!= null) && (typeActiviteSecondaire.getLibelleTypeactivite() != null)) {
			//activiteSecondaire = (Activite) iservice.getObjectById(getIdActiviteSecondaire(), "Activite");
			profession.setActivite(activiteSecondaire);
			profession.setTypeActivite((TypeActivite) iservice.getObjectById(2, "TypeActivite"));
			profession.setAnimateur(animateur);
			iservice.addObject(profession);
			}
			
			//Gestion de la table Enseigner
			enseigner.setCampagne(campagneEnCours);
			//typeAlphabetisation = (TypeAlphabetisation) iservice.getObjectById(idTypeAlpha, "TypeAlphabetisation");
			enseigner.setTypeAlphabetisation(typeAlphabetisation);
			//setLangue((Langue) iservice.getObjectById(idLangue, "Langue"));
			enseigner.setLangue(langue);
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
			
			genererFicheEnreg();
			
		annuler();
		genererCodePromoteur();
		genererCodeAnimateur();
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
		personnePhysique.setNumCni(null);
		
		//Personne Morale
		personneMorale.setTelephonePersMorale(null);
		personneMorale.setRaisonSociale(null);
		personneMorale.setEmailPersMorale(null);

		
		//ONG
		ong.setNomOng(null);
		ong.setTelephoneOng(null);
		ong.setMailOng(null);
		
		//Ministère
		ministere.setNomMinistere(null);
		ministere.setTelephoneMinistere(null);
		
		//Programme
		programme.setLibelleProgramme(null);
		
		//Centre
		centre.setCodeCentre(null);
		centre.setNomCentre(null);
		centre.setAbreviationNomCentre(null);
		centre.setAdresseCentre(null);
		centre.setTelephoneCentre(null);
		centre.setMailCentre(null);
		centre.setDroitOuvertureCentre(null);
		centre.setLongitude(null);
		centre.setLatitude(null);
		
		//les combos
		setIdDepartement(0);
		setIdSousPrefecture(0);
		setIdDrena(0);
		setIdIep(0);
		setIdLocalite(0);
		setIdNatureProjet(0);
		setIdNature(0);
		setIdGenre(0);
		setIdNiveau(0);
		setIdActivitePrimaire(0);
		setIdActiviteSecondaire(0);
		setIdLangue(0);
		setIdTypeAlpha(0);
		setIdEcole(0);
		//Animateur
		animateur.setCodeAnimateur(null);
		animateur.setNomAnimateur(null);
		animateur.setPrenomAnimateur(null);
		animateur.setAdresseAnimateur(null);
		animateur.setDateNaisAnimateur(null);
		animateur.setTelephoneAnimateur(null);
		animateur.setMailAnimateur(null);
		
		//Deselectionner les composant
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
		natureProOneMenu.setDisabled(true);
		
		oneRadioPermanent.resetValue();
		oneRadioNatureLieu.resetValue();
		
		
		checkbox1.setSelected(false);
		checkbox2.setSelected(false);
		checkbox3.setSelected(false);
		
		//Vider les listes
		listSousPrefecture.clear();
		listDrena.clear();
		listIep.clear();
		listLocalite.clear();
		listEcole.clear();
	}
	
	
	public void activiverChamp() {
		switch (type_promoteur) {
		case "personne_physique": {
			this.setpGridPh(true);
			this.setEtatFileUpload(true);
			this.setEtatGraphicImage(true);
			this.setpGridOng(false);
			this.setpGridMini(false);
			this.setpGridProg(false);
			this.setpGridEntrep(false);
			
			//Pour le recap
			this.naturePromoteur = "Personne physique";
			
			this.setRecap_grid_ph(true);
			this.setRecap_grid_pm(false);
			this.setRecap_grid_mini(false);
			this.setRecap_grid_ong(false);
			this.setRecap_grid_prog(false);
			break;
		}
		
		case "personne_morale": {
			this.setpGridOng(false);
			this.setpGridMini(false);
			this.setpGridPh(false);
			this.setEtatFileUpload(false);
			this.setEtatGraphicImage(false);
			this.setpGridProg(false);
			this.setpGridEntrep(true);
			
			//Pour le recap
			this.naturePromoteur = "Prsonne morale";
			
			this.setRecap_grid_ph(false);
			this.setRecap_grid_pm(true);
			this.setRecap_grid_ong(false);
			this.setRecap_grid_mini(false);
			this.setRecap_grid_prog(false);
			break;
		}
		
		case "ong": {
			this.setpGridOng(true);
			this.setpGridMini(false);
			this.setpGridPh(false);
			this.setEtatFileUpload(false);
			this.setEtatGraphicImage(false);
			this.setpGridProg(false);
			this.setpGridEntrep(false);
			
			//Pour le recap
			this.naturePromoteur = "Ong";
			
			this.setRecap_grid_ph(false);
			this.setRecap_grid_pm(false);
			this.setRecap_grid_ong(true);
			this.setRecap_grid_mini(false);
			this.setRecap_grid_prog(false);
			
			break;
		}
		
		case "programme": {
			this.setpGridOng(false);
			this.setpGridMini(false);
			this.setpGridPh(false);
			this.setEtatFileUpload(false);
			this.setEtatGraphicImage(false);
			this.setpGridProg(true);
			this.setpGridEntrep(false);
			
			//Pour le recap
			this.naturePromoteur = "Programme";
			
			this.setRecap_grid_ph(false);
			this.setRecap_grid_pm(false);
			this.setRecap_grid_ong(false);
			this.setRecap_grid_mini(false);
			this.setRecap_grid_prog(true);
			
			break;
		}
		
		
		case "ministere": {
			this.setpGridOng(false);
			this.setpGridMini(true);
			this.setpGridPh(false);
			this.setEtatFileUpload(false);
			this.setEtatGraphicImage(false);
			this.setpGridProg(false);
			
			//Pour le recap
			this.naturePromoteur = "Ministère";
			
			this.setRecap_grid_ph(false);
			this.setRecap_grid_pm(false);
			this.setRecap_grid_ong(false);
			this.setRecap_grid_mini(true);
			this.setRecap_grid_prog(false);
			
			break;
		}
		
		}
	}
	
	
		public void chargerLocalite() {
			listLocalite.clear();
		choosedSousPrefecture = (SousPrefecture) iservice.getObjectById(idSousPrefecture, "SousPrefecture");
		for ( LocaliteDImplantation var : choosedSousPrefecture.getLocaliteDImplantations()) {
			listLocalite.add(var);
			}
			
			//=======Pour le rangement par ordre alphabétique======
					Collections.sort(listLocalite, new Comparator<LocaliteDImplantation>() {
				        @Override
				        public int compare(LocaliteDImplantation ob1, LocaliteDImplantation ob2)
				        {
				            return  ob1.getNomLocalite().compareTo(ob2.getNomLocalite());
				        }
				    });
			//========================  Fin  =======================
		}
	
	public void selectionnerLigne() {
		centre = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	
	public void actualiserLieu() {
		System.out.println("===== Methode de lieu appelée=====");
		if (oneRadioNatureLieu.getValue().equals("ecole")) {
			oneMenuEcole.setDisabled(false);
			inputLieu.setDisabled(true);
		}
		
		if (oneRadioNatureLieu.getValue().equals("autre")) {
			oneMenuEcole.setDisabled(true);
			inputLieu.setDisabled(false);
		}
	}
	
	
	public void chargerdrena() {
		
	}
	
	public void chargerCombo() {
		choosedDepartement = (Departement) iservice.getObjectById(idDepartement,"Departement");
		// Charger les sous-préfectures
		listSousPrefecture.clear();
		listDrena.clear();
		listIep.clear();
		listLocalite.clear();
		listEcole.clear();
		
		//Charger la liste des Sous-Préfecture
		for ( SousPrefecture var : choosedDepartement.getSousPrefectures()) {
			listSousPrefecture.add(var);
		}
		
		//Charger les DRENA
		choosedDrena = (Drena) iservice.getObjectById(idDrena, "Drena");
		listDrena.clear();
		for (DrenaDepartement var : choosedDepartement.getDrenaDepartements()) {
			listDrena.add(var.getDrena());
		}
		
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listSousPrefecture, new Comparator<SousPrefecture>() {
	        @Override
	        public int compare(SousPrefecture ob1, SousPrefecture ob2)
	        {
	            return  ob1.getNomSousPrefecture().compareTo(ob2.getNomSousPrefecture());
	        }
	    });
		
		//========================  Fin  =======================	
		
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listDrena, new Comparator<Drena>() {
	        @Override
	        public int compare(Drena ob1, Drena ob2)
	        {
	            return  ob1.getNomDrena().compareTo(ob2.getNomDrena());
	        }
	    });
		//========================  Fin  =======================
			
	}
	
	
	public void chargerIep() {
			listIep.clear();
			listEcole.clear();
			choosedDrena = (Drena) iservice.getObjectById(idDrena, "Drena");
			System.out.println("===== DRENA selectionné:"+choosedDrena.getNomDrena()); //Clear after;
			
			for (Iep varIep : choosedDrena.getIeps()) {
				listIep.add(varIep);
			}
			
			System.out.println("===== Taille liste IEP:"+listIep.size()); //Clear after;
	
			//=======Pour le rangement par ordre alphabétique======
			Collections.sort(listIep, new Comparator<Iep>() {
		        @Override
		        public int compare(Iep ob1, Iep ob2)
		        {
		            return  ob1.getNomIep().compareTo(ob2.getNomIep());
		        }
		    });
			//======================  Fin  =====================

	}
	
	
	public void chargerNatureProjet() {
		natureCentre = (Nature) iservice.getObjectById(idNature, "Nature");
		if (natureCentre.getLibelleNature().equalsIgnoreCase("Projet")) {
			natureProOneMenu.setDisabled(false);
		}else {
			natureProOneMenu.setDisabled(true);
			setIdNatureProjet(0);
		}
	}
	
	
	public void chargerEcole() {
		listEcole.clear();
		choosedIep = (Iep) iservice.getObjectById(idIep, "Iep");
		for (Ecole varecole : choosedIep.getEcoles()) {
			listEcole.add(varecole);
		}
		listEcole = requeteEcole.recupEcoleParIEP(idIep);
	}
	
	
	
	
	
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
	
	
	
	
	public void upload(FileUploadEvent event) {
		System.out.println("======= Upload lancée");//clean after
        FacesMessage msg = new FacesMessage("Photo validée!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	
	public void copyFile(String fileName, InputStream in){
        try {
        //lE CHEMIN
        	cheminFinal = destination + fileName;
            OutputStream out = new FileOutputStream(new File(destination + fileName));
 
            //int read = 0;
            int byteValue;
            //byte[] bytes = new byte[1024];
 
            while ((byteValue = in.read()) != -1) {
                out.write(byteValue);
            }
 
            in.close();
            out.flush();
            out.close();
            System.out.println("======= Fin copie image");//clean after
            
 // Charger le fichier dans le graphique image
            getContent();
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
       
    }
	
	
	
	
		
//Getters and setters
	
	public List<Centre> getListObject() {
		
		try {
			listObject = requeteCentre.recupCentresParIEP(serviceResponsable.getIep().getIdIep());
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			listObject = iservice.getObjects("Centre");
		}
		
		//listObject = iservice.getObjects("Centre");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Centre>() {
	        @Override
	        public int compare(Centre ob1, Centre ob2)
	        {
	 
	            return  ob1.getNomCentre().compareTo(ob2.getNomCentre());
	        }
	    });
		//========================  Fin  =======================

return listObject;

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
		return listLocalite;
	}

	public void setListLocalite(List listLocalite) {
		this.listLocalite = listLocalite;
	}

	public List getListIep() {
		//listIep = iservice.getObjects("Iep");
		return listIep;
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

	public List getListDrena() {
		return listDrena;
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

	/*
	 * public UploadedFile getFichier() { return fichier; }
	 */

	/*
	 * public void setFichier(UploadedFile fichier) { this.fichier = fichier; }
	 */
	public String getEtatPermanence() {
		return etatPermanence;
	}

	public void setEtatPermanence(String etatPermanence) {
		this.etatPermanence = etatPermanence;
	}

	public List getListEcole() {
		//return listEcole = iservice.getObjects("Ecole"); 
		return listEcole; 
	}

	public void setListEcole(List listEcole) {
		this.listEcole = listEcole;
	}

	public int getIdEcole() {
		return idEcole;
	}

	public void setIdEcole(int idEcole) {
		this.idEcole = idEcole;
	}


	public int getIdTypeAlpha() {
		return idTypeAlpha;
	}


	public void setIdTypeAlpha(int idTypeAlpha) {
		this.idTypeAlpha = idTypeAlpha;
	}


	public List<TypeAlphabetisation> getListTypeAlpha() {
		return listTypeAlpha = iservice.getObjects("TypeAlphabetisation");
	}


	public void setListTypeAlpha(List<TypeAlphabetisation> listTypeAlpha) {
		this.listTypeAlpha = listTypeAlpha;
	}


	public SelectOneMenu getNatureProOneMenu() {
		return natureProOneMenu;
	}


	public void setNatureProOneMenu(SelectOneMenu natureProOneMenu) {
		this.natureProOneMenu = natureProOneMenu;
	}


	public List<Langue> getListLangue() {
		listLangue = iservice.getObjects("Langue");
		//=======Pour le rangement par ordre alphabétique======
				Collections.sort(listLangue, new Comparator<Langue>() {
			        @Override
			        public int compare(Langue ob1, Langue ob2)
			        {
			            return  ob1.getLibLangue().compareTo(ob2.getLibLangue());
			        }
			    });
				//========================  Fin  =======================
		
		return listLangue;
	}


	public void setListLangue(List<Langue> listLangue) {
		this.listLangue = listLangue;
	}


	public int getIdLangue() {
		return idLangue;
	}


	public void setIdLangue(int idLangue) {
		this.idLangue = idLangue;
	}

	public Departement getChoosedDepartement() {
		return choosedDepartement;
	}


	public void setChoosedDepartement(Departement choosedDepartement) {
		this.choosedDepartement = choosedDepartement;
	}


	public Drena getChoosedDrena() {
		return choosedDrena;
	}


	public void setChoosedDrena(Drena choosedDrena) {
		this.choosedDrena = choosedDrena;
	}


	public Iep getChoosedIep() {
		return choosedIep;
	}


	public void setChoosedIep(Iep choosedIep) {
		this.choosedIep = choosedIep;
	}


	public List<SousPrefecture> getListSousPrefecture() {
		return listSousPrefecture;
	}


	public void setListSousPrefecture(List<SousPrefecture> listSousPrefecture) {
		this.listSousPrefecture = listSousPrefecture;
	}


	public String getLieu() {
		return lieu;
	}


	public void setLieu(String lieu) {
		this.lieu = lieu;
	}


	public int getIdSousPrefecture() {
		return idSousPrefecture;
	}


	public void setIdSousPrefecture(int idSousPrefecture) {
		this.idSousPrefecture = idSousPrefecture;
	}


	public String getEtatLieu() {
		return etatLieu;
	}


	public void setEtatLieu(String etatLieu) {
		this.etatLieu = etatLieu;
	}


	/*
	 * public String getCheminFinal() { return cheminFinal; }
	 */

	/*
	 * public void setCheminFinal(String cheminFinal) { this.cheminFinal =
	 * cheminFinal; }
	 */


	/*
	 * public StreamedContent getContent() { if ((cheminFinal.equals(""))) {
	 * setCheminFinal(destination + "avatar.jpg"); }
	 * 
	 * try {
	 * 
	 * InputStream is = new FileInputStream(cheminFinal); //is.close(); content =
	 * new DefaultStreamedContent(is);
	 * 
	 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return content; }
	 */


	/*
	 * public void setContent(StreamedContent content) { this.content = content; }
	 */


	public boolean ispGridOng() {
		return pGridOng;
	}


	public void setpGridOng(boolean pGridOng) {
		this.pGridOng = pGridOng;
	}


	public boolean ispGridPh() {
		return pGridPh;
	}


	public void setpGridPh(boolean pGridPh) {
		this.pGridPh = pGridPh;
	}


	public boolean ispGridMini() {
		return pGridMini;
	}


	public void setpGridMini(boolean pGridMini) {
		this.pGridMini = pGridMini;
	}


	public boolean ispGridProg() {
		return pGridProg;
	}


	public void setpGridProg(boolean pGridProg) {
		this.pGridProg = pGridProg;
	}


	public boolean ispGridEntrep() {
		return pGridEntrep;
	}


	public void setpGridEntrep(boolean pGridEntrep) {
		this.pGridEntrep = pGridEntrep;
	}


	public boolean isEtatGraphicImage() {
		return etatGraphicImage;
	}


	public void setEtatGraphicImage(boolean etatGraphicImage) {
		this.etatGraphicImage = etatGraphicImage;
	}


	public boolean isEtatFileUpload() {
		return etatFileUpload;
	}


	public void setEtatFileUpload(boolean etatFileUpload) {
		this.etatFileUpload = etatFileUpload;
	}


	public PersonneMorale getPersonneMorale() {
		return personneMorale;
	}


	public void setPersonneMorale(PersonneMorale personneMorale) {
		this.personneMorale = personneMorale;
	}


	public SelectOneMenu getOneMenuEcole() {
		return oneMenuEcole;
	}


	public void setOneMenuEcole(SelectOneMenu oneMenuEcole) {
		this.oneMenuEcole = oneMenuEcole;
	}


	public InputText getInputLieu() {
		return inputLieu;
	}


	public void setInputLieu(InputText inputLieu) {
		this.inputLieu = inputLieu;
	}


	public StreamedContent getContent() {
		if ((cheminFinal.equals(""))) {
    		setCheminFinal(destination + "avatar.jpg");
    	}
    	
    	try {
			 
 			InputStream is = new FileInputStream(cheminFinal);
 			//is.close();  
 			content	= new DefaultStreamedContent(is);
 			
 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
		return content;
	}


	public void setContent(StreamedContent content) {
		this.content = content;
	}


	public String getCheminFinal() {
		return cheminFinal;
	}


	public void setCheminFinal(String cheminFinal) {
		this.cheminFinal = cheminFinal;
	}


	


	public String getNaturePromoteur() {
		return naturePromoteur;
	}


	public void setNaturePromoteur(String naturePromoteur) {
		this.naturePromoteur = naturePromoteur;
	}


	public boolean isRecap_grid_ong() {
		return recap_grid_ong;
	}


	public void setRecap_grid_ong(boolean recap_grid_ong) {
		this.recap_grid_ong = recap_grid_ong;
	}


	public boolean isRecap_grid_mini() {
		return recap_grid_mini;
	}


	public void setRecap_grid_mini(boolean recap_grid_mini) {
		this.recap_grid_mini = recap_grid_mini;
	}


	public boolean isRecap_grid_prog() {
		return recap_grid_prog;
	}


	public void setRecap_grid_prog(boolean recap_grid_prog) {
		this.recap_grid_prog = recap_grid_prog;
	}


	public boolean isRecap_grid_pm() {
		return recap_grid_pm;
	}


	public void setRecap_grid_pm(boolean recap_grid_pm) {
		this.recap_grid_pm = recap_grid_pm;
	}


	public boolean isRecap_grid_ph() {
		return recap_grid_ph;
	}


	public void setRecap_grid_ph(boolean recap_grid_ph) {
		this.recap_grid_ph = recap_grid_ph;
	}


	public Nature getNatureCentre() {
		return natureCentre;
	}


	public void setNatureCentre(Nature natureCentre) {
		this.natureCentre = natureCentre;
	}


	public SelectBooleanCheckbox getCheckbox1() {
		return checkbox1;
	}


	public void setCheckbox1(SelectBooleanCheckbox checkbox1) {
		this.checkbox1 = checkbox1;
	}


	public SelectBooleanCheckbox getCheckbox2() {
		return checkbox2;
	}


	public void setCheckbox2(SelectBooleanCheckbox checkbox2) {
		this.checkbox2 = checkbox2;
	}


	public SelectBooleanCheckbox getCheckbox3() {
		return checkbox3;
	}


	public void setCheckbox3(SelectBooleanCheckbox checkbox3) {
		this.checkbox3 = checkbox3;
	}


	public SelectOneRadio getOneRadioPermanent() {
		return oneRadioPermanent;
	}


	public void setOneRadioPermanent(SelectOneRadio oneRadioPermanent) {
		this.oneRadioPermanent = oneRadioPermanent;
	}


	public LocaliteDImplantation getLocalite() {
		return localite;
	}


	public void setLocalite(LocaliteDImplantation localite) {
		this.localite = localite;
	}


	public TypeAlphabetisation getTypeAlphabetisation() {
		return typeAlphabetisation;
	}


	public void setTypeAlphabetisation(TypeAlphabetisation typeAlphabetisation) {
		this.typeAlphabetisation = typeAlphabetisation;
	}


	public Langue getLangue() {
		return langue;
	}


	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	public SousPrefecture getChoosedSousPrefecture() {
		return choosedSousPrefecture;
	}


	public void setChoosedSousPrefecture(SousPrefecture choosedSousPrefecture) {
		this.choosedSousPrefecture = choosedSousPrefecture;
	}


	public NatureProjet getNatureProjet() {
		return natureProjet;
	}


	public void setNatureProjet(NatureProjet natureProjet) {
		this.natureProjet = natureProjet;
	}


	public NiveauAnimateur getNiveauAnimateur() {
		return niveauAnimateur;
	}


	public void setNiveauAnimateur(NiveauAnimateur niveauAnimateur) {
		this.niveauAnimateur = niveauAnimateur;
	}
	
	public SelectOneRadio getOneRadioNatureLieu() {
		return oneRadioNatureLieu;
	}

	public void setOneRadioNatureLieu(SelectOneRadio oneRadioNatureLieu) {
		this.oneRadioNatureLieu = oneRadioNatureLieu;
	}

	
}
