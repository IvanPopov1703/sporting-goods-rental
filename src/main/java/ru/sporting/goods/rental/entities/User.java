package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_USER", unique = true, nullable = false, updatable = false)
    private Long id;

    //Соединение с Role
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID")
    @JsonIgnoreProperties("role")
    private Role role;

    //Соединение с Order
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JoinColumn(name = "ORDER_ID")
    @JsonIgnoreProperties("user")
    private List<Order> orders;

    @ApiModelProperty
    @Column(name = "LOGIN")
    private String login;

    @ApiModelProperty
    @Column(name = "PASSWORD")
    private String password;

    @ApiModelProperty
    @Column(name = "FIO")
    private String fio;

    @ApiModelProperty
    @Column(name = "EMAIL")
    private String email;

    @ApiModelProperty
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
