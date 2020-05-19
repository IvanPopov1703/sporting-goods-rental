package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    public static final String ROLE_ADMIN = "ADMIN"; //админ
    public static final String ROLE_BUYER = "BUYER"; //Покупатель
    //public static final String ROLE_SELLER = "SELLER"; //Продавец

    @Id
    @GeneratedValue
    @Column(name = "ID_USER", unique = true, nullable = false, updatable = false)
    private Long id;

    @Size(min = 4, message = "Поле должно содержать, минимум 4 символа")
    @Column(name = "LOGIN")
    private String login;

    @Size(min = 4, message = "Поле должно содержать, минимум 4 символа")
    @Column(name = "PASSWORD")
    private String password;

    @Size(min = 3, message = "Поле должно содержать, минимум 3 символа")
    @Column(name = "SURNAME")
    private String surname;

    @Size(min = 2, message = "Поле должно содержать, минимум 2 символа")
    @Column(name = "NAME")
    private String name;

    @Size(min = 4, message = "Поле должно содержать, минимум 4 символа")
    @Column(name = "PATRONYMIC")
    private String patronymic;

    @Column(name = "ROLE")
    private String role;

    @Size(min = 6, message = "Поле должно содержать, минимум 6 символов")
    @Column(name = "EMAIL")
    private String email;

    @Size(min = 6, message = "Поле должно содержать, минимум 6 символов")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Size(min = 10, message = "Поле должно содержать, минимум 10 символов")
    @Column(name = "PASSPORT_DATA")
    private String passportData;

    //Валидация
    @Min(value = 0, message = "Поле не может содержать отрицательное значение")
    @Column(name = "PURSE")
    private double purse;

    @Min(value = 0, message = "Поле не может содержать отрицательное значение")
    @Column(name = "AMOUNT_OF_RENTED_GOODS")
    private int amountOfRentedGoods;

    //Соединение с Orders
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Orders> orders;


    //Роли
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return login;
    }

    //Просрочился или нет аккаунт
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Удаленный аккаунт
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Срок годности аккаунта
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Пользователь который не подтвердил аккаунт
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullUserName(){
        return surname + " " + name + " " + patronymic;
    }

}
