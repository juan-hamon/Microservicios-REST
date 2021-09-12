# Microservicios-REST
Repositorio para el desarrollo del taller 2 de la clase de Microservicios
## Tabla de contenidos

- [Requisitos](#Requisitos)
    - [Java 11](#Java-11)
    - [Maven](#Maven)
- [Parte 1: REST con servidor Jersey y cliente](#Parte-1:-REST-con-servidor-Jersey-y-cliente)
    - [Iniciar el servidor Jersey](#Iniciar-el-servidor-Jersey)
    - [Utilizar el cliente web para probar los métodos del servidor](#Utilizar-el-cliente-web-para-probar-los-métodos-del-servidor)
- [Parte 2: Calculadora con Microservicios](#Parte-2:-Calculadora-con-Microservicios)
    - [Iniciar EurekaServer](#Iniciar-EurekaServer)
    - [Iniciar la calculadora](#Iniciar-la-calculadora)
    - [Iniciar los operadores](#Iniciar-los-operadores)
        - [Iniciar un Sumador](#Iniciar-un-Sumador)
        - [Iniciar un Sustractor](#Iniciar-un-Sustractor)
        - [Iniciar un Multiplicador](#Iniciar-un-Multiplicador)
        - [Iniciar un Divisor](#Iniciar-un-Divisor)
    - [Probar la calculadora](#Probar-la-calculadora)

## Requisitos

### Java 11

Los proyectos utilizados para este taller fueron realizados en Java 11, por lo que es necesario tener instalada esta versión de Java. Para verificar si tiene instalado Java 11 ejecute el siguiente comando:

```sh
java --version
```
La respuesta debería ser similar a esto:

```sh
openjdk 11.0.11 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)
OpenJDK 64-Bit Server VM (build 11.0.11+9-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)
```

En caso de no tener esta respuesta en la consola, significa que no tiene Maven instalado, puede acceder [aquí](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) para descargarlo.
### Maven

Para poder utilizar los diferentes proyectos generados para el taller es necesario tener Maven instalado. Para verificar si tiene instalado Maven ejecute el siguiente comando:

```sh
mvn --version
```

La respuesta debería ser similar a esto:

```sh
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 11.0.11, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.10.16.3-microsoft-standard-wsl2", arch: "amd64", family: "unix"
```

En caso de no tener esta respuesta en la consola, significa que no tiene Maven instalado, puede acceder [aquí](https://maven.apache.org/download.cgi) para descargarlo.


## Parte 1: REST con servidor Jersey y cliente

### Iniciar el servidor Jersey

Para iniciar el servidor de Jersey se debe ingresar a la carpeta **Parte 1** y posteriormente ingresar a la carpeta **Jersey Server**. Esto mediante los siguientes comandos:

```sh
cd Parte\ 1/
cd JerseyServer/
```

Una vez estemos ubicados en la carpeta **Jersey Server** ejecutaremos el comando de maven para compilar el proyecto:

```sh
mvn clean install
```

Finalmente, ejecutaremos el servidor con el siguiente comando:

```sh
mvn exec:java
```

### Utilizar el cliente web para probar los métodos del servidor

Se desarrolló un cliente web para utilizar los métodos del servidor. Para utilizarlo simplemente ubicamos el archivo "index.html" en la carpeta **Client** dentro de la carpeta **Parte 1** (esto navegando a través del explorador de archivos de nuestro sistema operativo) y lo abrimos en nuestro navegador de preferencia.

Desde este cliente se podrán utilizar los diferentes métodos definidos en el servidor, el cual debe haber sido iniciado previamente como se indicó en la sección anterior. De tener alguna duda, se puede revisar el video tutorial que se encuentra en la wiki de este repositorio.

## Parte 2: Calculadora con Microservicios

### Iniciar EurekaServer

Para iniciar el servidor de Eureka, el cual permitirá registrar y descubrir la calculadora y sus diferentes instancias de los operadores, se debe ingresar la carpeta **Parte 2** y posteriormente ingresar a la carpeta **EurekaServer**. Esto mediante los siguientes comandos:

```sh
cd Parte\ 2/
cd EurekaServer/
```

Una vez estemos en la carpeta **EurekaServer** ejecutaremos el comando de maven para compilar el proyecto:

```sh
mvn clean install
```

Finalmente, ejecutaremos el servidor con el siguiente comando:

```sh
mvn spring-boot:run
```

### Iniciar la calculadora

Para iniciar la calculadora, la cual actuará como mediador para acceder a los métodos de los diferentes operadores disponibles, se debe ingresar la carpeta **Parte 2** y posteriormente ingresar a la carpeta **calculadora**. Esto mediante los siguientes comandos:

```sh
cd Parte\ 2/
cd calculadora/
```

Una vez estemos en la carpeta **calculadora** ejecutaremos el comando de maven para compilar el proyecto:

```sh
mvn clean install
```

> Nota: Al compilar el proyecto, Maven ejecuta unas pruebas que se conectan con el servidor de Eureka, por lo que es recomendable tenerlo activo previamente. Sin embargo, aún si el servidor no está corriendo, la compilación se ejecutará correctamente.

Finalmente, ejecutaremos la calculadora con el siguiente comando:

```sh
mvn spring-boot:run
```
> Nota: Para ejecutar la calculadora es necesario que el servidor de Eureka esté activo, esto para que la instacia de la calculadora se pueda registrar en el servidor.

### Iniciar los operadores

Para que la calculadora pueda ofrecer las diferentes operaciones de forma correcta, es necesario iniciar las instancias de los operadores desarrollados (no es necesario iniciarlos todos, solo los que se desean ofrecer).

De igual forma, se pueden iniciar múltiples instancias de uno o más operadores gracias al servicio de registro y descubrimiento que ofrece el servidor de Eureka. Para iniciar la primera instancia se puede hacer de forma normal, sin embargo, para las demás instancias será necesario indicar un número de puerto diferente al establecido en las configuraciones del proyecto para cada una de las otras instancias que se quieran iniciar.

A continuación se explicará como iniciar las instancias de cada uno de los operadores.

#### Iniciar un Sumador

Para iniciar un sumador, el cual se encargará de ofrecer la operación de suma, se debe ingresar la carpeta **Parte 2** y posteriormente ingresar a la carpeta **sumador**. Esto mediante los siguientes comandos:

```sh
cd Parte\ 2/
cd sumador/
```

Una vez estemos en la carpeta **sumador** ejecutaremos el comando de maven para compilar el proyecto:

```sh
mvn clean install
```

> Nota: Al compilar el proyecto, Maven ejecuta unas pruebas que se conectan con el servidor de Eureka, por lo que es recomendable tenerlo activo previamente. Sin embargo, aún si el servidor no está corriendo, la compilación se ejecutará correctamente.

Finalmente, ejecutaremos el sumador. Si solamente queremos ejecutar una instacia del sumador se ejecuta el siguiente comando:

```sh
mvn spring-boot:run
```
> Nota: Para ejecutar el sumador es necesario que el servidor de Eureka esté activo, esto para que la instacia del sumador se pueda registrar en el servidor.

Sin embargo, si se quiere ejecutar una nueva instancia del sumador, se debe especifciar el nuevo puerto con la palabra clave **SERVER_PORT**. Por ejemplo:

```sh
SERVER_PORT=9999 mvn spring-boot:run
```
> Nota: Esto solo funcionará para sistemas con Linux o MacOS, por lo que si se ejecuta desde un Windows es recomendable utilizar WSL para ejecutarlo desde Linux. En caso de no contar con WSL, se debe modificar el valor de la propiedad **server.port** en el archivo **src/main/resources/application.properties** dentro de la carpeta **sumador** y ejecutar el comando **mvn spring-boot:run** en una nueva terminal.

#### Iniciar un Sustractor

Para iniciar un sustractor, el cual se encargará de ofrecer la operación de resta, se debe ingresar la carpeta **Parte 2** y posteriormente ingresar a la carpeta **sustractor**. Esto mediante los siguientes comandos:

```sh
cd Parte\ 2/
cd sustractor/
```

Una vez estemos en la carpeta **sustractor** ejecutaremos el comando de maven para compilar el proyecto:

```sh
mvn clean install
```

> Nota: Al compilar el proyecto, Maven ejecuta unas pruebas que se conectan con el servidor de Eureka, por lo que es recomendable tenerlo activo previamente. Sin embargo, aún si el servidor no está corriendo, la compilación se ejecutará correctamente.

Finalmente, ejecutaremos el sustractor. Si solamente queremos ejecutar una instacia del sustractor se ejecuta el siguiente comando:

```sh
mvn spring-boot:run
```
> Nota: Para ejecutar el sustractor es necesario que el servidor de Eureka esté activo, esto para que la instacia del sustractor se pueda registrar en el servidor.

Sin embargo, si se quiere ejecutar una nueva instancia del sustractor, se debe especifciar el nuevo puerto con la palabra clave **SERVER_PORT**. Por ejemplo:

```sh
SERVER_PORT=9999 mvn spring-boot:run
```
> Nota: Esto solo funcionará para sistemas con Linux o MacOS, por lo que si se ejecuta desde un Windows es recomendable utilizar WSL para ejecutarlo desde Linux. En caso de no contar con WSL, se debe modificar el valor de la propiedad **server.port** en el archivo **src/main/resources/application.properties** dentro de la carpeta **sustractor** y ejecutar el comando **mvn spring-boot:run** en una nueva terminal.

#### Iniciar un Multiplicador

Para iniciar un multiplicador, el cual se encargará de ofrecer la operación de multiplicación, se debe ingresar la carpeta **Parte 2** y posteriormente ingresar a la carpeta **multiplicador**. Esto mediante los siguientes comandos:

```sh
cd Parte\ 2/
cd multiplicador/
```

Una vez estemos en la carpeta **multiplicador** ejecutaremos el comando de maven para compilar el proyecto:

```sh
mvn clean install
```

> Nota: Al compilar el proyecto, Maven ejecuta unas pruebas que se conectan con el servidor de Eureka, por lo que es recomendable tenerlo activo previamente. Sin embargo, aún si el servidor no está corriendo, la compilación se ejecutará correctamente.

Finalmente, ejecutaremos el multiplicador. Si solamente queremos ejecutar una instacia del multiplicador se ejecuta el siguiente comando:

```sh
mvn spring-boot:run
```
> Nota: Para ejecutar el multiplicador es necesario que el servidor de Eureka esté activo, esto para que la instacia del multiplicador se pueda registrar en el servidor.

Sin embargo, si se quiere ejecutar una nueva instancia del multiplicador, se debe especifciar el nuevo puerto con la palabra clave **SERVER_PORT**. Por ejemplo:

```sh
SERVER_PORT=9999 mvn spring-boot:run
```
> Nota: Esto solo funcionará para sistemas con Linux o MacOS, por lo que si se ejecuta desde un Windows es recomendable utilizar WSL para ejecutarlo desde Linux. En caso de no contar con WSL, se debe modificar el valor de la propiedad **server.port** en el archivo **src/main/resources/application.properties** dentro de la carpeta **multiplicador** y ejecutar el comando **mvn spring-boot:run** en una nueva terminal.

#### Iniciar un Divisor

Para iniciar un divisor, el cual se encargará de ofrecer la operación de división, se debe ingresar la carpeta **Parte 2** y posteriormente ingresar a la carpeta **divisor**. Esto mediante los siguientes comandos:

```sh
cd Parte\ 2/
cd divisor/
```

Una vez estemos en la carpeta **divisor** ejecutaremos el comando de maven para compilar el proyecto:

```sh
mvn clean install
```

> Nota: Al compilar el proyecto, Maven ejecuta unas pruebas que se conectan con el servidor de Eureka, por lo que es recomendable tenerlo activo previamente. Sin embargo, aún si el servidor no está corriendo, la compilación se ejecutará correctamente.

Finalmente, ejecutaremos el divisor. Si solamente queremos ejecutar una instacia del divisor se ejecuta el siguiente comando:

```sh
mvn spring-boot:run
```
> Nota: Para ejecutar el divisor es necesario que el servidor de Eureka esté activo, esto para que la instacia del divisor se pueda registrar en el servidor.

Sin embargo, si se quiere ejecutar una nueva instancia del divisor, se debe especifciar el nuevo puerto con la palabra clave **SERVER_PORT**. Por ejemplo:

```sh
SERVER_PORT=9999 mvn spring-boot:run
```
> Nota: Esto solo funcionará para sistemas con Linux o MacOS, por lo que si se ejecuta desde un Windows es recomendable utilizar WSL para ejecutarlo desde Linux. En caso de no contar con WSL, se debe modificar el valor de la propiedad **server.port** en el archivo **src/main/resources/application.properties** dentro de la carpeta **divisor** y ejecutar el comando **mvn spring-boot:run** en una nueva terminal.

### Probar la calculadora

Una vez inicializado el servidor de Eureka, la calculadora y las diferentes instancias de los operadores, se debe acceder a un navegador para para así utilizar a las URIs que permiten realizar las operaciones y mostrar sus resultados. Las URIs para las diferentes operaciones se muestran en la siguiente tabla:

| URI | Descripción |
|-----|-------------|
| http://localhost:8888/calculadora/suma?a={num1}&b={num2}&user={usuario}| Retorna la suma de num1 y num2. Además registra los datos de la operación, el resultado y el usuario que hizo la solicitud|
| http://localhost:8888/calculadora/suma/files| Retorna la información de las solicitudes realizadas para la operación de suma.|
| http://localhost:8888/calculadora/resta?a={num1}&b={num2}&user={usuario}| Retorna la resta de num1 y num2. Además registra los datos de la operación, el resultado y el usuario que hizo la solicitud|
| http://localhost:8888/calculadora/resta/files| Retorna la información de las solicitudes realizadas para la operación de resta.|
| http://localhost:8888/calculadora/multip?a={num1}&b={num2}&user={usuario}| Retorna la multiplicación de num1 y num2. Además registra los datos de la operación, el resultado y el usuario que hizo la solicitud|
| http://localhost:8888/calculadora/multip/files| Retorna la información de las solicitudes realizadas para la operación de multiplicación.|
| http://localhost:8888/calculadora/div?a={num1}&b={num2}&user={usuario}| Retorna la división de num1 y num2. Además registra los datos de la operación, el resultado y el usuario que hizo la solicitud|
| http://localhost:8888/calculadora/div/files| Retorna la información de las solicitudes realizadas para la operación de división.|