# 🚀 Sistema de Controle de Ponto

Bem-vindo ao **Sistema de Controle de Ponto**! Este projeto é uma solução completa Fullstack para gerenciamento de check-in e check-out de funcionários, permitindo que gestores visualizem as horas trabalhadas.

---

## 🛠️ Tecnologias Utilizadas

### **Back-end**
*   ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) **Java 17**
*   ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) **Spring Boot**
*   ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) **PostgreSQL**
*   ![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white) **SpringDoc OpenAPI**

### **Front-end**
*   ![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB) **React**
*   ![Vite](https://img.shields.io/badge/vite-%23646CFF.svg?style=for-the-badge&logo=vite&logoColor=white) **Vite**
*   ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) **Docker & Docker Compose**

---

## 🚀 Como Executar o Projeto

Para rodar a aplicação completa (Back-end, Front-end e Banco de Dados), certifique-se de ter o **Docker** e **Docker Compose** instalados.

1.  Clone o repositório (se ainda não o fez).
2.  Abra o terminal na pasta raiz do projeto (`Projeto1`).
3.  Execute o comando:

```bash
docker-compose up --build
```

Aguarde alguns instantes até que todos os containers estejam de pé e rodando.

---

## 🔗 Links de Acesso

Após iniciar os containers, você pode acessar os serviços através dos seguintes links:

| Serviço | Descrição | URL |
| :--- | :--- | :--- |
| **Front-end** | Aplicação Web (React) | [http://localhost:3000](http://localhost:3000) |
| **Back-end API** | API REST (Spring Boot) | [http://localhost:8080](http://localhost:8080) |
| **Swagger UI** | Documentação da API | [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) |

---

## 📂 Estrutura do Projeto

*   **`Back-end/`**: Código fonte da API em Java/Spring Boot.
*   **`Front-end/`**: Código fonte da interface em React.
*   **`docker-compose.yml`**: Arquivo de orquestração dos containers.

---

## 📝 Notas Adicionais

*   O banco de dados PostgreSQL roda na porta **5432**.
*   As credenciais de banco de dados estão configuradas no `docker-compose.yml`.

Desenvolvido com ❤️ para o Desafio.
