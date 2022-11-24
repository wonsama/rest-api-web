package kr.co.sysnova.restapiweb.config.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    // @Value("spring.jwt.secret")
    // private String secretKey;

    private Long tokenValidMillisecond = 60 * 60 * 1000L;

    private final CustomUserDetailsService userDetailsService;

    private SecretKey key;

    @PostConstruct
    protected void init() {
        // secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // or HS384 or HS512
    }

    // Jwt 생성
    public String createToken(String userPk, List<String> roles) {

        // user 구분을 위해 Claims에 User Pk값 넣어줌
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        // 생성날짜, 만료날짜를 위한 Date
        Date now = new Date();

        // see more :
        // https://stackoverflow.com/questions/40252903/static-secret-as-byte-key-or-string/40274325#40274325
        // https://github.com/jwtk/jjwt#quickstart
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                // .signWith(SignatureAlgorithm.HS256, secretKey)
                .signWith(key)
                .compact();
    }

    // Jwt 로 인증정보를 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // jwt 에서 회원 구분 Pk 추출
    public String getUserPk(String token) {
        // return
        // Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    // HTTP Request 의 Header 에서 Token Parsing -> "X-AUTH-TOKEN: jwt"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // jwt 의 유효성 및 만료일자 확인
    public boolean validationToken(String token) {
        try {
            // Jws<Claims> claimsJws =
            // Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date()); // 만료날짜가 현재보다 이전이면 false
        } catch (Exception e) {
            return false;
        }
    }
}