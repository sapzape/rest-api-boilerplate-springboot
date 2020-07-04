# REST API Boilerplate using Spring Boot

## Introduction

`Spring Boot` + `JPA` + practice repository for learning REST API server.

The features are as follows.

1. [JDK 11](https://adoptopenjdk.net/). (version 8 or higher can be used.)
2. [Spring Boot 2.1.7.RELEASE]()
3. [Gradle 4 or higher](https://gradle.org/)
4. Apply ORM with [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
5. Apply tests using [JUnit 4](https://junit.org/junit4/)
6. Apply [Lombok](https://projectlombok.org/)
7. Apply [MapStruct](https://mapstruct.org/)

## Example

```
{
  "type": "DIAGNOSIS",
  "applyDate": "2020-07-03",
  "recommendCode": "ABCDEFG",
  "memberName": "Member Kim",
  "memberType": "INDIVIDUAL",
  "cellPhone": "010-1111-2222",
  "email": "mail@abc.de",
  "memo": "Memo Test",
  "address": {
    "streetNameAddress1": "Somewhere in Gangnam-gu, Seoul",
    "streetNameAddress2": "What Apartment No. 101, 1st floor No. 101",
    "zipCode": "123456",
    "oldAddress1": "Somewhere in Gangnam-gu, Seoul",
    "oldAddress2": "What Apartment No. 101, 1st floor No. 101"
  },
  "chargeManager": "Manager Kim"
}
```
