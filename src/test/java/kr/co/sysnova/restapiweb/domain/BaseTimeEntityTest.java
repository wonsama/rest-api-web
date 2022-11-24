package kr.co.sysnova.restapiweb.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sysnova.restapiweb.domain.user.User;
import kr.co.sysnova.restapiweb.domain.user.UserJpaRepo;

// see more : https://cjred.net/2020-04-30-junit-5-runwith-extendwith/

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseTimeEntityTest {

    @Autowired
    UserJpaRepo userJpaRepo;

    @Test
    public void BaseTimeEntity_등록() throws Exception {
        // given
        LocalDateTime now = LocalDateTime
                .of(2021, 8, 5, 22, 31, 0);
        userJpaRepo.save(User.builder()
                .name("운식")
                .email("운식@이메일.com")
                .build());

        // when
        List<User> users = userJpaRepo.findAll();

        // then
        User user = users.get(0);

        System.out.println(">>>>>>> createDate = " + user.getCreatedDate()
                + ", modifiedDate = " + user.getModifiedDate());

        Assertions.assertThat(user.getCreatedDate()).isAfter(now);
        Assertions.assertThat(user.getModifiedDate()).isAfter(now);
    }
}
