package kr.co.sysnova.restapiweb.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sysnova.restapiweb.entity.User;
import kr.co.sysnova.restapiweb.repository.UserJpaRepo;

@Tag(name = "사용자", description = "사용자 관련 API")
@RequestMapping("/v1")
@RestController
public class UserController {

    @Autowired
    UserJpaRepo userJpaRepo;

    @Operation(summary = "모든 회원 조회", description = "모든 회원 목록을 조회합니다.")
    @GetMapping("/users")
    public List<User> findAllUser() {
        return userJpaRepo.findAll();
    }

    @Operation(summary = "회원 등록", description = "회원을 등록합니다.")
    @PostMapping("/user")
    public User save(@RequestParam String email, @RequestParam String name) {
        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        return userJpaRepo.save(user);
    }

    @Operation(summary = "회원 검색 (이름)", description = "이름으로 회원을 검색합니다.")
    @GetMapping("/findUserByName/{name}")
    public List<User> findUserByName(@PathVariable String name) {
        return userJpaRepo.findByName(name);
    }

    @Operation(summary = "회원 검색 (이메일)", description = "이름으로 회원을 검색합니다.")
    @GetMapping("/findUserByEmail/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userJpaRepo.findByEmail(email);
    }
}
