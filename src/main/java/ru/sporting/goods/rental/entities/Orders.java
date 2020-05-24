package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ID_ORDER", unique = true, nullable = false, updatable = false)
    private Long id;

    @Min(value = 0, message = "Поле не может содержать отрицательное значение")
    @Column(name = "AMOUNT_OF_GUARANTEE")
    private double amountOfGuarantee;

    @Column(name = "PLANNED_TIME_OF_RETURNING_PRODUCT")
    private Date plannedTimeOfReturningProduct;

    @Column(name = "REAL_TIME_OF_RETURNING_PRODUCT")
    private Date realTimeOfReturningProduct;

    @Column(name = "DATE_OF_RECEIPT_OF_ITEM")
    private Date timeOfReceiptOfItem;

    @Min(value = 0, message = "Поле не может содержать отрицательное значение")
    @Column(name = "ORDER_COST")
    private double orderCost;

    @Column(name = "FINE")
    private double fine;

    //Соединение с User
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    @JsonIgnoreProperties("orders")
    private User user;

    //Соединение с InstanceOfItem
    @ManyToOne
    @JoinColumn(name = "ID_INSTANCE_OF_ITEM")
    @JsonIgnoreProperties("orders")
    private InstanceOfItem instance;
}