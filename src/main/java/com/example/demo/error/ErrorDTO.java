package com.example.demo.error;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDTO {

    private String message; // 에러메세지를 담을거임 , 에러내용을 제이슨으로 보내야함.



}
