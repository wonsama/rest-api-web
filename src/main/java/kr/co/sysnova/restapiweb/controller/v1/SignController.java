package kr.co.sysnova.restapiweb.controller.v1;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sysnova.restapiweb.config.security.JwtProvider;
import kr.co.sysnova.restapiweb.model.response.SingleResult;
import kr.co.sysnova.restapiweb.service.ResponseService;
import kr.co.sysnova.restapiweb.service.UserService;
import lombok.RequiredArgsConstructor;

@Tag(name = "1. SignUp / LogIn")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class SignController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "로그인", description = "이메일로 로그인을 합니다.")
    @GetMapping("/login")
    public SingleResult<String> login(
            @Parameter(description = "로그인 아이디 : 이메일") @RequestParam String email,
            @Parameter(description = "로그인 비밀번호") @RequestParam String password) {
        UserLoginResponseDto userLoginDto = userService.login(email, password);

        String token = jwtProvider.createToken(String.valueOf(userLoginDto.getUserId()), userLoginDto.getRoles());
        return responseService.getSingleResult(token);
    }

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @PostMapping("/signup")
    public SingleResult<Long> signup(
            @Parameter(description = "회원 가입 아이디 : 이메일") @RequestParam String email,
            @Parameter(description = "회원 가입 비밀번호") @RequestParam String password,
            @Parameter(description = "회원 가입 이름") @RequestParam String name,
            @Parameter(description = "회원 가입 닉네임") @RequestParam String nickName) {

        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickName(nickName)
                .build();
        Long signupId = userService.signup(userSignupRequestDto);
        return responseService.getSingleResult(signupId);
    }
}
