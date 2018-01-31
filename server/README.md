# Angular4 Spring Boot Project
This sub-project is the backend server portion of the project.

**Make sure you have Maven and Java 1.7 or greater**

```bash
# change directory to server
cd Springular/server

# clean and install the repo with mvn
mvn clean install

# start the server
mvn spring-boot:run

# the app will be running on port 8080
# there are two built-in user accounts to demonstrate the differing levels of access to the endpoints:
# - User - user:123
# - Admin - admin:123
```


## File Structure
```
Springular/server
 ├──src/                                                        * our source files
 │   ├──main
 │   │   ├──java.com.mediatheque
 │   │   │   ├──config
 │   │   │   │   └──WebSecurityConfig.java                      * security configureation file, all the important things.
 │   │   │   ├──model
 │   │   │   │   ├──Authority.java
 │   │   │   │   ├──Document.java                       
 │   │   │   │   ├──Audio.java                        
 │   │   │   │   ├──Video.java                        
 │   │   │   │   ├──Game.java                        
 │   │   │   │   ├──Livre.java                       
 │   │   │   │   ├──FicheEmprunt.java                      
 │   │   │   │   ├──Localisation.java                      
 │   │   │   │   ├──Mediatheque.java                        
 │   │   │   │   ├──Rappel.java                         
 │   │   │   │   ├──UserRequest.java                    
 │   │   │   │   ├──UserTokenState.java                         * stores the token states like token_key and token_ttl.
 │   │   │   │   └──User.java                                   * our main user model which implements UserDetails.
 │   │   │   ├──repository                                      * repositories folder for accessing database
 │   │   │   │   ├──AudioRepository.java              
 │   │   │   │   ├──AuthorityRepository.java             
 │   │   │   │   ├──DocumentRepository.java              
 │   │   │   │   ├──FicheEmpruntRepository.java              
 │   │   │   │   ├──GameRepository.java              
 │   │   │   │   ├──LivreRepository.java              
 │   │   │   │   ├──LocalisationRepository.java             
 │   │   │   │   ├──MediathequeRepository.java              
 │   │   │   │   ├──RappelRepository.java              
 │   │   │   │   ├──VidoRepository.java                  
 │   │   │   │   └──UserRepository.java
 │   │   │   ├──rest                                            * rest endpoint folder
 │   │   │   │   ├──DocumentController.java                          
 │   │   │   │   ├──AuthenticationController.java               
 │   │   │   │   └──UserController.java                         
 │   │   │   │   └──EmpruntController.java                         
 │   │   │   │   └──MediathequeController.java                      
 │   │   │   │   └──PublicController.java                         
 │   │   │   │   └──RappelController.java                         
 │   │   │   │   └──LocalisationController.java                        
 │   │   │   ├──security                                        * Security related folder(JWT, filters)
 │   │   │   │   ├──auth
 │   │   │   │   │   ├──AuthenticationFailureHandler.java       * login fail handler, configrued in WebSecurityConfig
 │   │   │   │   │   ├──AuthenticationSuccessHandler.java       * login success handler, configrued in WebSecurityConfig
 │   │   │   │   │   ├──AnonAuthentication.java                 * it creates Anonymous user authentication object. If the user doesn't have a token, we mark the user as an anonymous visitor.
 │   │   │   │   │   ├──LogoutSuccess.java                      * controls the behavior after sign out.
 │   │   │   │   │   ├──RestAuthenticationEntryPoint.java       * logout success handler, configrued in WebSecurityConfig
 │   │   │   │   │   ├──TokenAuthenticationFilter.java          * the JWT token filter, configured in WebSecurityConfig
 │   │   │   │   │   └──TokenBasedAuthentication.java           * this is our custom Authentication class and it extends AbstractAuthenticationToken.
 │   │   │   │   └──TokenHelper.java                            * token helper class that responsible to token generation, validation, etc.
 │   │   │   ├──service
 │   │   │   │   ├──impl
 │   │   │   │   │   ├──CustomUserDetailsService.java           * custom UserDatilsService implementataion, tells formLogin() where to check username/password
 │   │   │   │   │   └──UserServiceImpl.java
 │   │   │   │   │   └──DocumentServiceImpl.java
 │   │   │   │   │   └──FicheEmpruntServiceImpl.java
 │   │   │   │   │   └──LocalisationServiceImpl.java
 │   │   │   │   │   └──MediathequeServiceImpl.java
 │   │   │   │   │   └──RappelServiceImpl.java
 │   │   │   │   │   └──AuthorityServiceImpl.java
 │   │   │   │   └──AuthorityService.java
 │   │   │   │   └──DocumentService.java
 │   │   │   │   └──Empruntable.java
 │   │   │   │   └──ficheEmpruntService.java
 │   │   │   │   └──LocalisationService.java
 │   │   │   │   └──MediathequeService.java
 │   │   │   │   └──RappelService.java
 │   │   │   │   └──UserService.java       
 │   │   │   └──Application.java                                * Application main enterance
 │   │   └──recources
 │   │       ├──static                                          * Angular2 frontend code will get built and served from here.
 │   │       ├──application.yml                                 * application variables are configured here
 │   │       ├──banner.txt                                      * application banner :^)
 │   │       └──import.sql                                      * h2 database query(table creation)
 │   └──test                                                    * Junit test folder
 └──pom.xml                                                     * what maven uses to manage it's dependencies
```

## Configuration
- **WebSecurityConfig.java**: The server-side authentication configurations.
- **application.yml**: Application level properties i.e the token expire time, token secret etc. You can find a reference of all application properties [here](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html).
- **JWT token TTL**: JWT Tokens are configured to expire after 10 minutes, you can get a new token by signing in again.
- **Using a different database**: This Starter kit is using an embedded H2 database that is automatically configured by Spring Boot. If you want to connect to another database you have to specify the connection in the *application.yml* in the resource directory. Here is an example for a MySQL DB:


```
spring:
  jpa:
    hibernate:
      # possible values: validate | update | create | create-drop
      ddl-auto: create-drop
  datasource:
    url: jdbc:mysql://localhost/myDatabase
    username: myUser
    password: myPassword
    driver-class-name: com.mysql.jdbc.Driver
```
*Hint: For other databases like MySQL sequences don't work for ID generation. So you have to change the GenerationType in the entity beans to 'AUTO' or 'IDENTITY'.*

### Generating password hash for users
We're using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords. Your can generate your hashes with this simple tool: [BCrypt Calculator](https://www.dailycred.com/article/bcrypt-calculator)
