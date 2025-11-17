# -Sistema-CRUD-com-Testes-Automatizados-Mockito-JMeter-

# CRUD API de Produtos

Esta é uma API REST desenvolvida em **Spring Boot** para realizar operações de cadastro de produtos.  
O projeto segue boas práticas de arquitetura em camadas, contendo **Controller**, **Service**, **Model** e **Exception Handling**.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Web**
- **Maven**
- **H2 Database** (em memória)
- **Spring DevTools**

---

## 📌 Funcionalidades da API

A API permite:

- ✔️ Criar produto  
- ✔️ Listar todos os produtos  
- ✔️ Buscar produto por ID  
- ✔️ Atualizar produto  
- ✔️ Deletar produto  

---

## 📂 Estrutura do Projeto

src/
├── main/
│ ├── java/com/renata/crudapi/
│ │ ├── controller/ → Endpoints REST
│ │ ├── service/ → Regras de negócio
│ │ ├── model/ → Entidade Produto
│ │ └── exception/ → Erros personalizados
│ └── resources/
│ ├── application.properties
│ └── data.sql (opcional)
└── test/
└── java/ (testes unitários)

yaml
Copiar código

---

## ▶️ Como executar o projeto

No terminal, dentro da pasta do projeto, execute:

```bash
mvn spring-boot:run
A API iniciará em:

arduino
Copiar código
http://localhost:8080
🛠️ Endpoints Disponíveis
➤ Criar produto
bash
Copiar código
POST /api/produtos
Exemplo de JSON:

json
Copiar código
{
  "nome": "Monitor",
  "preco": 1200.00,
  "quantidade": 10
}
➤ Listar produtos
bash
Copiar código
GET /api/produtos
➤ Buscar por ID
bash
Copiar código
GET /api/produtos/{id}
➤ Atualizar produto
bash
Copiar código
PUT /api/produtos/{id}
➤ Deletar produto
bash
Copiar código
DELETE /api/produtos/{id}
🧱 Modelo Produto
java
Copiar código
public class Produto {
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
🧑‍💻 Autora
Renata Gabrielle Martins da Silva
Desenvolvedora em formação — Sistemas de Informação
