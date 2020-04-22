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
@Table(name = "TEST_EMPTY")
public class TestEmpty {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_EMPTY", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с User
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testEmpty")
    @JsonIgnoreProperties("testEmpty")
    private List<User> users;
}
