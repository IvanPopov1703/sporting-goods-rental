package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_ROLE", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с User
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    @JoinColumn(name = "USER_ID")
    @JsonIgnoreProperties("user")
    private List<User> users;
}
