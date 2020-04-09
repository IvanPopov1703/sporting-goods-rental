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
@Table(name = "PRODUCT_TYPE")
public class TypeOfItem {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_PRODUCT", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с Item
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeOfProduct")
    @JsonIgnoreProperties("typeOfProduct")
    private List<Item> typeProduct;
}
