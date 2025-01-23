
# Mon Projet Spring Boot

Une application Spring Boot moderne suivant les meilleures pratiques pour l’utilisation des DTOs, la gestion des erreurs,
l’intégration de JWT dans Swagger, et bien plus encore.

---

## Fonctionnalités principales

1. **Intégration de Lombok dans IntelliJ IDEA Community Edition** :
   - Simplifie le code en générant automatiquement les getters, setters, et constructeurs.
   - Voir ci-dessous pour les instructions d'installation.
2. **Utilisation des DTOs** :
   - Les contrôleurs ne retournent jamais d’entités directement, mais des DTOs.
   - Exemple : **ProfileController > getUserInfo**.
3. **Utilisation de MapStruct** :
   - Automatisation du mapping entre les entités et les DTOs (voir le package `Mappers`).
4. **JWT avec Swagger** :
   - Intégration de `SecurityScheme` dans la configuration Swagger pour gérer les authentifications JWT.
5. **Gestion des audits** :
   - Gestion automatique des dates de création (`createdDate`) et modification (`lastModifiedDate`).
   - Exemple : Classe `User` qui hérite d’une classe `Auditable`.
6. **Gestion globale des erreurs** :
   - Une classe `GlobalExceptionHandler` gère les exceptions levées dans l’application.
7. **Configuration flexible avec `values.env`** :
   - Variables d'environnement pour gérer les configurations sensibles ou spécifiques.

---

## Installation de Lombok dans IntelliJ IDEA Community Edition

1. **Ajouter la dépendance dans `pom.xml`** :
   ```xml
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.18.28</version>
       <scope>provided</scope>
   </dependency>
   ```

2. **Installer le plugin Lombok** :
   - Ouvrez IntelliJ IDEA.
   - Allez dans `File > Settings > Plugins`.
   - Recherchez **Lombok**, installez-le, puis redémarrez IntelliJ.

3. **Activer le traitement des annotations** :
   - Allez dans `File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors`.
   - Cochez **"Enable annotation processing"**.
   - Enregistrez les modifications.

---

## Utilisation des DTOs

1. **Pourquoi utiliser les DTOs ?**
   - Les entités ne doivent pas être exposées directement par les contrôleurs.
   - Séparez la logique métier de la structure des réponses API.

2. **Exemple : `ProfileController > getUserInfo`** :
   ```java
   @RestController
   @RequestMapping("/api/auth")
   public class ProfileController {

       private final UserService userService;

       public ProfileController(UserService userService) {
           this.userService = userService;
       }

       @GetMapping("/me")
       public UserDTO getUserInfo(Principal principal) {
           return userService.findByEmailOrName(principal.getName());
       }
   }
   ```

3. **Services retournant des DTOs** :
   ```java
   @Service
   public class UserService {

       private final UserRepository userRepository;
       private final UserMapper userMapper;

       public UserService(UserRepository userRepository, UserMapper userMapper) {
           this.userRepository = userRepository;
           this.userMapper = userMapper;
       }

       public UserDTO findByEmailOrName(String emailOrName) {
           return userRepository.findByEmailOrName(emailOrName, emailOrName)
                   .map(userMapper::toUserDTO)
                   .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));
       }
   }
   ```

---

## MapStruct pour le mapping

1. **Exemple de Mapper** :
   ```java
   @Mapper(componentModel = "spring")
   public interface UserMapper {

       UserDTO toUserDTO(User user);
       User toUser(RegisterDTO registerDTO);
   }
   ```

---

## Gestion des audits

1. **Configuration dans `AuditConfig`** :
   ```java
   @Configuration
   @EnableJpaAuditing
   public class AuditConfig {
   }
   ```

2. **Classe de base pour l’audit** :
   ```java
   @MappedSuperclass
   @EntityListeners(AuditingEntityListener.class)
   public abstract class Auditable {

       @CreatedDate
       @Column(updatable = false)
       private LocalDateTime createdDate;

       @LastModifiedDate
       private LocalDateTime lastModifiedDate;

       // Getters et setters
   }
   ```

3. **Exemple d’entité avec audit** :
   ```java
   @Entity
   public class User extends Auditable {
       private String name;
       private String email;
       private String password;
   }
   ```

---

## Intégration JWT avec Swagger

1. **Mise à jour de `SwaggerConfig`** :
   ```java
   @Configuration
   public class SwaggerConfig {
   @Bean
    public OpenAPI springShopOpenAPI() {

        SecurityScheme bearerAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

   }}
   ```

---

## Gestion globale des erreurs DE L'Api

1. **Exemple : `GlobalExceptionHandler`** :
   ```java
   @RestControllerAdvice
   public class GlobalExceptionHandler {

       @ExceptionHandler(ResourceNotFoundException.class)
       @ResponseStatus(HttpStatus.NOT_FOUND)
       public Map<String, String> handleResourceNotFound(ResourceNotFoundException ex) {
           return Map.of("error", ex.getMessage());
       }

       @ExceptionHandler(Exception.class)
       @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
       public Map<String, String> handleGenericException(Exception ex) {
           return Map.of("error", "Une erreur inattendue s’est produite.");
       }
   }
   ```

---

## Configuration flexible avec `values.env`

1. **Fichier `values.env (à modifier)` **  :
   - Stocke les configurations sensibles (ex. : clés JWT, credentials).
   - Exemple :
     ```env
     JWT_SECRET=ma-cle-secrete
     DB_URL=jdbc:postgresql://localhost:5432/mabd
     DB_USER=monutilisateur
     DB_PASSWORD=monmotdepasse
     ```

2. **Références dans `application.properties`** :
   - Exemple :
     ```properties
     spring.datasource.url=${DB_URL}
     spring.datasource.username=${DB_USER}
     spring.datasource.password=${DB_PASSWORD}
     spring.jpa.hibernate.ddl-auto=update

     ```

---

## Tests

1. **Lombok** :
   - Vérifiez que les getters et setters sont bien générés.

2. **Swagger UI** :
   - Accédez à `/swagger-ui.html` pour tester les endpoints sécurisés.

3. **DTOs** :
   - Confirmez que les contrôleurs renvoient des DTOs sans champs sensibles.

4. **Variables d’environnement** :
   - Assurez-vous que les valeurs de `values.env` sont bien utilisées.

---

