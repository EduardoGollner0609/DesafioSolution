package com.eduardo.user_cep_manager.dtos.responses;

import com.eduardo.user_cep_manager.entitites.Address;

public record AddressResponseDTO(String cep, String street, String neighborhood, String city, String state) {

	public AddressResponseDTO(Address address) {
		this(
				address.getCep(), 
				address.getStreet(), 
				address.getNeighborhood(), 
				address.getCity(), 
				address.getState()
				);
	}
}
