package com.scc.daemon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ods_geniteurs")
public class OdsParent{

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "nom")
	private String name;

	@Column(name = "affixe")
	private String affixe;

	@Column(name = "on_suffixe")
	private String onSuffixe;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getAffixe() { return affixe; }
	public void setAffixe(String affixe) { this.affixe = affixe; }

	public String getOnSuffixe() { return onSuffixe; }
	public void setOnSuffixe(String onSuffixe) { this.onSuffixe = onSuffixe; }

	public OdsParent withId(int id){ this.setId( id ); return this; }
	public OdsParent withName(String name){ this.setName(name); return this; }
	public OdsParent withAffixe(String affixe){ this.setAffixe(affixe); return this; }
	public OdsParent withOnSuffixe(String onSuffixe){ this.setOnSuffixe(onSuffixe); return this; }
	
}
