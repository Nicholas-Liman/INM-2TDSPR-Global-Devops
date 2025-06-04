# 🔥 AshBoard – Cadastro de Alertas de Desastres

## 📌 Sobre o Projeto

Este projeto foi desenvolvido como parte da disciplina **Java Advanced**, com foco em **Spring Boot**, **MVC** e **implantação em nuvem (Azure)**, pela equipe N.I.M.:

👥 **Integrantes**

* 🏆 **Igor Gabriel Marcondes** – RM553544
* 🏆 **Maria Beatriz Fogolin** – RM552669
* 🏆 **Nicholas Barbosa Lima** – RM552744

## 🎯 **Objetivo**

A aplicação tem como objetivo oferecer uma plataforma para o **cadastro e gerenciamento de usuários e alertas de desastres naturais**. Por meio de operações completas de **CRUD (Create, Read, Update, Delete)**, usuários autenticados podem registrar e divulgar **alertas em tempo real**, informando a ocorrência de eventos como **enchentes, incêndios, deslizamentos** ou outros desastres em sua região.

A solução busca **facilitar a comunicação rápida e eficaz entre moradores de áreas afetadas**, promovendo **maior segurança e conscientização**. Toda a aplicação é construída com **Java Spring Boot** e **Thymeleaf**, com **implantação em nuvem via Azure App Service** e **persistência de dados em banco SQL Server hospedado na Azure**.


## 🎥 Demonstração

📡 [Link para a solução em nuvem](https://ashboard-app-apcgcfbcdad3edd2.brazilsouth-01.azurewebsites.net/usuarios)

📹 [Assista no YouTube]()


## 🏗️ Arquitetura do Projeto

Abaixo está o diagrama da arquitetura geral do projeto, representando a estrutura de microsserviços, integração com banco de dados na nuvem, monitoramento e APIs:

<p align="center"> <img src="https://cdn.discordapp.com/attachments/700444108380045382/1379897920983400458/Global_Devops_Arquitetura_do_Projeto_NIM.drawio.png?ex=6841e995&is=68409815&hm=3b91b45e6ad129ffa9a21fa37d14c8009b29e64efe166b34f0d6904daff301a6&" alt="Arquitetura do Projeto" width="800"/> </p>

O projeto segue o padrão MVC, com divisão clara de responsabilidades:

* `Model` – Representação das entidades como `Alerta`, `Funcionario`, `Endereco`, etc.
* `Repository` – Interfaces de persistência via Spring Data JPA
* `Service` – Camada de regras de negócio e manipulação de dados
* `Controller` – Controle de rotas para as páginas web e ações do usuário
* `View` – Telas HTML com **Thymeleaf**

---

## 🛠️ Tecnologias Utilizadas

* ✅ **Java 17**
* ✅ **Spring Boot 3.3**
* ✅ **Spring Data JPA**
* ✅ **Thymeleaf**
* ✅ **SQL Server (Azure)**
* ✅ **Deploy via Azure App Service**
* ✅ **Spring Security**
* ✅ **Docker**
* ✅ **Prometheus & Spring Boot Admin**
* ✅ **Integração com IA via API (DeepSeek)**

---

## ⚙️ Requisitos

* [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.8+](https://maven.apache.org/download.cgi)
* Conta na **Azure** com App Service e SQL Server provisionados
* IDE (IntelliJ, VS Code ou Eclipse)

---

## 🚀 Como Executar o Projeto

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/ashboard-java.git

# Acesse a pasta do projeto
cd ashboard-java

# Execute com Maven
./mvnw spring-boot:run
```

> Acesse a aplicação: `http://localhost:8080`

⚠️ **Em produção**, a aplicação está disponível em:

```
https://ashboard-java.azurewebsites.net/
```

---

## 🌐 Conexão com Banco de Dados (Azure)

A aplicação conecta-se a um banco SQL Server hospedado na Azure, utilizando autenticação via Active Directory:

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://sqlserver-global.database.windows.net:1433;database=NIM-2TDSPR-GlobalSolution;encrypt=true;trustServerCertificate=false;loginTimeout=30;
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
    username: <seu-usuario>@<dominio>
    password: <sua-senha>
```

---

## 📦 Entidades Principais

### 🧾 Alerta

| Campo | Tipo   | Descrição                       |
| ----- | ------ | ------------------------------- |
| id    | Long   | Identificador único             |
| local | String | Localização do alerta           |
| data  | Date   | Data do desastre                |
| hora  | Time   | Hora do desastre                |
| tipo  | String | Tipo de desastre (ex: enchente) |

### 👤 Funcionário

| Campo | Tipo   | Descrição         |
| ----- | ------ | ----------------- |
| id    | Long   | ID do funcionário |
| nome  | String | Nome completo     |
| cargo | String | Cargo ocupacional |

### 🧍 Usuário

| Campo | Tipo   | Descrição           |
| ----- | ------ | ------------------- |
| id    | Long   | ID do usuário       |
| login | String | Login do sistema    |
| senha | String | Senha criptografada |

---

## 🧪 Dados Iniciais Gerados (DataInitializer)

### 👤 Usuário

| Campo              | Valor                                                                   |
| ------------------ | ----------------------------------------------------------------------- |
| Nome               | Teste Inicial                                                           |
| Sobrenome          | Spring                                                                  |
| CPF                | 599.972.590-92                                                          |
| Telefone           | 1234567890                                                              |
| E-mail             | [mariateste.fogolin@example.com](mailto:mariateste.fogolin@example.com) |
| Data de nascimento | 01/01/1990                                                              |

> Um histórico do usuário é criado automaticamente com 1 alerta.

### ⚠️ Alerta

| Campo       | Valor                        |
| ----------- | ---------------------------- |
| Evento      | Teste de Alerta              |
| Gravidade   | 3 (Moderada)                 |
| Localização | -23.5505, -46.6333           |
| Data/Hora   | Gerados em tempo de execução |

### 👷 Funcionário

| Campo     | Valor                                                   |
| --------- | ------------------------------------------------------- |
| Nome      | João                                                    |
| Sobrenome | Silva                                                   |
| Registro  | 12345678                                                |
| Tipo      | Técnico                                                 |
| Telefone  | 11 99999-9999                                           |
| E-mail    | [joao.silva@example.com](mailto:joao.silva@example.com) |
| Endereço  | Rua Exemplo, SP                                         |

---

## 🔒 Acesso Inicial

| Usuário | Senha    |
| ------- | -------- |
| admin   | admin123 |

---

## 📡 API e Endpoints Principais

### 🧠 IA

* `POST /deepseek-r1-query` – Envia uma pergunta e retorna a resposta da IA

### 🔔 Alerta

* `GET /alertas`
* `GET /alertas/novo/{cpf}`
* `POST /alertas/novo/{cpf}`
* `GET /alertas/{id}`
* `GET /alertas/editar/{id}`
* `POST /alertas/editar`
* `GET /alertas/deletar/{id}`

### 👨‍💼 Funcionário

* `GET /funcionarios`
* `GET /funcionarios/novo`
* `POST /funcionarios/novo`
* `GET /funcionarios/{registro}`
* `GET /funcionarios/editar/{registro}`
* `POST /funcionarios/editar`
* `GET /funcionarios/deletar/{registro}`

### 📜 Histórico

* `GET /historicos`
* `GET /historicos/{id}`
* `GET /historicos/deletar/{id}`

### 👥 Usuário

* `GET /usuarios`
* `GET /usuarios/novo`
* `POST /usuarios/novo`
* `GET /usuarios/{cpf}`
* `GET /usuarios/editar/{cpf}`
* `POST /usuarios/editar`
* `GET /usuarios/deletar/{cpf}`

---

## 🐋 Execução com Docker

```bash
docker-compose up --build
docker exec -it ollama ollama pull deepseek-coder
```

---

## 📊 Observabilidade

Acesse [http://localhost:9090/targets](http://localhost:9090/targets) para visualizar os alvos do Prometheus e garantir que o monitoramento está ativo.

---

## 📌 Conclusão

Este projeto demonstra como construir uma aplicação Java Spring Boot com:

* ✅ Interface web com Thymeleaf
* ✅ Backend robusto com autenticação
* ✅ Deploy em nuvem (Azure App Service)
* ✅ Integração com banco de dados (Azure SQL Server)
* ✅ Arquitetura limpa com MVC
* ✅ Observabilidade e testes
* ✅ Integração com IA via API
* ✅ Inicialização com dados reais

---

### 🧠 Desenvolvido por **Equipe N.I.M.**

---
