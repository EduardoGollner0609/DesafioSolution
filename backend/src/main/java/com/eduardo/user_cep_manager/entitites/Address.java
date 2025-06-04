package com.eduardo.user_cep_manager.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(nullable = false)
	private String cep;

	@Column(name = "logradouro", nullable = false)
	private String street;

	@Column(name = "bairro", nullable = false)
	private String neighborhood;

	@Column(name = "cidade", nullable = false)
	private String city;

	@Column(name = "estado", nullable = false)
	private String state;

	public Address() {
	}

	public Address(String cep, String street, String neighborhood, String city, String state) {
		super();
		this.cep = cep;
		this.street = street;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}