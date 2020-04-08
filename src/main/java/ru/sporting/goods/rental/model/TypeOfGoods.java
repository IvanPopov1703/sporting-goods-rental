package ru.sporting.goods.rental.model;

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
@Table(name = "TYPE_OF_GOODS")
public class TypeOfGoods {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_TYPE", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с Goods
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeOfGoods")
    @JsonIgnoreProperties("typeOfGoods")
    private List<Goods> typeGoods;
}
