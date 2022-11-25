package kr.co.sysnova.restapiweb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.co.sysnova.restapiweb.advice.exception.UserNotFoundException;
import kr.co.sysnova.restapiweb.domain.user.UserJpaRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepo userJpaRepo;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        return userJpaRepo.findById(Long.parseLong(userPk)).orElseThrow(UserNotFoundException::new);
    }
}
