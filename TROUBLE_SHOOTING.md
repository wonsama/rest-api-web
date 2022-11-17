# TROUBLE SHOOTING

## Adding locally hosted code to GitHub

> [Adding locally hosted code to GitHub](https://docs.github.com/en/get-started/importing-your-projects-to-github/importing-source-code-to-github/adding-locally-hosted-code-to-github)

```sh
# INSTALL GITHUB-CLI
# https://github.com/cli/cli#installation
# (in windows) cmd 를 관리자 권한으로 실행
# cmd 를 재 오픈하면 아래 명령을 사용할 수 있다.
# git-bash 에서는 아마 path가 없어서 사용불가 할 수 있음에 유의
choco install gh

# github 로그인
gh auth login
# Github.com - HTTPS - Login with a web browser - 화면상 코드를 웹에 넣고 인증완료

# 저장소 생성하기
# --public / --private 선택 가능
gh repo create wonsama/rest-api-web --public

# 저장소 초기화 및 커밋
git init -b main
git add . && git commit -m "initial commit"
git remote add origin git@github.com:wonsama/rest-api-web.git

# [참조] 현재 폴더의 GIT 원격 저장소 확인
git remote -v

# 저장소 푸시
git push origin main
```

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
