Proyecto: Consumo de API COVID-19

Este proyecto en Java con Spring Boot consume una API pública que proporciona estadísticas de COVID-19 por región, provincia y fecha específica. Los datos se almacenan en una base de datos MariaDB/MySQL usando JPA. El proceso se ejecuta automáticamente 15 segundos después del arranque, mediante un hilo.

📊 Tecnologías Usadas

Java 17

Spring Boot 3.4.5

Maven

JPA (Hibernate)

MySQL / MariaDB

RapidAPI - COVID-19 Statistics API

📁 Estructura del Proyecto

model/: Contiene las entidades Region, Province y Report.

repository/: Interfaces para acceder a la base de datos.

service/: Lógica de negocio separada para cada entidad.

controller/: Endpoints REST para exponer los datos guardados.

config/: Clases de configuración, incluyendo el hilo (StartupThreadRunner).

⏱️ Flujo de Ejecución

Al iniciar la aplicación, se espera 15 segundos (hilo).

Se consumen los siguientes endpoints de la API:

/regions

/provinces (filtrado por iso = GTM)

/reports (filtrado por iso = GTM y fecha 2022-04-16)

Los datos se procesan y almacenan en la base de datos.

Los registros se pueden consultar mediante los controladores REST:

/api/regions

/api/provinces

/api/reports

📆 Configuración

En application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/covid_db
spring.datasource.username=root
spring.datasource.password= (tu password aquí)
spring.jpa.hibernate.ddl-auto=update

🔧 Requisitos Técnicos Cubiertos ✅



🔗 Repositorio

https://github.com/CarlosValienteM/Consumo-de-API-COVID-19

✅ Autor

Carlos Valiente

🌐 Fuente de la API

COVID-19 Statistics by RapidAPI
https://rapidapi.com/axisbits-axisbits-default/api/covid-19-statistics

📅 Fecha

Mayo 2025

