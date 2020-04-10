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
@Table(name = "INSTANCE_OF_ITEM")
public class InstanceOfItem {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_INSTANCE_OF_ITEM", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "HOURS_OF_USE")
    private int hoursOfUse;

    @ApiModelProperty
    @Column(name = "PURCHASE_PRICE")
    private double purchasePrice;

    //Соединение с Item
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("item")
    @JoinColumn(name = "ID_ITEM")
    private Item item;

    //Соединение с Order
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instanceOfItem")
    @JoinColumn(name = "INSTANCE_OF_ITEM_ID")
    @JsonIgnoreProperties("order")
    private List<Order>  orders;
}
