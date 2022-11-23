package kr.co.sysnova.restapiweb.dto.user;

import kr.co.sysnova.restapiweb.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String email;
    private String name;
    private String nickName;

    public User toEntity() {
        return User.builder().email(email).name(name).nickName(nickName).build();
    }
}
