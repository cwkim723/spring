package com.hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloData {
    // json은 객체로 파싱해서 사용
    private String username;
    private int age;

}
