package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.TestEmpty;
import ru.sporting.goods.rental.repositories.TestEmptyRepository;

import java.util.List;

@Service
public class TestEmptyService {

    @Autowired
    TestEmptyRepository testEmptyRepository;

    //Получение всех пользователей
    public List<TestEmpty> getAll(){
        return testEmptyRepository.findAll();
    }

    public void add(TestEmpty empty){
        testEmptyRepository.save(empty);
    }
}
