package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER")
public class Order {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_ORDER", unique = true, nullable = false, updatable = false)
    private Long id;

    //Соединение с User
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    @JoinColumn(name = "ID_USER")
    private User user;

    //Соединение с InstanceOfItem
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("instance")
    @JoinColumn(name = "ID_INSTANCE_OF_ITEM")
    private InstanceOfItem instance;

    //Соединение с OrderStatus
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_STATUS")
    @JsonIgnoreProperties("orderStatus")
    private OrderStatus orderStatus;

    @ApiModelProperty
    @Column(name = "DATE_OF_RECEIPT_OF_ITEM")
    private Time timeOfReceiptOfItem;

    @ApiModelProperty
    @Column(name = "COUNT_ITEMS")
    private int countItems;

    @ApiModelProperty
    @Column(name = "AMOUNT_OF_GUARANTEE")
    private double amountOfGuarantee;

    @ApiModelProperty
    @Column(name = "PLANNED_TIME_OF_RETURNING_PRODUCT")
    private Time plannedTimeOfReturningProduct;

    @ApiModelProperty
    @Column(name = "REAL_TIME_OF_RETURNING_PRODUCT")
    private Time realTimeOfReturningProduct;

    @ApiModelProperty
    @Column(name = "ORDER_COST")
    private double orderCost;
}
