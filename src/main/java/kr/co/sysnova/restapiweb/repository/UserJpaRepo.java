package kr.co.sysnova.restapiweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sysnova.restapiweb.entity.User;

public interface UserJpaRepo extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    User findByEmail(String email);
}
