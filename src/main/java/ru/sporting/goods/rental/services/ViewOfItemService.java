package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.exceptions.TypeOfItemNotFoundException;
import ru.sporting.goods.rental.repositories.ViewOfItemRepository;

import java.util.List;

@Service
public class ViewOfItemService {

    @Autowired
    ViewOfItemRepository viewOfItemRepository;


}
