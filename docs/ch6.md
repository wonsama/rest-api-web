# ch6

## Entity 와 DTO의 분리

Entity는 매우 Core한 객체로 사용범위도 매우 광범위하고 모든 데이터를 갖고 있는 객체로 이러한 엔티티와 서비스가 의존관계를 갖게 하는것은 유지보수 측면에서나 관리측면에서 매우 부적합하다.

예시로 Entity의 "name"을 "username"으로 변경이 발생하면 Service영역의 코드까지 변경해야 하는등 영향이 매우 크다.

## 정리 - DTO와 Entity를 구별하지 않으면 생기는 문제점

> Controller에서는 Entity와 구분하여 API 요청에 맞는 DTO를 구현하여 사용하자.

- 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
  - 널값을 방어하기 위해 @NotNull 등을 달아줘야 하는 등 Entity에 대해 추가 제약이 붙는다
- API마다 필수 요소들이 다를수 있는데도 일률적으로 제한해야 한다.
- 엔티티에 API 검증을 위한 로직이 들어간다. (@NotEmpty, @NotNull)
- 엔티티가 변경되면 API 스펙이 같이 변한다.
  - 즉 api가 Entity에 의존적이게 된다.

## 맺음말

엔티티는 이제 UserJpaRepo에서만 사용되고 엔티티의 변경이 Controller나 Service에 가지않게 변경되었다.

엔티티의 이름이 변경되어도 Service에서 사용하는 Entity의 Get, Set 메서드만 이름에 맞춰 수정하면 되므로 영향이 매우 제한적이다.
