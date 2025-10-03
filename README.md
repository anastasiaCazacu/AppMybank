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