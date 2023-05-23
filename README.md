# InfraTracker 🚦

InfraTracker é uma solução inovadora que oferece um gerenciamento eficiente e abrangente de registros de infrações de trânsito, em todo território brasileiro.

## Requisitos 📋

Para executar o InfraTracker, você precisará ter as seguintes ferramentas instaladas:

- [Docker](https://www.docker.com/) :whale:
- [Docker Compose](https://docs.docker.com/compose/install/) :octopus:
- [Maven](https://maven.apache.org/) 📦

## Tecnologias utilizadas 🛠️

### Frontend

 O frontend do InfraTracker foi desenvolvido utilizando as seguintes tecnologias:
 
[![Frontend-Tools](https://skillicons.dev/icons?i=react,bootstrap,vite)](https://skillicons.dev)

### Backend

 O backend do InfraTracker foi desenvolvido utilizando as seguintes tecnologias:
 
 [![Backend-Tools](https://skillicons.dev/icons?i=java,spring,postgresql)](https://skillicons.dev)

 ![Swagger](https://img.shields.io/badge/-Swagger-000?&logo=Swagger)
 O Swagger foi utilizado para documentar a API do InfraTracker. Para acessar a documentação quando a aplicação estiver rodando, utilize o endereço <http://localhost:8080//infraction-central/swagger-ui.html>

 ![FlyWay](https://img.shields.io/badge/-FlyWay-000?&logo=FlyWay)
 O Flyway é uma ferramenta que permite a versionagem e aplicação fácil e confiável de alterações no esquema do banco de dados.
As migrações são armazenadas no diretório `backend/src/main/resources/db/migration`.

## Endpoints do Backend 🚀

A seguir, estão listados os endpoints do backend do InfraTracker:

| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | /records | Retorna todas as infraçoes. |
| GET    | /records/{id} | Retorna uma infração. |
| POST   | /records | Cria uma nova infração. |
| PATCH  | /records/{id} | Atualiza uma infração existente. |
| DELETE | /records/{id} | Deleta uma infração existente. |

### Docker

 Para facilitar o processo de deploy do projeto, foram utilizados contêineres Docker. A seguir, você pode conferir os arquivos Dockerfile e docker-compose.yml utilizados.

#### Dockerfile Backend

```Dockerfile
FROM adoptopenjdk/openjdk11:alpine
 WORKDIR /app
 COPY target/infra-traker-0.0.1-SNAPSHOT.jar infra-tracker.jar
 EXPOSE 8080
 CMD ["java", "-jar", "infra-tracker.jar"]
```

#### Dockerfile Frontend

```Dockerfile
FROM node:20
 WORKDIR /app
 ENV PATH /app/node_modules/.bin:$PATH
 COPY package.json ./
 RUN yarn
 COPY . .
 EXPOSE 5173
 CMD ["yarn", "dev"]
```

#### docker-compose.yml

```yaml
version: "3.9"
services:
  db:
    container_name: infraction-central-db
    image: postgres
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: k29DlaweP65
      POSTGRES_DB: infraction-central
    ports:
      - "5432:5432"
    volumes:
      - db-data:/var/lib/postgresql/data
   backend:
    container_name: infraction-central-backend
    build:
      context: ./backend
      dockerfile: Dockerfile
    restart: always
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/infraction-central
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: k29DlaweP65
    ports:
      - "8080:8080"
   frontend:
    container_name: infraction-central-frontend
    build:
      context: ./frontend
      dockerfile: Dockerfile
    restart: always
    ports:
      - "5173:5173"
 volumes:
  db-data:
```

## Build e Deploy 🏗️

Para fazer o build e o deploy do InfraTracker, siga as instruções abaixo:

### Docker 🐳

1. Clone o repositório do InfraTracker em sua máquina local.
2. Navegue até o diretório backend na linha de comando.
3. Execute o comando `mvn clean package -DskipTests` para gerar o JAR do projeto.
4. Volte ao diretório principal da aplicação e execute o comando `docker-compose up --build` para construir e executar o ambiente do InfraTracker.
5. Depois de executar o comando acima, você poderá acessar o InfraTracker através do endereço <http://localhost:5173>.

### 🚧 Em construção 🚧

Esta documentação ainda está em construção. Algumas informações podem estar incompletas ou desatualizadas.Este projeto ainda está em desenvolvimento para fins de estudo, e esta documentação pode ser atualizada a qualquer momento.
