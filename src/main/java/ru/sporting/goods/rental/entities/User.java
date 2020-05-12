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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    public static final String ROLE_ADMIN = "ADMIN"; //админ
    public static final String ROLE_BUYER = "BUYER"; //Покупатель
    public static final String ROLE_SELLER = "SELLER"; //Продавец

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_USER", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "LOGIN")
    private String login;

    @ApiModelProperty
    @Column(name = "PASSWORD")
    private String password;

    @ApiModelProperty
    @Column(name = "SURNAME")
    private String surname;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty
    @Column(name = "PATRONYMIC")
    private String patronymic;

    @ApiModelProperty
    @Column(name = "ROLE")
    private String role;

    @ApiModelProperty
    @Column(name = "EMAIL")
    private String email;

    @ApiModelProperty
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

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
