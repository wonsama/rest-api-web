package kr.co.sysnova.restapiweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import kr.co.sysnova.restapiweb.config.security.JwtProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;

    // see more : https://devlog-wjdrbs96.tistory.com/434

}
