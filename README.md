# README

REST API 를 학습하기 위한 프로젝트

챕터별 개별 branch 가 작성 되어 있으며, 해당 챕터단위로 branch를 변경 해보면서 학습을 진행 할 수 있습니다.

최종 작업 진행 사항은 물론 `main` branch 에 병합되어 있으니 참조 바랍니다.

> 출처 : [스프링 부트 REST API WEB 프로젝트](https://ws-pace.tistory.com/64)

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

(temp)

- h2

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
