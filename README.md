mybank/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mybank/
│   │   │           ├── MybankApplication.java         # punctul de intrare
│   │   │
│   │   │           ├── config/                        # configurări globale
│   │   │           │   ├── SecurityConfig.java        # acces, autentificare
│   │   │           │   └── SwaggerConfig.java         # documentație API
│   │   │
│   │   │           ├── controller/                    # endpointuri REST
│   │   │           │   ├── PublicBankController.java  # acces liber
│   │   │           │   ├── CreditController.java      # gestionare credite
│   │   │           │   ├── DepositController.java     # gestionare depozite
│   │   │           │   └── UserController.java        # gestionare utilizatori
│   │   │
│   │   │           ├── entity/                        # entități JPA
│   │   │           │   ├── User.java
│   │   │           │   ├── Credit.java
│   │   │           │   └── Deposit.java
│   │   │
│   │   │           ├── model/                         # DTO-uri și modele de date model sau dto
│   │   │           │   ├── CreditRequest.java
│   │   │           │   ├── CreditResponse.java
│   │   │           │   ├── DepositRequest.java
│   │   │           │   ├── DepositResponse.java
│   │   │           │   └── UserDTO.java
│   │   │
│   │   │           ├── repository/                    # acces la baza de date
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── CreditRepository.java
│   │   │           │   └── DepositRepository.java
│   │   │
│   │   │           ├── service/                       # logică de business
│   │   │           │   ├── CreditService.java
│   │   │           │   ├── DepositService.java
│   │   │           │   └── UserService.java
│   │   │
│   │   │           └── exception/                     # gestionare erori
│   │   │               ├── GlobalExceptionHandler.java
│   │   │               └── ResourceNotFoundException.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties                 # configurări Spring
│   │       ├── static/                                # fișiere UI (HTML, CSS, JS)
│   │       └── templates/                             # pagini Thymeleaf (dacă folosești)
│
├── test/                                              # teste unitare și de integrare
│   └── java/
│       └── com/
│           └── mybank/
│               ├── CreditServiceTest.java
│               └── UserControllerTest.java
│
├── target/                                            # generat automat
│
├── pom.xml                                            # configurare Maven
├── Dockerfile                                         # imagine Docker
└── docker-compose.yml                                 # orchestrare containere


## In clasa

Securitate:
Rolul este o clasa, in Model, in model are propria clasa, Rol are privet UUID rol : rol enum, care are :admin, banc, 
client, rolul are clasa separata.

cd D:\Anastasia\Study\USM\Anul3\Semestru1\Spring\mybank\mybank

## Docker

    opresc containerul:
    docker-compose down -v
    
    Reconstruiește aplicația:
    mvn clean package -DskipTests
    docker-compose up --build
    
    pornește, verifică
    docker ps
    
    testare:
    http://localhost:8080




TODO:
de la prof:
SecurityConfig: 
in security sa avem :FilterChainExceptionHandrler(cand apare vrio eroare, cu doFilterInternal- ErrorRespons-cu 
bilder si title,details(reqest.get).respons.getWrite().write()),convetarea in JSON(object),cu verificarea daca 
obiectul este null{}, convertirea errorRespons si daca e ok atunci OK ,si in 
lAa fel FilterChanExceptionHandler
UserdetailsService

SecurityFilterChain(){ csrf desible.AuthorizasionHttpReqest
}
Cors
exeptii: 
ErrorRespons(title si details), 
- SecuritiConfig: public SecurityFilterChain (HTTPsSecurity)
- in security-clasa JwtUserDetailService{}
- implimentam userDetails- (public class JwtUser implement UserDetails): getUserId.... IsAcountnonExpared in git 
  ignore...
-JWTUserFactory(return new JwtUser(
 user.getUser.....)ca sa avem un user cu mai multe roluri

- //@Bean  public AutentificationProvider: @Bean. (un user- setUserDetailsService. cliptarea parolelor-bcript 
  returnam autentificatorProvider.

- filtrari care (.andFilterException(filterCainnelHandler)))) and Corsfilter si jwtAuthFilter, Username return http.
  build

La JwtUserDetails(sa poate loauduserByUsername) daca nu gaseste un user- atunci user not found
 - in UserNotFoundException extend RunTimeException- not foun 404 , astfel avemJwtUserDetails

avem dejaexceptie, eroare
Vom mai face JwtAuthFilter