package kr.co.sysnova.restapiweb.dto.user;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.sysnova.restapiweb.domain.user.User;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private final Long userId;
    private final List<String> roles;
    private final LocalDateTime createdDate;

    public UserLoginResponseDto(User user) {
        this.userId = user.getUserId();
        this.roles = user.getRoles();
        this.createdDate = user.getCreatedDate();
    }
}