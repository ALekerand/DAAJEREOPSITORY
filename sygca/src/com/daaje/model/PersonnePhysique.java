package com.daaje.model;
// Generated 20 sept. 2023, 12:20:34 by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PersonnePhysique generated by hbm2java
 */
@Entity
@Table(name = "personne_physique", catalog = "sygca_bd")
public class PersonnePhysique implements java.io.Serializable {

	private int idPromoteur;
	private Promoteur promoteur;
	private String codePromoteur;
	private String nomPersonne;
	private String prenomsPersonne;
	private String telephonePersonne;
	private String mailPersonne;

	public PersonnePhysique() {
	}

	public PersonnePhysique(Promoteur promoteur) {
		this.promoteur = promoteur;
	}

	public PersonnePhysique(Promoteur promoteur, String codePromoteur, String nomPersonne, String prenomsPersonne,
			String telephonePersonne, String mailPersonne) {
		this.promoteur = promoteur;
		this.codePromoteur = codePromoteur;
		this.nomPersonne = nomPersonne;
		this.prenomsPersonne = prenomsPersonne;
		this.telephonePersonne = telephonePersonne;
		this.mailPersonne = mailPersonne;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "promoteur"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "ID_PROMOTEUR", unique = true, nullable = false)
	public int getIdPromoteur() {
		return this.idPromoteur;
	}

	public void setIdPromoteur(int idPromoteur) {
		this.idPromoteur = idPromoteur;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Promoteur getPromoteur() {
		return this.promoteur;
	}

	public void setPromoteur(Promoteur promoteur) {
		this.promoteur = promoteur;
	}

	@Column(name = "CODE_PROMOTEUR", length = 10)
	public String getCodePromoteur() {
		return this.codePromoteur;
	}

	public void setCodePromoteur(String codePromoteur) {
		this.codePromoteur = codePromoteur;
	}

	@Column(name = "NOM_PERSONNE", length = 25)
	public String getNomPersonne() {
		return this.nomPersonne;
	}

	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}

	@Column(name = "PRENOMS_PERSONNE", length = 50)
	public String getPrenomsPersonne() {
		return this.prenomsPersonne;
	}

	public void setPrenomsPersonne(String prenomsPersonne) {
		this.prenomsPersonne = prenomsPersonne;
	}

	@Column(name = "TELEPHONE_PERSONNE", length = 15)
	public String getTelephonePersonne() {
		return this.telephonePersonne;
	}

	public void setTelephonePersonne(String telephonePersonne) {
		this.telephonePersonne = telephonePersonne;
	}

	@Column(name = "MAIL_PERSONNE", length = 30)
	public String getMailPersonne() {
		return this.mailPersonne;
	}

	public void setMailPersonne(String mailPersonne) {
		this.mailPersonne = mailPersonne;
	}

}
