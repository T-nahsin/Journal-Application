package com.tnahsin.journalApplication.service;



import com.tnahsin.journalApplication.entity.User;
import com.tnahsin.journalApplication.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;


    @Disabled
    @ValueSource(strings =
    "ram"
    )
    @ParameterizedTest
    public void userNotFoundTest(String userName) {
        userRepository.findByUserName(userName);
    }
}
