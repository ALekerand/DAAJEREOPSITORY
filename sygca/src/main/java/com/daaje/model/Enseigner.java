package com.daaje.model;
// Generated 8 avr. 2024, 14:52:14 by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Enseigner generated by hbm2java
 */
@Entity
@Table(name = "enseigner", catalog = "sygca_bd")
public class Enseigner implements java.io.Serializable {

	private Integer idEnseigner;
	private Animateur animateur;
	private Campagne campagne;
	private Centre centre;
	private Langue langue;
	private NiveauFormation niveauFormation;
	private TypeAlphabetisation typeAlphabetisation;
	private String codeEnseigner;

	public Enseigner() {
	}

	public Enseigner(Animateur animateur, Campagne campagne, Centre centre, Langue langue,
			NiveauFormation niveauFormation, TypeAlphabetisation typeAlphabetisation) {
		this.animateur = animateur;
		this.campagne = campagne;
		this.centre = centre;
		this.langue = langue;
		this.niveauFormation = niveauFormation;
		this.typeAlphabetisation = typeAlphabetisation;
	}

	public Enseigner(Animateur animateur, Campagne campagne, Centre centre, Langue langue,
			NiveauFormation niveauFormation, TypeAlphabetisation typeAlphabetisation, String codeEnseigner) {
		this.animateur = animateur;
		this.campagne = campagne;
		this.centre = centre;
		this.langue = langue;
		this.niveauFormation = niveauFormation;
		this.typeAlphabetisation = typeAlphabetisation;
		this.codeEnseigner = codeEnseigner;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_ENSEIGNER", unique = true, nullable = false)
	public Integer getIdEnseigner() {
		return this.idEnseigner;
	}

	public void setIdEnseigner(Integer idEnseigner) {
		this.idEnseigner = idEnseigner;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ANIMATEUR", nullable = false)
	public Animateur getAnimateur() {
		return this.animateur;
	}

	public void setAnimateur(Animateur animateur) {
		this.animateur = animateur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CAMPAGNE", nullable = false)
	public Campagne getCampagne() {
		return this.campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CENTRE", nullable = false)
	public Centre getCentre() {
		return this.centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LANGUE", nullable = false)
	public Langue getLangue() {
		return this.langue;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NIVEAU_FORMATION", nullable = false)
	public NiveauFormation getNiveauFormation() {
		return this.niveauFormation;
	}

	public void setNiveauFormation(NiveauFormation niveauFormation) {
		this.niveauFormation = niveauFormation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TYPE_ALPHA", nullable = false)
	public TypeAlphabetisation getTypeAlphabetisation() {
		return this.typeAlphabetisation;
	}

	public void setTypeAlphabetisation(TypeAlphabetisation typeAlphabetisation) {
		this.typeAlphabetisation = typeAlphabetisation;
	}

	@Column(name = "CODE_ENSEIGNER", length = 10)
	public String getCodeEnseigner() {
		return this.codeEnseigner;
	}

	public void setCodeEnseigner(String codeEnseigner) {
		this.codeEnseigner = codeEnseigner;
	}

}
