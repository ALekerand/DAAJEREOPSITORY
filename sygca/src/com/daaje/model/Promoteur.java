package com.daaje.model;
// Generated 20 sept. 2023, 12:20:34 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Promoteur generated by hbm2java
 */
@Entity
@Table(name = "promoteur", catalog = "sygca_bd")
public class Promoteur implements java.io.Serializable {

	private Integer idPromoteur;
	private String codePromoteur;
	private Ong ong;
	private Ministere ministere;
	private Progamme progamme;
	private PersonnePhysique personnePhysique;
	private Set<Centre> centres = new HashSet<Centre>(0);

	public Promoteur() {
	}

	public Promoteur(String codePromoteur, Ong ong, Ministere ministere, Progamme progamme,
			PersonnePhysique personnePhysique, Set<Centre> centres) {
		this.codePromoteur = codePromoteur;
		this.ong = ong;
		this.ministere = ministere;
		this.progamme = progamme;
		this.personnePhysique = personnePhysique;
		this.centres = centres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_PROMOTEUR", unique = true, nullable = false)
	public Integer getIdPromoteur() {
		return this.idPromoteur;
	}

	public void setIdPromoteur(Integer idPromoteur) {
		this.idPromoteur = idPromoteur;
	}

	@Column(name = "CODE_PROMOTEUR", length = 10)
	public String getCodePromoteur() {
		return this.codePromoteur;
	}

	public void setCodePromoteur(String codePromoteur) {
		this.codePromoteur = codePromoteur;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "promoteur")
	public Ong getOng() {
		return this.ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "promoteur")
	public Ministere getMinistere() {
		return this.ministere;
	}

	public void setMinistere(Ministere ministere) {
		this.ministere = ministere;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "promoteur")
	public Progamme getProgamme() {
		return this.progamme;
	}

	public void setProgamme(Progamme progamme) {
		this.progamme = progamme;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "promoteur")
	public PersonnePhysique getPersonnePhysique() {
		return this.personnePhysique;
	}

	public void setPersonnePhysique(PersonnePhysique personnePhysique) {
		this.personnePhysique = personnePhysique;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "promoteur")
	public Set<Centre> getCentres() {
		return this.centres;
	}

	public void setCentres(Set<Centre> centres) {
		this.centres = centres;
	}

}