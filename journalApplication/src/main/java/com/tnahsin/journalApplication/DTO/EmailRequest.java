package com.tnahsin.journalApplication.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String body;

}