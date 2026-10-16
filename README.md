# Cadastro de Colaboradores

Projeto simples de portfólio, feito para praticar **Java, Spring Boot, SQL, HTML, CSS e JavaScript** juntos em uma aplicação completa.

É um CRUD (criar, listar, editar e excluir) de colaboradores ligados a departamentos, com uma API REST no backend e uma página web que consome essa API.

> Projeto de estudo: o objetivo é mostrar o fluxo inteiro de uma aplicação web, do banco de dados até a tela, de forma direta e sem complexidade extra.

---

## Tecnologias

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 3 (Web, Data JPA, Validation) |
| Banco de dados | H2 (em memória) |
| ORM | Hibernate / JPA |
| Frontend | HTML, CSS e JavaScript puro (`fetch`) |
| Build | Maven |

---

## Funcionalidades

- Cadastrar, listar, editar e excluir colaboradores
- Vincular cada colaborador a um departamento (chave estrangeira)
- Filtrar colaboradores por cargo
- Validação dos campos obrigatórios (nome, cargo e data de admissão)
- Respostas de erro claras em JSON (`404` para não encontrado e `400` para dados inválidos)
- Dados de exemplo carregados automaticamente ao iniciar (`data.sql`)

---

## Como rodar

**Pré-requisito:** JDK 21 ou superior.

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Depois acesse:

| Endereço | O que é |
|---|---|
| http://localhost:8080 | Tela do cadastro |
| http://localhost:8080/api/colaboradores | API em JSON |
| http://localhost:8080/h2-console | Console do banco (JDBC URL `jdbc:h2:mem:colaboradores`, usuário `sa`, sem senha) |

---

## API

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/colaboradores` | Lista todos |
| GET | `/api/colaboradores?cargo=analista` | Filtra por cargo |
| GET | `/api/colaboradores/{id}` | Busca um colaborador |
| POST | `/api/colaboradores` | Cria um colaborador |
| PUT | `/api/colaboradores/{id}` | Atualiza um colaborador |
| DELETE | `/api/colaboradores/{id}` | Exclui um colaborador |
| GET | `/api/departamentos` | Lista os departamentos |

Exemplo de corpo para `POST` e `PUT`:

```json
{
  "nome": "Ana Souza",
  "cargo": "Analista de Sistemas",
  "dataAdmissao": "2023-03-15",
  "departamento": { "id": 1 }
}
```

---

## Estrutura

O backend segue a arquitetura em camadas usada em projetos Spring:

```
src/main/java/com/rafael/colaboradores/
├── model/        Entidades que viram tabelas (Colaborador, Departamento)
├── repository/   Acesso ao banco com Spring Data JPA
├── service/      Regras de negócio
├── controller/   Endpoints REST
└── exception/    Exceção própria e tratamento central de erros

src/main/resources/
├── application.properties   Configuração do banco
├── data.sql                 Dados iniciais
└── static/                  Tela web (index.html, style.css, app.js)
```

Fluxo de uma requisição:

```
Navegador (app.js) → Controller → Service → Repository → Banco H2
```

---

## O que foi praticado

- **Java e POO:** encapsulamento, herança (exceção própria), interfaces, composição entre classes
- **Spring Boot:** injeção de dependência, camadas, API REST, validação, `@RestControllerAdvice`
- **SQL e JPA:** mapeamento de entidades, chave primária e estrangeira, `INSERT`, `JOIN`, consulta derivada do nome do método
- **Web:** HTML, CSS e JavaScript consumindo uma API com `fetch`, `async/await` e JSON
- **Git:** um commit por etapa do desenvolvimento

---

Desenvolvido por **Rafael Barros Raiser**.
