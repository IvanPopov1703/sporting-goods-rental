package ru.sporting.goods.rental.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.sporting.goods.rental.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("**/static/**", "/goodsPage").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("**/static/**", "/goodsPage", "/goodOnePage").permitAll()
//                .anyRequest().authenticated() //Не одна страничка недоступна не авториз польз
//                .and()
//                //Пробрасывает на форму авторизации
//                .exceptionHandling().accessDeniedPage("/login")
//                .and()
//                //указываем где находится форма логина
//                .formLogin()
//                //вот по такому урлу
//                .loginPage("/authoriz")
//                //мы для логина разрешаем вход всем
//                .permitAll()
//                .and()
//                //и на выход тоже разрешаем всем
//                .logout()
//                .permitAll();
    }

    private UserService userService;

    @Bean
    public UserDetailsService userDetailsService(){return (UserDetailsService) userService;}

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    //Шифрование паролей
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
