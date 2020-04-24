package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Orders {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_ORDER", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "COUNT_ITEMS")
    private int countItems;

    @ApiModelProperty
    @Column(name = "AMOUNT_OF_GUARANTEE")
    private double amountOfGuarantee;

    /*@ApiModelProperty
    @Temporal(TemporalType.DATE)
    @Column(name = "PLANNED_TIME_OF_RETURNING_PRODUCT")
    private Date plannedTimeOfReturningProduct;

    @ApiModelProperty
    @Column(name = "REAL_TIME_OF_RETURNING_PRODUCT")
    private Date realTimeOfReturningProduct;


    @ApiModelProperty
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_OF_RECEIPT_OF_ITEM")
    private Date timeOfReceiptOfItem;*/

    @ApiModelProperty
    @Column(name = "ORDER_COST")
    private double orderCost;


    //Соединение с User
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER")
    @JsonIgnoreProperties("orders")
    private User user;

    //Соединение с InstanceOfItem
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_INSTANCE_OF_ITEM")
    @JsonIgnoreProperties("orders")
    private InstanceOfItem instance;

    //Соединение с OrderStatus
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_STATUS_ID")
    @JsonIgnoreProperties("orders")
    private OrderStatus orderStatus;
}