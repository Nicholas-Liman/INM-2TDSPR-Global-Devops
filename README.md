# 🔥 AshBoard – Cadastro de Alertas de Desastres Naturais

## 📌 Sobre o Projeto

Este projeto foi desenvolvido como parte da disciplina **Java Advanced*, com foco em **Spring Boot**, **MVC** e **implantação em nuvem (Azure)**, pela equipe N.I.M.:

👥 **Integrantes**
- 🏆 **Igor Gabriel Marcondes** – RM553544  
- 🏆 **Maria Beatriz Fogolin** – RM552669  
- 🏆 **Nicholas Barbosa Lima** – RM552744  

🎯 **Objetivo**  
Criar uma aplicação web com **Java Spring Boot** para o **cadastro e gerenciamento de alertas de desastres naturais**, incluindo:

- Interface MVC com **Thymeleaf**
- Persistência em banco de dados **SQL Server na Azure**
- Implantação em **Azure App Service**
- Cadastro de alertas com **localização**, **data** e **hora**

---

## 🧱 Arquitetura

O projeto segue o padrão MVC com divisão clara de responsabilidades:

- `Model` – Representação das entidades como `Alerta`, `Funcionario`, `Endereco`, etc.  
- `Repository` – Interfaces para persistência via Spring Data JPA  
- `Service` – Regras de negócio e manipulação de dados  
- `Controller` – Rotas de controle para páginas web e ações do usuário  
- `View` – Telas em HTML com **Thymeleaf**

---

## 🛠️ Tecnologias Utilizadas

- ✅ **Java 17**  
- ✅ **Spring Boot 3.3**  
- ✅ **Spring Data JPA**  
- ✅ **Thymeleaf**  
- ✅ **SQL Server (Azure)**  
- ✅ **Deploy via Azure App Service**

---

## ⚙️ Requisitos

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- [Maven 3.8+](https://maven.apache.org/download.cgi)  
- Conta na **Azure** com App Service e SQL Server provisionados  
- IDE (IntelliJ, VS Code, Eclipse)

---

## 🚀 Como Executar o Projeto

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/ashboard-java.git

# Acesse a pasta do projeto
cd ashboard-java

# Execute com Maven
./mvnw spring-boot:run
````

> Acesse a aplicação: `http://localhost:8080`

⚠️ **Em produção**, a aplicação será hospedada em:

```bash
https://ashboard-java.azurewebsites.net/
```

---

## 🌐 Conexão com Banco de Dados (Azure)

A aplicação está conectada a um banco SQL Server na Azure, com autenticação via Active Directory:

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

O projeto utiliza entidades criadas diretamente no SQL Server com gatilhos e históricos, incluindo:

### 🧾 Alerta

| Campo | Tipo   | Descrição                       |
| ----- | ------ | ------------------------------- |
| id    | Long   | Identificador único             |
| local | String | Localização do alerta           |
| data  | Date   | Data do desastre                |
| hora  | Time   | Hora do desastre                |
| tipo  | String | Tipo de desastre (ex: enchente) |

### 👤 Funcionario

| Campo | Tipo   | Descrição         |
| ----- | ------ | ----------------- |
| id    | Long   | ID do funcionário |
| nome  | String | Nome completo     |
| cargo | String | Cargo ocupacional |

### 🧍 Usuario

| Campo | Tipo   | Descrição           |
| ----- | ------ | ------------------- |
| id    | Long   | ID do usuário       |
| login | String | Login do sistema    |
| senha | String | Senha criptografada |

---

## 📌 Conclusão

Este projeto demonstra como construir uma aplicação Spring Boot com MVC, persistência em banco na nuvem (Azure SQL Server), interface web com Thymeleaf e uma arquitetura limpa.

Com ele, conseguimos:

📍 Cadastrar alertas com local, data e hora

📤 Hospedar em nuvem pública (Azure)

📄 Manter organização com boas práticas de MVC

💡 Utilizar Thymeleaf para criação de views dinâmicas

---

### 🧠 Desenvolvido por **Equipe N.I.M.**
