package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    //Получаем текущего залогинненного пользователся
    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByLogin(username);
        if (user == null){
            throw new UsernameNotFoundException("Пользователь с логином " + username + " не найден!");
        }
        return user;
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

    //Получение пользователя по логину
    public User getUserByLogin(String login) throws UsernameNotFoundException{
        if (userRepository.getUserByLogin(login) == null){
            throw new UsernameNotFoundException("Введенные данные некорректны!");
        }
        return userRepository.getUserByLogin(login);
    }

    public void checkPass(String pass1, String pass2) throws UsernameNotFoundException{
        if (!pass1.equals(pass2)) {
            throw new UsernameNotFoundException("Введенные данные некорректны!");
        }
    }

    //Получение всех пользователей
    public List<User> getAllUsers() {
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
    public User update(User user) throws Exception{
        if (user.getId() != null && !existsById(user.getId())){
            throw new Exception("Запись с номером " + user.getId() + " не найдена!");
        }
        return userRepository.save(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
