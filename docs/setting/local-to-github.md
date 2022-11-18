# local-to-github

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
