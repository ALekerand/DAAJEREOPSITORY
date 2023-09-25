package com.daaje.model;
// Generated 25 sept. 2023, 12:18:35 by Hibernate Tools 4.3.6.Final

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
	private NiveauFormation niveauFormation;
	private String codeEnseigner;
	private Set<Centre> centres = new HashSet<Centre>(0);

	public Enseigner() {
	}

	public Enseigner(Animateur animateur, Campagne campagne, Centre centre, NiveauFormation niveauFormation) {
		this.animateur = animateur;
		this.campagne = campagne;
		this.centre = centre;
		this.niveauFormation = niveauFormation;
	}

	public Enseigner(Animateur animateur, Campagne campagne, Centre centre, NiveauFormation niveauFormation,
			String codeEnseigner, Set<Centre> centres) {
		this.animateur = animateur;
		this.campagne = campagne;
		this.centre = centre;
		this.niveauFormation = niveauFormation;
		this.codeEnseigner = codeEnseigner;
		this.centres = centres;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CENTRE", nullable = false)
	public Centre getCentre() {
		return this.centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NIVEAU_FORMATION", nullable = false)
	public NiveauFormation getNiveauFormation() {
		return this.niveauFormation;
	}

	public void setNiveauFormation(NiveauFormation niveauFormation) {
		this.niveauFormation = niveauFormation;
	}

	@Column(name = "CODE_ENSEIGNER", length = 10)
	public String getCodeEnseigner() {
		return this.codeEnseigner;
	}

	public void setCodeEnseigner(String codeEnseigner) {
		this.codeEnseigner = codeEnseigner;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enseigner")
	public Set<Centre> getCentres() {
		return this.centres;
	}

	public void setCentres(Set<Centre> centres) {
		this.centres = centres;
	}

}
