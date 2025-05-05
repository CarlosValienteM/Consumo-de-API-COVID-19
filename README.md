# 📘 Parcial 2 - Proyecto: Consumo de API COVID-19

Este proyecto en Java con Spring Boot consume una API pública que proporciona estadísticas de COVID-19 por región, provincia y fecha específica. Los datos se almacenan en una base de datos MariaDB/MySQL usando JPA. El proceso se ejecuta automáticamente 15 segundos después del arranque, mediante un hilo.

---

## 📊 Tecnologías Usadas

- Java 17  
- Spring Boot 3.4.5  
- Maven  
- JPA (Hibernate)  
- MySQL / MariaDB  
- RapidAPI - COVID-19 Statistics API  

---

## 📁 Estructura del Proyecto

- `model/`: Contiene las entidades `Region`, `Province`, `Report`, `ExecutedReport`.  
- `repository/`: Interfaces JPA para acceder a la base de datos.  
- `service/`: Lógica de negocio separada para cada entidad.  
- `controller/`: Endpoints REST para exponer los datos guardados.  
- `config/`: Clases de configuración, incluyendo el hilo (`StartupThreadRunner`).  

---

## ⏱️ Flujo de Ejecución Automática

1. Al iniciar la aplicación, se espera 15 segundos (`Thread.sleep(15000)`).
2. Luego se consumen automáticamente los siguientes endpoints de la API:

   - `/regions`
   - `/provinces` (filtrado por `iso=GTM`)
   - `/reports` (filtrado por `iso=GTM` y `date=2022-04-16`)

3. Los datos se procesan, **se evita la duplicación** usando la entidad `ExecutedReport`, y se almacenan en la base de datos.

---

## 🔎 Funcionalidades REST Disponibles

- `GET /api/regions`  
- `GET /api/provinces`  
- `GET /api/reports`  
- `GET /api/reports/by-date?date=YYYY-MM-DD`  
- `GET /api/reports/by-region?name=US`  
- `GET /api/reports/by-province?name=California`  
- `GET /api/reports/grouped-by-province?date=YYYY-MM-DD&iso=US` (agrupamiento por provincia)

---

## 📆 Configuración

En el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/covid_db
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update



 ## Video explicativo: https://drive.google.com/file/d/1NlzSuM0-VPkqFcwzLBhTisybwLwInO4r/view?usp=drive_link

