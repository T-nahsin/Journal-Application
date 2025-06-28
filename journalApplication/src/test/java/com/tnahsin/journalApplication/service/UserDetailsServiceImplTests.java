package com.tnahsin.journalApplication.service;

import com.tnahsin.journalApplication.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTests {


    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((com.tnahsin.journalApplication.entity.User) User.builder().username("ram").password("34567ty").build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNull(user);
    }
}
