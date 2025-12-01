# 💳 MyBank — Aplicație bancară modulară cu Spring Boot și Docker

Aplicație didacticăpentru gestionarea utilizatorilor, rolurilor, creditelor și depozitelor, cu securitate JWT, mapări JPA

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

# 📦 Structura

- **Backend:** Spring Boot 3.5.6, Java 21
- **Persistență:** PostgreSQL 15, HikariCP
- **Entități:** `User`, `Role`, `Credit`, `Deposit`
- **Relații JPA:**
    - `User` ↔ `Role`: `@ManyToOne`
    - `User` ↔ `Credit`: `@ManyToMany` (bidirecțională, cu `@JoinTable`)
    - `User` ↔ `Deposit`: `@OneToMany`
- **Securitate:**
    - JWT + `@PreAuthorize`
    - Roluri: `ADMIN`, `CLIENT`
    - Validare înregistrare: `confirmPassword`, `@NotNull`, `@Column(unique)`
- **Entități:**
    - `User` ↔ `Role`: `@ManyToOne`
    - `User` ↔ `Credit`: `@ManyToMany` (bidirecțională, cu `@JoinTable`)
    - `User` ↔ `Deposit`: `@OneToMany`
- **Persistență:** PostgreSQL 15, HikariCP
- **Docker:** `docker-compose.yml` cu `mybank-app` și `mybank-db`
- **Testare:** Postman pentru endpointuri `auth/register`, `auth/login`

---

# ✅ Ce am realizat

- Eliminat conflictul de bean-uri `SecurityConfig`
- Corectat maparea `@ManyToMany` cu `@JoinTable` între `Credit` și `User`
- Adăugat mapare bidirecțională în `User.java`
- Reconstruit imaginea Docker și expus portul `8080`
- Testat înregistrarea și login-ul în Postman
- Documentat erorile Hibernate și soluțiile aplicate

---

#  Ce urmează

- [ ] Populare automată a rolurilor în baza de date (`ADMIN`, `CLIENT`)
- [ ] Implementare endpointuri protejate cu `@PreAuthorize`
- [ ] Adăugare câmpuri de audit (`createdAt`, `updatedBy`) în entități
- [ ] Integrare design patterns: `Builder`, `Decorator`, `Adapter`, `Bridge`
- [ ] Adăugare filtre și căutare în UI pentru entități

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

### In clasa:

- JVT-nu unem in jwtAuthFilter- nu punem date sensibile


## Realizari pana in 25 octobrie

- extend: DepositType - ca sa pot avea mai multe tipuri de depozite, acelasi lucru va fi si pentru credite
- doar cu userul care in creaza "ADMIN", "BANK" poti crea depozite. 
- La fel doar cu userul care in creaza "ADMIN", "BANK" va crea credite

## AM REALIZAT 08.11.25:
Dacă totul merge în Postman — inclusiv logarea — înseamnă că ai reușit să:
- ✅ Configurezi corect JWT (cheie secretă, expirare, filtre)
- ✅ Salvezi utilizatorul cu rolul potrivit (CLIENT, ADMIN, etc.)
- ✅ Generezi și validezi tokenul în AuthController
- ✅ Protejezi endpointurile cu SecurityFilterChain și @PreAuthorize sau .hasRole(...)
- ✅ Testezi cu succes în Postman: login, acces cu token, refresh

## TODO:
PROPUNERE DE ORDINE IMPLEMENTARE
- validările pentru acordarea creditului
- ✅ Finalizează entitățile și relațiile (User, Role, Credit, Deposit, DepositType)
- 🔐 Configurează Spring Security (login, logout, roluri)
- 🧾 Creează DTO-uri pentru toate entitățile (Request + Response)
- 🛠️ Implementează validările la credit (vârstă, venit, etc.)
- 🧑‍⚖️ Adaugă restricții pe roluri în controller/service
-+ 🚨 Configurează handler global pentru erori
