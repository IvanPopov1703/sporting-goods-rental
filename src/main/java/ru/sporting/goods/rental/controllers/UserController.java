package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.services.UserService;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Api(value = "Работа с пользователем", tags = {"Пользователь"})
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("Получение списка users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ApiOperation("Добавление нового user")
    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        userService.addUsers(user);
        return ResponseEntity.ok(user);
    }

}
