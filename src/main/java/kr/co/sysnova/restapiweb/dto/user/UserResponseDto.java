package kr.co.sysnova.restapiweb.dto.user;

import kr.co.sysnova.restapiweb.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    // 응답은 setter 제거를 통해 무분별하게 수정되는 것을 방지한다

    private final Long userId;
    private final String email;
    private final String name;
    private final String nickName;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickName = user.getNickName();
    }

}
