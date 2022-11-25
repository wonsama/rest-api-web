package kr.co.sysnova.restapiweb.dto.user;

import java.util.Collections;

import kr.co.sysnova.restapiweb.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickName;

    @Builder
    public UserSignupRequestDto(String email, String password, String name, String nickName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}