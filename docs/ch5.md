# ch5

## 예외처리 방법

일반적으로 예외처리를 하는 방법을 보면

- 예외가 날 수 있을만한 구간을 try ~ catch문을 사용해서 감싸준다.
- 요구조건을 통한 예외처리 (0 ~ 255 사이의 값만 valid하고 그 외는 예외처리)
- 스프링 시큐리티를 통해 인터셉터로 잡아서 UnauthoizedException과 같은 예외처리

기타 등등

## 예외처리 방식

@ExceptionHandler : 해당 클래스 내
@ContrllerAdvice : Controller 에서 발생하는 예외를 한곳에서 처리

### @ExceptionHandler

- @ExceptionHandler(Exception.calss1, Exception.calss2 ..) 식으로 2개 이상 등록해서 사용할 수 있다.
- @Controller, @RestController에만 사용가능하다. (@Service 등 다른 곳에서는 사용 X)
- 리턴 타입은 자유롭게 설정 가능하다. Controller 내 선언된 메서드들은 다양한 타입의 반환값을 가지는데 그 타입과 관계없이 설정할 수 있다.
- 현재 @Controller가 등록된 클래스 내에 있는 @ExceptionHandler만이 해당 클래스의 예외를 처리할 수 있다.
  - 즉, 예외처리를 하고싶은 클래스마다 따로 넣어줘야 한다.
- 예외처리를 하나로 다 처리하고싶으면 상위 예외타입인 Exception.class를 사용하면 된다.

### @ContrllerAdvice

아래와 같은 코드를 새로운 클래스로 만들고 annotation을 달아주면 모든 Controller에서 발생하는 예외를 처리하게 된다.
그리고 원하는 예외별로 @ExceptionHandler를 이용해서 메서드를 연결시켜주면 된다.

```java
@RestControllerAdvice
public class MyAdvice {
    @ExceptionHandler(Exception.class)
    public String custom() {
        return "hello Exception";
    }
}
```

패키지 활용

> 패키지를 적어주면 전역으로 에러를 처리하면서 패키지별로 에러를 담당

```java
@RestControllerAdvice("kr/co/sysnova/restapiweb/controller/v1")
```

## 통합 애러관리

- @ControllerAdvice를 이용해서 통합으로 에러 컨트롤을 하기 위해서 주의해야할 점이 있다. 무엇보다 에러 메시지가 잘 정의되어 있어야 한다
- 또한, 코드를 작성할 때도 조건문을 통해 잘못된 케이스는 케이스에 맞춰서 throw new XXXException();을 호출해 버리면 되므로 유지보수 관점에서도 매우 좋다.

```java
@Getter
@AllArgsConstructor
public enum ErrorCode {
    OperationNotAuthorized(6000, "Operation not authorized"),
    DuplicatedIdFound(6001, "Duplicate Id"),
    DuplicatedEmailFound(6002, "Duplicate Email"),
    //...
    UnrecognizedRole(6010, "Unrecognized Role");

    private int code;
    private String description;
}
```
