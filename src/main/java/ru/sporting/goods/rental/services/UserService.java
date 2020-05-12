package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        User user = getUserDataByLogin(username);
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
    public User registerUser(User user) throws Exception{
        if (userRepository.getUserByLogin(user.getLogin()) != null){
            //throw new ValidationException("global", "registration.login.used");
            throw new Exception("Пользователь с таким логином уже существует!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //Получение пользователя по логину
    public User getUserDataByLogin(String login){
        return userRepository.getUserByLogin(login);
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

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
}
