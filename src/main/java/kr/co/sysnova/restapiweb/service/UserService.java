package kr.co.sysnova.restapiweb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sysnova.restapiweb.advice.exception.EmailLoginFailedException;
import kr.co.sysnova.restapiweb.advice.exception.UserNotFoundException;
import kr.co.sysnova.restapiweb.domain.user.User;
import kr.co.sysnova.restapiweb.domain.user.UserJpaRepo;
import kr.co.sysnova.restapiweb.dto.user.UserLoginResponseDto;
import kr.co.sysnova.restapiweb.dto.user.UserRequestDto;
import kr.co.sysnova.restapiweb.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserJpaRepo userJpaRepo;

    // GET요청에 대한 응답으로 UserResponseDto를 반환
    // POST, PUT 요청에 대해서는 UserRequestDto를 받도록 함

    @Transactional
    public Long save(UserRequestDto userDto) {
        userJpaRepo.save(userDto.toEntity());
        return userJpaRepo.findByEmail(userDto.getEmail()).getUserId();
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userJpaRepo.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        User user = userJpaRepo.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException();
        else
            return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUser() {
        return userJpaRepo.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, UserRequestDto userRequestDto) {
        User modifiedUser = userJpaRepo
                .findById(id).orElseThrow(UserNotFoundException::new);
        modifiedUser.setNickName(userRequestDto.getNickName());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        userJpaRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UserLoginResponseDto login(String email, String password) {
        User user = userJpaRepo.findByEmail(email).orElseThrow(EmailLoginFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new EmailLoginFailedCException();
        return new UserLoginResponseDto(user);
    }

    @Transactional
    public Long signup(UserSignupRequestDto userSignupDto) {
        if (userJpaRepo.findByEmail(userSignupDto.getEmail()).orElse(null) == null)
            return userJpaRepo.save(userSignupDto.toEntity()).getUserId();
        else
            throw new EmailSignupFailedException();
    }
}
