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
import kr.co.sysnova.restapiweb.dto.user.UserRequestDto;
import kr.co.sysnova.restapiweb.dto.user.UserResponseDto;
import kr.co.sysnova.restapiweb.model.response.CommonResult;
import kr.co.sysnova.restapiweb.model.response.ListResult;
import kr.co.sysnova.restapiweb.model.response.SingleResult;
import kr.co.sysnova.restapiweb.service.ResponseService;
import kr.co.sysnova.restapiweb.service.UserService;
import lombok.RequiredArgsConstructor;

@Tag(name = "사용자", description = "사용자 관련 API")
@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class UserController {

    // 요청을 보낼때는 UserRequestDto 를 생성해서 보낸다.
    // 요청을 받을때는 UserResponseDto 를 통해 받는다

    private final UserService userService;
    private final ResponseService responseService;

    @Operation(summary = "회원 단건 검색", description = "userId로 회원을 조회합니다.")
    @GetMapping("/user/id/{userId}")
    public SingleResult<UserResponseDto> findUserById(
            @Parameter(name = "userId", required = true) @PathVariable Long userId) {
        return responseService.getSingleResult(userService.findById(userId));
    }

    @Operation(summary = "회원 단건 검색 (이메일)", description = "이메일로 회원을 조회합니다.")
    @GetMapping("/user/email/{email}")
    public SingleResult<UserResponseDto> findUserByEmail(
            @Parameter(name = "email", required = true) @PathVariable String email) {
        return responseService.getSingleResult(userService.findByEmail(email));
    }

    @Operation(summary = "회원 목록 조회", description = "모든 회원을 조회합니다.")
    @GetMapping("/users")
    public ListResult<UserResponseDto> findAllUser() {
        return responseService.getListResult(userService.findAllUser());
    }

    @Operation(summary = "회원 등록", description = "회원을 등록합니다.")
    @PostMapping("/user")
    public SingleResult<Long> save(
            @Parameter(name = "email", required = true) @RequestParam String email,
            @Parameter(name = "name", required = true) @RequestParam String name) {
        UserRequestDto user = UserRequestDto.builder()
                .email(email)
                .name(name)
                .build();
        return responseService.getSingleResult(userService.save(user));
    }

    @Operation(summary = "회원 수정", description = "회원 정보를 수정합니다.")
    @PutMapping("/user")
    public SingleResult<Long> update(
            @Parameter(name = "userId", required = true) @RequestParam Long userId,
            @Parameter(name = "nickName", required = true) @RequestParam String nickName) {
        UserRequestDto user = UserRequestDto.builder()
                .nickName(nickName)
                .build();

        return responseService.getSingleResult(userService.update(userId, user));
    }

    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    @DeleteMapping("/user/{userId}")
    public CommonResult delete(@Parameter(name = "userId", required = true) @PathVariable Long userId) {
        userService.delete(userId);
        return responseService.getSuccessResult();
    }
}