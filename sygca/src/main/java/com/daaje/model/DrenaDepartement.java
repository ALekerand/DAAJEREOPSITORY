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
 * DrenaDepartement generated by hbm2java
 */
@Entity
@Table(name = "drena_departement", catalog = "sygca_bd")
public class DrenaDepartement implements java.io.Serializable {

	private Integer idDrenaDepart;
	private Departement departement;
	private Drena drena;

	public DrenaDepartement() {
	}

	public DrenaDepartement(Departement departement, Drena drena) {
		this.departement = departement;
		this.drena = drena;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_DRENA_DEPART", unique = true, nullable = false)
	public Integer getIdDrenaDepart() {
		return this.idDrenaDepart;
	}

	public void setIdDrenaDepart(Integer idDrenaDepart) {
		this.idDrenaDepart = idDrenaDepart;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DEPARTEMENT", nullable = false)
	public Departement getDepartement() {
		return this.departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DRENA", nullable = false)
	public Drena getDrena() {
		return this.drena;
	}

	public void setDrena(Drena drena) {
		this.drena = drena;
	}

}
