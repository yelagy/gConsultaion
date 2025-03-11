# gConsultation

## Description
**gConsultation** est une application web permettant de gérer les consultations médicales des patients. Développée avec **Spring Boot**, **MySQL** et une interface en **HTML + Bootstrap**, cette application permet d'ajouter, modifier et supprimer des patients ainsi que leurs consultations.

## Fonctionnalités
- Gestion des patients (ajout, modification, suppression)
- Gestion des consultations (ajout, modification, suppression)
- Interface utilisateur avec Bootstrap
- Validation des formulaires et alertes SweetAlert
- Vérification avant suppression des patients ayant des consultations

## Technologies utilisées
- **Backend** : Java, Spring Boot, Spring Data JPA
- **Base de données** : MySQL
- **Frontend** : HTML, Thymeleaf, Bootstrap, JavaScript (SweetAlert)

## Installation et exécution

### Prérequis
- Java 17+
- MySQL
- Maven
- Un IDE comme IntelliJ IDEA ou Eclipse

### Étapes
1. **Cloner le projet**
   ```bash
   git clone https://github.com/votre-repo/gConsultation.git
   cd gConsultation
   ```
2. **Configurer la base de données**
   - Ouvrir `src/main/resources/application.properties`
   - Modifier les paramètres de connexion MySQL :
     ```properties
     # Configuration de la base de données MySQL
     spring.datasource.url=jdbc:mysql://localhost:3306/gconsultation
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     # Configuration de Hibernate
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

     logging.level.org.thymeleaf=DEBUG
     logging.level.org.springframework.web=DEBUG

     # Configuration de Thymeleaf
     spring.thymeleaf.cache=false
     ```
   - Créer la base de données `gconsultation` dans MySQL

3. **Lancer l'application**
   ```bash
   mvn spring-boot:run
   ```
   L'application sera accessible sur `http://localhost:8080/patients`

## Utilisation
- **Ajouter un patient** : Aller sur `/patients/add`
- **Modifier un patient** : Bouton "Modifier" sur la liste des patients
- **Supprimer un patient** : Bouton "Supprimer" (avec confirmation)
- **Ajouter une consultation** : Aller sur `/consultations/add`
- **Modifier une consultation** : Bouton "Modifier" sur la liste des consultations
- **Supprimer une consultation** : Bouton "Supprimer" (avec confirmation)

## Auteur
- **Youssef Elagy** - Développeur
