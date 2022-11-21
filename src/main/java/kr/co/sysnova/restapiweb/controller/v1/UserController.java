package kr.co.sysnova.restapiweb.controller.v1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sysnova.restapiweb.advice.exception.UserNotFoundException;
import kr.co.sysnova.restapiweb.entity.User;
import kr.co.sysnova.restapiweb.repository.UserJpaRepo;
import kr.co.sysnova.restapiweb.response.CommonResult;
import kr.co.sysnova.restapiweb.response.ListResult;
import kr.co.sysnova.restapiweb.response.SingleResult;
import kr.co.sysnova.restapiweb.service.ResponseService;
import lombok.RequiredArgsConstructor;

@Tag(name = "사용자", description = "사용자 관련 API")
@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @Operation(summary = "회원 단건 검색", description = "userId로 회원을 조회합니다.")
    @GetMapping("/user/{userId}")
    public SingleResult<User> findUserByKey(
            @Parameter(name = "userId", required = true) @PathVariable Long userId) throws Exception {
        // 값이 없으면 값을 null 로 설정하여 성공 처리 된다
        return responseService.getSingleResult(userJpaRepo.findById(userId).orElse(null));
        // return
        // responseService.getSingleResult(userJpaRepo.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Operation(summary = "회원 단건 검색", description = "userId로 회원을 조회합니다.")
    @GetMapping("/user/id/{userId}")
    public SingleResult<User> findUserById(@Parameter(name = "회원 ID", required = true) @PathVariable Long userId) {
        // 값이 없으면 오류를 발생 시킨다
        return responseService.getSingleResult(userJpaRepo.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Operation(summary = "회원 단건 검색 (이메일)", description = "이메일로 회원을 조회합니다.")
    @GetMapping("/user/email/{email}")
    public SingleResult<User> findUserByEmail(@Parameter(name = "회원 이메일", required = true) @PathVariable String email) {
        User user = userJpaRepo.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException();
        else
            return responseService.getSingleResult(user);
    }

    @Operation(summary = "회원 목록 조회", description = "모든 회원을 조회합니다.")
    @GetMapping("/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @Operation(summary = "회원 등록", description = "회원을 등록합니다.")
    @PostMapping("/user")
    public SingleResult<User> save(
            @Parameter(name = "email", required = true) @RequestParam String email,
            @Parameter(name = "name", required = true) @RequestParam String name) {
        User user = User.builder()
                .email(email)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @Operation(summary = "회원 수정", description = "회원 정보를 수정합니다.")
    @PutMapping("/user")
    public SingleResult<User> modify(
            @Parameter(name = "userId", required = true) @RequestParam Long userId,
            @Parameter(name = "email", required = true) @RequestParam String email,
            @Parameter(name = "name", required = true) @RequestParam String name) {
        User user = User.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .build();

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    @DeleteMapping("/user/{userId}")
    public CommonResult delete(@Parameter(name = "userId", required = true) @PathVariable Long userId) {
        userJpaRepo.deleteById(userId);
        return responseService.getSuccessResult();
    }
}