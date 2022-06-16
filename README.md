# Ber
## Oauth 2.0(구글, 네이버)

### 작업 환경
- gradle
- Spring Data Jpa
- JDK11
- intelliJ
- h2
- mysql -> RDS
- yml(DB, JPA, thread) + properties(Security)

- domain 단위 패키지 구조 -> layer 단위 패키지 구조
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
	implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.session:spring-session-core'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	runtimeOnly 'mysql:mysql-connector-java'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
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
    url: jdbc:mysql://localhost:3306/DB 이름?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: 비밀번호

  security:
    jwt:
      header: Authorization
      secret: c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK
      token-validity-in-seconds: 86400

  jpa:
    database: mysql
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    generate-ddl: true
    hibernate:
      ddl-auto: create-drop
    show_sql: true
    format_sql: true

#    default_batch_fetch_size: 1000

logging.level:
  org.hibernate.SQL: debug
  org.hibernate.type: trace
  # parameter Binding

```

***

## ERD
![ber-erd](https://user-images.githubusercontent.com/94087228/174112064-d0eb55e3-b31b-418a-95e6-bc94d59e6768.png)
