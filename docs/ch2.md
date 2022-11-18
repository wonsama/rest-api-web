# ch2

## hibernate.hbm2ddl.auto 옵션

create : 애플리케이션이 켜질 때 DB도 지우고 새로 만든다
none : 아무것도 하지 않으므로 DB에 값이 유지
create-drop : 시작할 때 db를 만드는 것은 동일하나, 종료시 삭제까지 한다
update : 서버 시작 시 Entiry와 Table을 비교해서 변경된 내용을 감지한다. table이 없다면 생성해준다
validate : 서버 시작 시 Entity와 Table이 다르면 시작하지 않고 종료한다

## annotation

@Builder

- 디자인 패턴 중 하나인 빌더패턴방식으로 객체를 생성할 수 있게해준다.

@Entity

- @Entity가 붙은 클래스는 JPA가 관리하는 클래스가 되고, 해당 클래스를
- JPA를 사용해서 테이블과 매핑할 클래스는 반드시 @Entity를 붙여야 한다.

@Id

- private key 임을 명시해준다

@GeneratedValue

- pk 값의 생성을 DB에게 위임한다

@Table

- Entity를 JPA가 관리하도록 했고, 해당 엔티티를 테이블과 매핑하기 위해 테이블을 지정해 주는 것이다
- name은 테이블 명이 된다. ( default : 엔티티명 )

@Getter, @Setter

- lombok을 사용해서 자동으로 get, set 메서드를 생성해준다

@NoArgsConstructor, @AllArgsConstructor, @RequiredConstructor

- No : 인자가 없는 생성자를 만들어 준다.
- All : 필드의 모든 값을 인자로 생성자를 만들어 준다. ( No랑 같이 사용 불가 )
- Required : final 로 선언된 값이 필수적인 것만 인자로 받는 생성자를 만들어 준다

## JPARepository

JpaRepository 라는 Spring Data Jpa를 사용하면
이러한 일반적으로 통용되는 DAO에 대한 CRUD메소드를 전부 구현 해 놨다

EntityRepository 인터페이스를 생성 후 `JapRepository<Entitiy class 명, key TYPE>` 을 상속받는다.
이때, Entity 필드값으로 조회 메서드를 추가하고 싶다면
<반환타입> findBy{변수명} (<타입> 인자명) 을 선언만 해주면 알아서 JPA가 구현해 준다.

기본적으로 findAll, save, update ... 다양한 메소드는 기본적으로 제공 해준다

## 참조링크

- [Spring Data – CrudRepository save() Method](https://www.baeldung.com/spring-data-crud-repository-save)

## 문제점

save() 를 호출하면 한번은 되지만 그 이상은 되지 않음, 문제는 user_id 값이 1로 지속 설정 되기 때문 auto_increasement 화 되어야 되는데... 흠
우선적으로는 PASS 함. ( 다음에는 mysql 등과 같은 rdbms 를 사용할 것이기 때문 )
