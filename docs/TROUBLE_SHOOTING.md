# TROUBLE SHOOTING

## [오류해결-swagger] Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException

> [Failed to start bean 'documentationPluginsBootstrapper'](https://goyunji.tistory.com/137)

Spring boot 2.6버전 이후에 spring.mvc.pathmatch.matching-strategy 값이 ant_apth_matcher에서 path_pattern_parser로 변경되면서 몇몇 라이브러리에서 오류가 발생하고 있다!
application.properties 파일에서 한 줄을 추가하자.

```properties
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```

## 파일 경로 바꾸기

`url: jdbc:h2:~/rest-api-web`

이런식으로 설정 되어 있는데 혹시나 `rest-api-web` 파일이 사용중이다 이런식으로 나오면 다른 파일 경로로 바꿔서 사용하기를 바랍니다.

## h2 scope 변경하기

application 동작 시 `scpoe` 을 `test` 가 아닌 `runtime` 으로 변경 해줘야 된다.
그렇지 않으면 `Cannot load driver class: org.h2.Driver` 와 같은 오류 메시지를 확인 할 수 있다.
(보통 h2 는 실제 DB 연결 보다는 임시로 사용하는 DDL 및 간단 테스트 용도로 많이 사용되기 때문)

## h2 에서 user

> 출처 : [stackoverflow](https://stackoverflow.com/questions/71722483/org-h2-jdbc-jdbcsqlsyntaxerrorexception-syntax-error-in-sql-statement-drop-tab)

user 는 예약 키워드임에 유의 "User" 를 사용하거나 다른 단어를 사용해야 된다

## Spring Boot 최초 실행 시 'Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured. '

> `spring-boot-starter-data-jpa` 의존성을 추가하면 반드시 datasource 설정을 해줘야 한다
> 우선 주석 처리 하고 사용할 때 관련 정보를 yml 또는 properties 에 추가하도록 하자

```yml
# application.properties 에 다음과 같이 JDBC url을 추가해준다. [Mysql 일 경우] (Oracle이나 다른 DB는 응용하세요.)

# DataSource
spring.datasource.url=jdbc:mysql://localhost:3306/[DB스키마명]?autoReconnect=true
spring.datasource.username=[DB접속Id]
spring.datasource.password=[DB접속Password]
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
