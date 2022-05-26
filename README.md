# Ber1
## Oauth 2.0(구글, 네이버)

### 작업 환경
- gradle
- Spring Data Jpa
- JDK11
- intelliJ
- h2
- mysql -> RDS
- yml(DB, JPA, thread) + properties(Security)
***

### build.gradle

```java
plugins {
	id 'org.springframework.boot' version '2.7.0'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'
}

group = 'bssm'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
//	implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
//	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.session:spring-session-core'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	runtimeOnly 'mysql:mysql-connector-java'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
//	testImplementation 'org.springframework.security:spring-security-test'
}

tasks.named('test') {
	useJUnitPlatform()
}
```

***

## application.yml - h2
```java
  spring:
    datasource:
      url: jdbc:h2:tcp://localhost/~/DB이름
      username: sa
      password: 비밀번호
      driver-class-name: org.h2.Driver
    
    jpa:
      hibernate:
        ddl-auto: create-drop
      properties:
        hibernate:
          show_sql: true
          format_sql: true
          default_batch_fetch_size: 1000
  
  logging.level:
    org.hibernate.SQL: debug
  #  org.hibernate.type: trace
  ```
  <img width="428" alt="스크린샷 2022-05-25 오후 7 46 41" src="https://user-images.githubusercontent.com/94087228/170245190-3aabeaa3-de7d-4195-9c0f-540ff474f9d6.png">
  
***

## application.yml - mysql
```java
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:포트번호//DB이름?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: 비밀번호


  jpa:
    database: mysql
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    generate-ddl: true
    hibernate:
      ddl-auto: create
    show_sql: true
    format_sql: true

#    default_batch_fetch_size: 1000

logging.level:
  org.hibernate.SQL: debug
  org.hibernate.type: trace
  # parameter Binding
```
<img width="198" alt="스크린샷 2022-05-25 오후 7 50 15" src="https://user-images.githubusercontent.com/94087228/170245775-89f07e9d-5fd8-4c31-ac28-8eb2e6103857.png"> <img width="542" alt="스크린샷 2022-05-25 오후 7 41 54" src="https://user-images.githubusercontent.com/94087228/170244276-d81f91ad-09a9-47c8-baba-df8d74520d41.png">

