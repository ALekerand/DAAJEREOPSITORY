package com.daaje.model;
// Generated 2 nov. 2023, 15:04:15 by Hibernate Tools 4.3.6.Final

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
 * Nature generated by hbm2java
 */
@Entity
@Table(name = "nature", catalog = "sygca_bd")
public class Nature implements java.io.Serializable {

	private Integer idNature;
	private String codeNature;
	private String libelleNature;
	private Set<Centre> centres = new HashSet<Centre>(0);

	public Nature() {
	}

	public Nature(String codeNature, String libelleNature, Set<Centre> centres) {
		this.codeNature = codeNature;
		this.libelleNature = libelleNature;
		this.centres = centres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_NATURE", unique = true, nullable = false)
	public Integer getIdNature() {
		return this.idNature;
	}

	public void setIdNature(Integer idNature) {
		this.idNature = idNature;
	}

	@Column(name = "CODE_NATURE", length = 10)
	public String getCodeNature() {
		return this.codeNature;
	}

	public void setCodeNature(String codeNature) {
		this.codeNature = codeNature;
	}

	@Column(name = "LIBELLE_NATURE", length = 30)
	public String getLibelleNature() {
		return this.libelleNature;
	}

	public void setLibelleNature(String libelleNature) {
		this.libelleNature = libelleNature;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nature")
	public Set<Centre> getCentres() {
		return this.centres;
	}

	public void setCentres(Set<Centre> centres) {
		this.centres = centres;
	}

}
