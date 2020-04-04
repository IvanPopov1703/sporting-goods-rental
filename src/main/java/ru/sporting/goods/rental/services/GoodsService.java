package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.model.Goods;
import ru.sporting.goods.rental.repositories.GoodsRepository;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    //Получение всех товаров
    public List<Goods> getAll(){
        return goodsRepository.findAll();
    }
}
