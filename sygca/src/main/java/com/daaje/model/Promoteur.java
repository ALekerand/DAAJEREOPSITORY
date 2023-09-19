package com.daaje.model;
// Generated 19 sept. 2023, 10:50:45 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Promoteur generated by hbm2java
 */
@Entity
@Table(name = "promoteur", catalog = "sygca_bd")
public class Promoteur implements java.io.Serializable {

	private Integer idPromoteur;
	private String codePromoteur;
	private Set<Centre> centres = new HashSet<Centre>(0);

	public Promoteur() {
	}

	public Promoteur(String codePromoteur, Set<Centre> centres) {
		this.codePromoteur = codePromoteur;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "promoteur")
	public Set<Centre> getCentres() {
		return this.centres;
	}

	public void setCentres(Set<Centre> centres) {
		this.centres = centres;
	}

}
