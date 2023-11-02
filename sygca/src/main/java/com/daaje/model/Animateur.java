package com.daaje.model;
// Generated 2 nov. 2023, 15:04:15 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Animateur generated by hbm2java
 */
@Entity
@Table(name = "animateur", catalog = "sygca_bd")
public class Animateur implements java.io.Serializable {

	private Integer idAnimateur;
	private Genre genre;
	private NiveauAnimateur niveauAnimateur;
	private String codeAnimateur;
	private String nomAnimateur;
	private String prenomAnimateur;
	private Date dateNaisAnimateur;
	private String telephoneAnimateur;
	private String adresseAnimateur;
	private String mailAnimateur;
	private Set<Profession> professions = new HashSet<Profession>(0);
	private Set<Enseigner> enseigners = new HashSet<Enseigner>(0);

	public Animateur() {
	}

	public Animateur(Genre genre, NiveauAnimateur niveauAnimateur) {
		this.genre = genre;
		this.niveauAnimateur = niveauAnimateur;
	}

	public Animateur(Genre genre, NiveauAnimateur niveauAnimateur, String codeAnimateur, String nomAnimateur,
			String prenomAnimateur, Date dateNaisAnimateur, String telephoneAnimateur, String adresseAnimateur,
			String mailAnimateur, Set<Profession> professions, Set<Enseigner> enseigners) {
		this.genre = genre;
		this.niveauAnimateur = niveauAnimateur;
		this.codeAnimateur = codeAnimateur;
		this.nomAnimateur = nomAnimateur;
		this.prenomAnimateur = prenomAnimateur;
		this.dateNaisAnimateur = dateNaisAnimateur;
		this.telephoneAnimateur = telephoneAnimateur;
		this.adresseAnimateur = adresseAnimateur;
		this.mailAnimateur = mailAnimateur;
		this.professions = professions;
		this.enseigners = enseigners;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_ANIMATEUR", unique = true, nullable = false)
	public Integer getIdAnimateur() {
		return this.idAnimateur;
	}

	public void setIdAnimateur(Integer idAnimateur) {
		this.idAnimateur = idAnimateur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_GENRE", nullable = false)
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NIVEAU", nullable = false)
	public NiveauAnimateur getNiveauAnimateur() {
		return this.niveauAnimateur;
	}

	public void setNiveauAnimateur(NiveauAnimateur niveauAnimateur) {
		this.niveauAnimateur = niveauAnimateur;
	}

	@Column(name = "CODE_ANIMATEUR", length = 10)
	public String getCodeAnimateur() {
		return this.codeAnimateur;
	}

	public void setCodeAnimateur(String codeAnimateur) {
		this.codeAnimateur = codeAnimateur;
	}

	@Column(name = "NOM_ANIMATEUR", length = 30)
	public String getNomAnimateur() {
		return this.nomAnimateur;
	}

	public void setNomAnimateur(String nomAnimateur) {
		this.nomAnimateur = nomAnimateur;
	}

	@Column(name = "PRENOM_ANIMATEUR", length = 30)
	public String getPrenomAnimateur() {
		return this.prenomAnimateur;
	}

	public void setPrenomAnimateur(String prenomAnimateur) {
		this.prenomAnimateur = prenomAnimateur;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_NAIS_ANIMATEUR", length = 10)
	public Date getDateNaisAnimateur() {
		return this.dateNaisAnimateur;
	}

	public void setDateNaisAnimateur(Date dateNaisAnimateur) {
		this.dateNaisAnimateur = dateNaisAnimateur;
	}

	@Column(name = "TELEPHONE_ANIMATEUR", length = 10)
	public String getTelephoneAnimateur() {
		return this.telephoneAnimateur;
	}

	public void setTelephoneAnimateur(String telephoneAnimateur) {
		this.telephoneAnimateur = telephoneAnimateur;
	}

	@Column(name = "ADRESSE_ANIMATEUR", length = 20)
	public String getAdresseAnimateur() {
		return this.adresseAnimateur;
	}

	public void setAdresseAnimateur(String adresseAnimateur) {
		this.adresseAnimateur = adresseAnimateur;
	}

	@Column(name = "MAIL_ANIMATEUR", length = 30)
	public String getMailAnimateur() {
		return this.mailAnimateur;
	}

	public void setMailAnimateur(String mailAnimateur) {
		this.mailAnimateur = mailAnimateur;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "animateur")
	public Set<Profession> getProfessions() {
		return this.professions;
	}

	public void setProfessions(Set<Profession> professions) {
		this.professions = professions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "animateur")
	public Set<Enseigner> getEnseigners() {
		return this.enseigners;
	}

	public void setEnseigners(Set<Enseigner> enseigners) {
		this.enseigners = enseigners;
	}

}
