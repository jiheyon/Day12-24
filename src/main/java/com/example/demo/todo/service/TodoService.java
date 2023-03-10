package com.example.demo.todo.service;


import com.example.demo.todo.dto.FindAllDTO;
import com.example.demo.todo.dto.TodoDto;
import com.example.demo.todo.entity.ToDo;
import com.example.demo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 역할: 컨트롤러와 저장소 사이의 잡일 처리 역할
@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    /*
         - 할 일 목록조회 중간처리
         1. 컨트롤러에게 userId를 뺀 할일 리스트를 전달한다.
         2. 할일 목록의 카운트를 세서 따로 추가해서 전달한다.
     */
    public FindAllDTO findAllServ(String userId) {
        return new FindAllDTO(repository.findAll(userId));
    }

    public FindAllDTO createServ(final ToDo newTodo) {

        if (newTodo == null) {
            log.warn("newTodo cannot be null!");
            throw new RuntimeException("newTodo cannot be null!");
        }

        boolean flag = repository.save(newTodo);
        if (flag) log.info("새로운 할일 [Id: {}]이 저장되었습니다.", newTodo.getId());

        return flag ? findAllServ(newTodo.getUserId()) : null;
    }

    public FindAllDTO deleteServ(String id, String userId) {

        int x = 10, y = 20;
//        System.out.println(false & y++ == 21);


        boolean flag = repository.remove(id);

        // 삭제 실패한 경우
        if (!flag) {
            log.warn("delete fail!! not found id [{}]", id);
            throw new RuntimeException("delete fail!");
        }
        return findAllServ(userId);
    }



    public TodoDto findOneServ(String id) {

        ToDo toDo = repository.findOne(id);
        log.info("findOneServ return data - {}", toDo);


        return toDo != null ? new TodoDto(toDo) : null;
    }

    public FindAllDTO update(ToDo toDo) { //수정된 정보를 가지고 나갈거임

        boolean flag = repository.modify(toDo);
        return flag ? findAllServ(toDo.getUserId()) : new FindAllDTO();
    }
    // 수정잘 하면 수정된 갱신된 목록을 가져다 줌 -> findAllServ()
}
