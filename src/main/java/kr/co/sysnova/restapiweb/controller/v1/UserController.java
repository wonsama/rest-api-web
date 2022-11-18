package kr.co.sysnova.restapiweb.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sysnova.restapiweb.entity.User;
import kr.co.sysnova.restapiweb.repository.UserJpaRepo;

@RestController
public class UserController {

    @Autowired
    UserJpaRepo userJpaRepo;

    @PostMapping("/user")
    public User save() {
        User user = User.builder().email("wonsama@kakao.com").name("wonsama").build();
        return userJpaRepo.save(user);
    }

    @GetMapping("/findUser/{name}")
    public User findUserByName(@PathVariable String name) {
        return userJpaRepo.findByName(name);
    }
}
