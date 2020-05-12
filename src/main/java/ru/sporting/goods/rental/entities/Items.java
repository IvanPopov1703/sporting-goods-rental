package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Items {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_ITEM", unique = true, nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Поле не может быть путым!")
    @ApiModelProperty
    @Column(name = "NAME_ITEM")
    private String name;

    @Digits(integer=6, fraction=2, message="Не может быть пустым!")
    @Positive(message = "Не может быть отрицательным!")
    @ApiModelProperty
    @Column(name = "NUMBER_OF_COPIES")
    private int numberOfCopies;

    @Positive(message = "Не может быть отрицательным!")
    @ApiModelProperty
    @Column(name = "COST_ONE_HOUR_RENTAL")
    private double сostOneHourRental;

    //Соединение с ViewOfItem
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "VIEW_OF_ITEM_ID")
    @JsonIgnoreProperties("items")
    private ViewOfItem viewOfItem;

    //Соединение с InstanceOfItem
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    @JsonIgnoreProperties("item")
    private List<InstanceOfItem> items;

    //Соединение с TypeOfItem
    @ManyToOne
    @JoinColumn(name = "TYPE_OF_ITEM_ID")
    @JsonIgnoreProperties("items")
    private TypeOfItem typeOfItem;
}
