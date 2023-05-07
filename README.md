# InfraTracker :traffic_light:

 InfraTracker é uma solução inovadora que oferece um gerenciamento eficiente e abrangente de registros de infrações de trânsito, em todo território brasileiro. Estamos aqui para ajudar, então, se você tiver alguma dúvida ou encontrar qualquer problema, não hesite em entrar em contato conosco. :wave:

## Requisitos 📋

- Maven instalado para executar o comando mvn clean package -DskipTests ✅
- Docker instalado para executar o comando docker-compose up ou docker-compose up -d ✅

## Tecnologias utilizadas 🛠️

### Frontend

 O frontend do InfraTracker foi desenvolvido utilizando as seguintes tecnologias:

<img src="https://img.shields.io/badge/-Vite-000?&logo=Vite" alt="Vite" />
<img src="https://img.shields.io/badge/-BootStrap-000?&logo=BootStrap" alt="BootStrap" />
<img src="https://img.shields.io/badge/-React-000?&logo=React" alt="React" />
<img src="https://img.shields.io/badge/-Axios-000?&logo=Axios" alt="Axios" />

### Backend

 O backend do InfraTracker foi desenvolvido utilizando as seguintes tecnologias:

<img src="https://img.shields.io/badge/-Java-000?&logo=Java8" alt="Swagger" />
<img src="https://img.shields.io/badge/-SpringBoot-000?&logo=SpringBoot" alt="Swagger" />
<img src="https://img.shields.io/badge/-Flyway-000?&logo=Flyway" alt="Swagger" />
<img src="https://img.shields.io/badge/-Swagger-000?&logo=Swagger" alt="Swagger" />
<img src="https://img.shields.io/badge/-PostgreSQL-000?&logo=PostgreSQL" alt="PostgreSQL" />

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

 Para realizar o build e o deploy do projeto, basta executar o script abaixo, presente na pasta principal do projeto. O script realiza os seguintes passos:

```bash
./build-and-deploy.sh
```

 1. Navega até a pasta do backend e executa o comando  `mvn clean package -DskipTests`  para gerar o arquivo JAR do backend.
 2. Volta para a pasta principal do projeto e verifica se o comando  `docker-compose`  está disponível.
 3. Executa o comando  `docker-compose build`  para gerar as imagens do Docker.
 4. Executa o comando  `docker-compose up -d`  para iniciar os contêineres Docker do projeto.

 Caso ocorra algum erro durante esses passos, o script exibirá a mensagem correspondente ao erro. Caso contrário, a mensagem "Script completed successfully!" será exibida. :white_check_mark:
