📚 Biblioteca Digital

Sistema de consulta e gerenciamento de livros integrado à API Gutendex, desenvolvido com Spring Boot, persistindo dados em banco relacional utilizando JPA/Hibernate.

🔗 Repositório:
https://github.com/Giscelmo/biblioteca-digital.git

🚀 Sobre o Projeto

A Biblioteca Digital é uma aplicação Java backend que permite buscar livros através da API pública Gutendex e armazená-los automaticamente em banco de dados relacional.

O sistema evita duplicidade de livros e autores, garantindo integridade dos dados.

🎯 Funcionalidades

🔎 Buscar livro por nome (integração com API externa)

💾 Salvar livro automaticamente no banco

👨‍💼 Salvar autores relacionados automaticamente

📖 Listar livros registrados

🧑‍🎓 Listar autores registrados

📅 Listar autores vivos em determinado ano

🌎 Listar livros por idioma

🚫 Evita duplicidade de livros e autores

🏗️ Arquitetura do Projeto

O projeto está organizado em camadas:

principal  → Camada de interação (menu e fluxo principal)
service    → Consumo da API e conversão de dados
modelo     → Entidades JPA e DTOs (Records)
repository → Interfaces de persistência (Spring Data JPA)
🛠️ Tecnologias Utilizadas

Java 17+

Spring Boot

Spring Data JPA

Hibernate

Maven

Banco relacional (PostgreSQL ou H2)

API Gutendex

Jackson (JSON → Objeto)

🔗 Integração com API Externa

A aplicação consome dados da API pública:

https://gutendex.com/books?search=nome-do-livro

Utilizando:

UriComponentsBuilder

Cliente HTTP

Conversão automática com Jackson

🗃️ Modelo de Dados
📘 Livro

id

titulo (único)

idioma

download

relacionamento ManyToMany com Autor

👤 Autor

id

nomeAutor (único)

dataNascimento

dataFalecimento

relacionamento ManyToMany com Livro

📋 Menu da Aplicação
1 - Buscar Livro
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros por idioma
0 - Sair
🧠 Conceitos Aplicados

Injeção de Dependência

JPQL

Derived Queries (Spring Data)

Relacionamento ManyToMany

Stream API

Records para DTO

Tratamento de exceções

Boas práticas de organização de código

▶️ Como Executar o Projeto
1️⃣ Clonar o repositório
git clone https://github.com/Giscelmo/biblioteca-digital.git
2️⃣ Configurar o application.properties

Configure seu banco de dados no arquivo:

src/main/resources/application.properties

Exemplo:

spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Executar a aplicação
mvn spring-boot:run
📌 Melhorias Futuras

Paginação com Pageable

Transformar em API REST

Dockerização

Testes unitários

Swagger/OpenAPI

Deploy em nuvem

👨‍💻 Autor

Desenvolvido por Giscelmo Costa

💼 Sobre o Projeto

Este projeto demonstra conhecimento em:

Desenvolvimento backend com Spring Boot

Integração com API externa

Persistência relacional com JPA

Modelagem de entidades

Boas práticas de organização de código