package com.daaje.model;
// Generated 8 avr. 2024, 14:52:14 by Hibernate Tools 4.3.6.Final

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
 * NiveauAnimateur generated by hbm2java
 */
@Entity
@Table(name = "niveau_animateur", catalog = "sygca_bd")
public class NiveauAnimateur implements java.io.Serializable {

	private Integer idNiveau;
	private String codeNiveau;
	private String nomNiveau;
	private Set<Animateur> animateurs = new HashSet<Animateur>(0);

	public NiveauAnimateur() {
	}

	public NiveauAnimateur(String codeNiveau, String nomNiveau, Set<Animateur> animateurs) {
		this.codeNiveau = codeNiveau;
		this.nomNiveau = nomNiveau;
		this.animateurs = animateurs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_NIVEAU", unique = true, nullable = false)
	public Integer getIdNiveau() {
		return this.idNiveau;
	}

	public void setIdNiveau(Integer idNiveau) {
		this.idNiveau = idNiveau;
	}

	@Column(name = "CODE_NIVEAU", length = 10)
	public String getCodeNiveau() {
		return this.codeNiveau;
	}

	public void setCodeNiveau(String codeNiveau) {
		this.codeNiveau = codeNiveau;
	}

	@Column(name = "NOM_NIVEAU", length = 30)
	public String getNomNiveau() {
		return this.nomNiveau;
	}

	public void setNomNiveau(String nomNiveau) {
		this.nomNiveau = nomNiveau;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "niveauAnimateur")
	public Set<Animateur> getAnimateurs() {
		return this.animateurs;
	}

	public void setAnimateurs(Set<Animateur> animateurs) {
		this.animateurs = animateurs;
	}

}
