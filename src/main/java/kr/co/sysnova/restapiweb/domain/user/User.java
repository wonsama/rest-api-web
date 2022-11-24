package kr.co.sysnova.restapiweb.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.sysnova.restapiweb.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user") // user 대신 tb_user 를 사용하도록 함
@Schema(description = "사용자")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "사용자 아이디(자동생성)")
    private Long userId;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    @Schema(description = "이메일", defaultValue = "wonsama@kakao.com")
    private String email;

    @Column(name = "name", nullable = false, length = 100)
    @Schema(description = "이름", defaultValue = "parkwon")
    private String name;

    @Column(name = "nick_name", nullable = true, length = 20)
    @Schema(description = "닉네임", defaultValue = "wonsama")
    private String nickName;
}
