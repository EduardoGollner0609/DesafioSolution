# 🧑‍💼 User Cep Manager

O **User Cep Manager** é uma aplicação Fullstack para cadastro e gerenciamento de usuários, com preenchimento automático de endereço via CEP. Esse projeto foi desenvolvido em uma etapa técnica de um processo seletivo.

---

## ⚙️ Tecnologias Utilizadas

### 🖥️ Frontend (React + Vite)
- React
- React Hook Form
- Zod (validações)
- React Router DOM
- Axios
- React Query (TanStack Query)

### 🌐 Backend (Spring Boot)
- Spring Boot
- Spring Data JPA
- Bean Validation (Jakarta)
- Banco H2 (ambiente de testes)
- PostgreSQL (desenvolvimento e produção)
- Swagger / OpenAPI (documentação da API)

---

## 🚀 Como Executar o Projeto Localmente

### Trazer para a máquina 
```bash
git clone https://github.com/EduardoGollner0609/UserCepManager.git
```

### Backend (Spring Boot)
Obs: dentro da pasta backend rodar (se quiser rodar com uma IDE é melhor)
```bash
./mvnw spring-boot:run
```
A API estará disponível em: http://localhost:8080
Documentação Swagger: http://localhost:8080/swagger-ui.html

### Frontend (React)
Obs: dentro da pasta backend rodar
```bash
yarn install
yarn dev
```
O Frontend estará disponível em: http://localhost:5173

---
## 📚 Endpoints da API

### 🔹 `GET /users`

- **Descrição**: Lista todos os usuários cadastrados.
- **Resposta**: 200 (Ok)
- **Corpo da Resposta**:
```json
{
  "id": 1,
  "name": "João da Silva",
  "cpf": "12345678901",
  "address": {
    "cep": "01001000",
    "street": "Praça da Sé",
    "neighborhood": "Sé",
    "city": "São Paulo",
    "state": "SP"
  }
}
```

### 🔹 `GET /users/{userId}`

- **Descrição**: Retorna usuário pelo ID.
- **Resposta**: 200 (Ok), 404 (User Not Found)
- **Corpo da Resposta quando encontrado pelo id**:
```json
{
  "id": 1,
  "name": "João da Silva",
  "cpf": "12345678901",
  "address": {
    "cep": "01001000",
    "street": "Praça da Sé",
    "neighborhood": "Sé",
    "city": "São Paulo",
    "state": "SP"
  }
}
```

### 🔹 `POST /users`

- **Descrição**: Cria um novo usuário. O endereço é buscado automaticamente com base no CEP informado.

- **Corpo da Requisição**:
```json
{
  "name": "João da Silva",
  "cpf": "12345678901",
  "cep": "01001000"
}
```
- **Corpo da Resposta quando criado**:
```json
{
  "id": 1,
  "name": "João da Silva",
  "cpf": "12345678901",
  "address": {
    "cep": "01001000",
    "street": "Praça da Sé",
    "neighborhood": "Sé",
    "city": "São Paulo",
    "state": "SP"
  }
}
```


- **Resposta**: 201 (Created), 422 (Unprocessable Entity), 404 (Cep Not Found)

### 🔹 `PUT /users/{userId}`

- **Descrição**: Atualiza os dados de um usuário existente pelo ID. O endereço é atualizado com base no novo CEP informado.

- **Resposta**: 201 (Created), 422 (Unprocessable Entity), 404 (Cep Not Found)
- **Corpo da Requisição**:
```json
{
  "name": "Maria Souza",
  "cpf": "98765432100",
  "cep": "04003000"
}
```
- **Corpo da Resposta quando atualizado**:
```json
{
  "id": 1,
  "name": "João da Silva",
  "cpf": "12345678901",
  "address": {
    "cep": "01001000",
    "street": "Praça da Sé",
    "neighborhood": "Sé",
    "city": "São Paulo",
    "state": "SP"
  }
}
```


### 🔹 `DELETE /users/{userId}`

- **Descrição**: Remove um usuário do sistema pelo ID.

- **Resposta**: 204 (No content), 404 (User Not Found)
