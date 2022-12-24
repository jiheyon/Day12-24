package com.example.demo.ibatis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// junit5
@SpringBootTest
class TestRepositoryTest {

    @Autowired TestRepository repository;

    @Test
    @DisplayName("유저이름과 나이가 TBL_TEST테이블에 저장되어야 한다")
    void saveTest() {
        //given : 테스트상황에 주어질 데이터
        TestEntity entity = new TestEntity();
        entity.setUsername("김영자");
        entity.setAge(50);

        //when: 실제 테스트 실행
        boolean flag = repository.save(entity);

        //then: 테스트 결과 단언
        assertTrue(flag);
        assertNotNull(entity.getId());
    }

    @Test
    @DisplayName("유저이름과 나이를 수정해야 한다.")
    void modifyTest() {
        //given
        TestEntity entity = new TestEntity();
        entity.setUsername("최수정");
        entity.setAge(25);
        entity.setId("838722ec-21eb-4521-a206-a8e2faf5d3f1");

        //when
        boolean flag = repository.modify(entity);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("유저이름과 나이를 수정해야 한다.")
    @Transactional
    @Rollback
    void deleteTest() {
        //given
        String id = "d335e973-62d7-49a6-9cfb-42dd165e5017";

        //when
        boolean flag = repository.remove(id);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("테스트 데이터 전체를 조회해야 한다.")
    void findAllTest() {
        //given
        //when
        List<TestEntity> entityList = repository.findAll();

        //then
        System.out.println(entityList);
        assertEquals(2, entityList.size());
    }

}