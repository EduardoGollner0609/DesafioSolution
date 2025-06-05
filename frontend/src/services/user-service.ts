import axios from "axios";
import { BASE_URL } from "../utils/system";
import type { UserRequestDTO } from "../models/user";

type UpdateUserParams = {
  id: number;
  requestDTO: UserRequestDTO;
};

const url = BASE_URL + "/users";

export function create(requestDTO: UserRequestDTO) {
  return axios.post(url, requestDTO);
}

export function findById(id: number) {
  return axios.get(`${url}/${id}`);
}

export async function findAll() {
  return await axios.get(url);
}

export function update({ id, requestDTO }: UpdateUserParams) {
  return axios.put(`${url}/${id}`, requestDTO);
}

export function deleteById(id: number) {
  return axios.delete(`${url}/${id}`);
}
