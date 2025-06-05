import type { AddressResponseDTO } from "./address";

export type UserRequestDTO = {
  name: string;
  cpf: string;
  cep: string;
};

export type UserResponseDTO = {
  id: number;
  name: string;
  cpf: string;
  address: AddressResponseDTO;
};
