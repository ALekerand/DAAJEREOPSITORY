package com.daaje.model;
// Generated 13 f�vr. 2024, 11:13:45 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ServiceResponsable generated by hbm2java
 */
@Entity
@Table(name = "service_responsable", catalog = "sygca_bd")
public class ServiceResponsable implements java.io.Serializable {

	private Integer idServiceDrena;
	private Drena drena;
	private Iep iep;
	private Responsable responsable;
	private String codeService;
	private Date dateArrivee;
	private Date dateDepart;

	public ServiceResponsable() {
	}

	public ServiceResponsable(Drena drena, Iep iep, Responsable responsable, String codeService, Date dateArrivee,
			Date dateDepart) {
		this.drena = drena;
		this.iep = iep;
		this.responsable = responsable;
		this.codeService = codeService;
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_SERVICE_DRENA", unique = true, nullable = false)
	public Integer getIdServiceDrena() {
		return this.idServiceDrena;
	}

	public void setIdServiceDrena(Integer idServiceDrena) {
		this.idServiceDrena = idServiceDrena;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DRENA")
	public Drena getDrena() {
		return this.drena;
	}

	public void setDrena(Drena drena) {
		this.drena = drena;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_IEP")
	public Iep getIep() {
		return this.iep;
	}

	public void setIep(Iep iep) {
		this.iep = iep;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RESPONSABLE")
	public Responsable getResponsable() {
		return this.responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	@Column(name = "CODE_SERVICE", length = 10)
	public String getCodeService() {
		return this.codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ARRIVEE", length = 10)
	public Date getDateArrivee() {
		return this.dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DEPART", length = 10)
	public Date getDateDepart() {
		return this.dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

}
