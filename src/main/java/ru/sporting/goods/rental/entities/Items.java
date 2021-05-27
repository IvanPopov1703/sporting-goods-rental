package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Items {

    public static final int AMOUNT_OF_GUARANTEE = 2000;

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_ITEM", unique = true, nullable = false, updatable = false)
    private Long id;

    @Size(min = 3, max = 40, message = "Название должно содержать минимум 3 символа")
    @ApiModelProperty
    @Column(name = "NAME_ITEM")
    private String name;

    @DecimalMin(value = "1", message = "Не может быть пустым или быть < 1")
    @ApiModelProperty
    @Column(name = "COST_ONE_Day_RENTAL")
    private double сostOneDayRental;

    @Size(min = 3, message = "Название должно содержать минимум 3 символа")
    @ApiModelProperty
    @Column(name = "DESCRIPTION")
    private String description;

    //Соединение с ViewOfItem
    @ManyToOne
    @JoinColumn(name = "VIEW_OF_ITEM_ID")
    @JsonIgnoreProperties("items")
    private ViewOfItem viewOfItem;

    //Соединение с InstanceOfItem
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "items")
    @JsonIgnoreProperties("items")
    private List<InstanceOfItem> instance;

    //Соединение с TypeOfItem
    @ManyToOne
    @JoinColumn(name = "TYPE_OF_ITEM_ID")
    @JsonIgnoreProperties("items")
    private TypeOfItem typeOfItem;

    //Получение суммы залога
    public int getAmountOfGuarantee(){
        return AMOUNT_OF_GUARANTEE;
    }
}
