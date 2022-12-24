package com.example.demo.ibatis;

import lombok.*;

import java.util.UUID;

@Setter @Getter @ToString
@AllArgsConstructor
public class TestEntity {

    private String id;
    private String username;
    private int age;

    public TestEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
