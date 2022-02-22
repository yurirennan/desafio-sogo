<h1 align="center" > DESAFIO <b style="color: #03A9F5;">SOGO TECNOLOGIA</b> </h1>

<h3> :loudspeaker: O que é o projeto? </h3>
<p>O desafio consiste em criar uma API REST para cadastro de tarefas desenvolvido em Java com framework Spring.</p>

<hr>

<h3 id="sumario"> 📑 Sumário </h3>

- <a href="#requisitos"> Requisitos para rodar o projeto:</a>
    - <a href="#requisitos-com-docker"> Com Docker.</a>
    - <a href="#requisitos-sem-docker"> Sem Docker.</a>
- <a href="#como-rodar">Como rodar o projeto:</a>
    - <a href="#rodar-com-docker"> Com Docker.</a>
    - <a href="#rodar-sem-docker"> Sem Docker.</a>
- <a href="#tecnologias">Tecnologias e recursos utilizadas no projeto.</a>

<hr>

<h3 id="requisitos"> 🧾 Requisitos para rodar o projeto</h3>

<h4 id="requisitos-com-docker"> Com Docker:</h4> 

- <a target="_blank" href="https://maven.apache.org/">Maven</a>
- <a target="_blank" href="https://openjdk.java.net/install/">Java 8</a>
- <a target="_blank" href="https://docs.docker.com/engine/install/">Docker</a>
- <a target="_blank" href="https://docs.docker.com/compose/install/">Docker Compose</a>

<h4 id="requisitos-sem-docker"> Sem Docker:</h4>

- <a target="_blank" href="https://maven.apache.org/">Maven</a>
- <a target="_blank" href="https://openjdk.java.net/install/">Java 8</a>
- <a target="_blank" href="https://www.postgresql.org/download/">Postgresql</a>

<hr>

<h3 id="como-rodar"> 🏗️ Rodar o projeto</h3>

<h4 id="rodar-com-docker"> Com Docker:</h4> 

- Gere o <b>.jar</b> da aplicação executando o comando no terminal:
    ```shell
    mvn clean install -Dskiptests
    ```

- Instancie e rode os containers executando o comando no terminal:
    ```shell
    docker-compose up --build --force-recreate
    ```

<h4 id="rodar-sem-docker"> Sem Docker:</h4> 

- Gere o <b>.jar</b> da aplicação executando o comando no terminal:
    ```shell
    mvn clean install -Dskiptests
    ```

- Para rodar o <b>.jar</b> vá até a pasta do projeto pelo terminal e rode o comando:
    ```shell
    java -jar -Dspring.profiles.active=localdebug Desafio-0.0.1-SNAPSHOT.jar
     ```

<h3 id="tecnologias"> 🚀 Tecnologias e recursos utilizadas no projeto</h3>

- Java
- Banco de Dados Postgresql
- Docker
- Spring Web
- Spring Security
- Token JWT
- Hibernate Validator
- Swagger v3
<hr>

