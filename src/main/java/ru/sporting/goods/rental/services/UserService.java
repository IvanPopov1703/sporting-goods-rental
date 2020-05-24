package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.repositories.UserRepository;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByLogin(username);
        if (user == null){
            throw new UsernameNotFoundException("Пользователь с логином " + username + " не найден!");
        }
        return user;
    }

    //Получаем текущего залогинненного пользователся
    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //Получение записи по id, иначе null
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    //Регистрация пользователя
    public void registerUser(User user) throws Exception{
        if (userRepository.getUserByLogin(user.getLogin()) != null){
            throw new Exception("Пользователь с таким логином уже существует!");
        }
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    //Сохранение пользователя
    public User save(User user) throws Exception{
        if (userRepository.getUserByLogin(user.getLogin()) != null){
            throw new Exception("Пользователь с таким логином уже существует!");
        }
        return userRepository.save(user);
    }

    //Получение пользователя по логину
    public User getUserByLogin(String login) throws UsernameNotFoundException{
        if (userRepository.getUserByLogin(login) == null){
            throw new UsernameNotFoundException("Введенные данные некорректны!");
        }
        return userRepository.getUserByLogin(login);
    }

    //Получение всех пользователей
    public List<User> findAll() {
        return userRepository.findAll();
    }

    
    public void changePassword(Long id, String password){
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    //Проверка наличия записи в базе
    private boolean existsById(Long id){
        return userRepository.existsById(id);
    }

    //Редактирование записи
    public void update(User user) {
        userRepository.save(user);
    }

    //Удаление записи
    public void deleteById(Long id) throws Exception{
        if (!existsById(id)){
            throw new Exception("Запись с номером " + id + " не найдена!");
        }
        else {
            userRepository.deleteById(id);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
