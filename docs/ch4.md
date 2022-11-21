# ch4

- 사용자를 등록할 때 동일한 값을 넣으면 오류가 발생한다.(키 증가가 되지 않음.)
- 오류가 발생하여도 시퀀스는 증가 한다.
- 매번 다른 값을 넣어주면 문제없이 생성이 됨

## 1. 리소스의 사용 목적에 맞게 HTTP Method 정의

> User 엔티티에 대해 먼저 http 메소드를 정의하자.

### 조회

GET /v1/users : 모든 회원 목록을 조회
GET /v1/user/{param} : param을 기준으로 회원을 조회
id, email, name 등에 따라서 단일 객체, 복수 객체가 반환된다.

### 등록

POST /v1/user : 회원 등록

### 수정

PUT /v1/user : 회원 정보 수정

### 삭제

DELETE /v1/user{userId} : 회원 삭제

## 2. 결과 데이터의 구조를 표준화해서 정의

기존 User 정보를 바탕으로 응답 데이터 구조 작성

```json
{
  "userId": 1,
  "email": "운식@이메일.com",
  "name": "최똥꼬"
}
```

User 정보를 바탕으로 응답 데이터 구성 (데이터 + api 요청 결과 데이터)

```json
{
    data: {
      "userId": 1,
      "email": "운식@이메일.com",
      "name": "최똥꼬"
    },
    "success": ture,
    "code": 0,
    "message": "성공하였습니다."
}
```

## 3. 응답 모델 3가지 구현

### 3.1. 공통 응답 모델

전달될 데이터와 별개로 API의 처리여부, 상태, 메시지가 담긴 데이터이다. 이 응답은 다른 모든 응답이 상속받아서 갖도록 한다.

```java
@Getter
@Setter
public class CommonResult {

    @ApiModelProperty(value = "응답 성공 여부: T/F")
    private boolean success;

    @ApiModelProperty(value = "응답 코드: >= 0 정상, < 0 비정상")
    private int code;

    @ApiModelProperty(value = "응답 메시지")
    private String msg;
}
```

### 3.2. 단일 응답 모델

```java
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}
```

### 3.3. 다중 응답 모델

```java
@Getter
@Setter
public class ListResult<T> extends CommonResult {
    private List<T> data;
}
```

## 4. API가 반환한 모델을 처리할 Service 구현

4.1. 공통 응답 모델을 Success / Fail로 처리하기위한 Enum 클래스를 생성한다.

```java
@Getter
@AllArgsConstructor
public enum CommonResponse {
    SUCCESS(0, "성공하였습니다."),
    FAIL(-1, "실패하였습니다.");

    private int code;
    private String msg;
}
```

4.2. 유형별로 ResponseService를 구현해준다.

```java
@Service
public class ResponseService {

    // 단일건 결과 처리 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 복수건 결과 처리 메서드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    // 실패 결과만 처리
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        setFailResult(result);
        return result;
    }

    // API 요청 성공 시 응답 모델을 성공 데이터로 세팅
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    // API 요청 실패 시 응답 모델을 실패 데이터로 세팅
    private void setFailResult(CommonResult result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
```

## 5. Http Method와 정형화된 주소체계로 Controller 구현

> 위에서 정의한 GET, POST, PUT, DELETE에 맞춰서 Mapping 테이블을 완성한다.
