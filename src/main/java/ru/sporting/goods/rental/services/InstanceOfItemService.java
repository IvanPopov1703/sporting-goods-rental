package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.repositories.InstanceOfItemRepository;

@Service
public class InstanceOfItemService {

    @Autowired
    InstanceOfItemRepository instanceOfItemRepository;

}
