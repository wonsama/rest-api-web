package kr.co.sysnova.restapiweb.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    User findByEmail(String email);
}
