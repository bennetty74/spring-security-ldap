# Spring-Security-LDAP

## Description

An `LDAP` demo with customizing login business logic

## Function

- [x] Customize login business logic in `LoginController`
- [ ] Get Authorities in self Database (`MySQL`) with `ORM` framework `MyBatis`

## Get started

## Run

Run `SpringSecurityApplication` . The default application URL is `http://127.0.0.1:9000/spring-security-ldap`

## Test login and home access

1. Use `postman` to perform a login request with method `POST` , URL `http://127.0.0.1:9000/spring-security-ldap/login` and request body

```json
{
    "username":"user",
    "password": "password"
}
```

2. If the step 1 returns `success`, then perform a home access request with method `GET` and URL `http://127.0.0.1:9000/spring-security-ldap/`. You will get result `Hello Spring Security LDAP`

