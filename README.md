# README

## 프로젝트 생성

테스트 환경

- OS : windows
- 자바 : open-jdk-11
- 프레임워크 : spring-boot 2.7.5
- 프로젝트 관리 도구 : maven
- 통합개발환경 : vscode 1.73

spring-boot 의존성

- spring-boot-starter-freemarker
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-web

(optional)

- spring-boot-starter-actuator
- spring-boot-devtools

추가 의존성

> `jjwt` moved to `jjwt-api`
>
> `junit` moved to `junit-jupiter-api`

- springfox-swagger2
- springfox-swagger-ui
- jjwt-api
- junit-jupiter-api
- gson
- lombok

## 테스트

앱 기동 (SERVER)

`mvn spring-boot:run`

## 사용하는 시점에 주석해제

`spring-boot-starter-security` - 자동적으로 basic auth 가 포함된다.
`spring-boot-starter-data-jpa` - datasource 설정이 반드시 되어야 된다.

## HOT DEPLOY

> 좀 더 찾아 봐야 될 것 같음. 소스 수정해도 재배포 안됨 음 ..
> 출처 : [VSCode 에서 Spring Boot Hot Swapping](https://velog.io/@nonz/VSCode-%EC%97%90%EC%84%9C-Spring-Boot-Hot-Swapping)

- ~~`spring-boot-devtools` 플러그인 추가~~
- ~~vscode 설정 : java > debug > settings : Hot Code Replace `auto`로 설정~~

## 출처

- [스프링 부트 REST API WEB 프로젝트](https://ws-pace.tistory.com/64)
