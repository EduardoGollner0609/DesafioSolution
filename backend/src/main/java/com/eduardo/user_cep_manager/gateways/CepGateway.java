package com.eduardo.user_cep_manager.gateways;

import java.util.Optional;

import com.eduardo.user_cep_manager.entitites.Address;

public interface CepGateway {

	Optional<Address> findAddressByCep(String cep);

}
