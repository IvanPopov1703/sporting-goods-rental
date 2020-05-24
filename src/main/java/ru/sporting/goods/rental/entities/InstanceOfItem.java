package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INSTANCE_OF_ITEM")
public class InstanceOfItem {

    public static final String STATUS_ORDER_PENDING = "PENDING"; //Ожидает выдачи
    public static final String STATUS_ORDER_ISSUED = "ISSUED"; //Выдан
    public static final String STATUS_ORDER_EXPIRED = "EXPIRED"; //Просрочен
    public static final String STATUS_ORDER_HAND_OVER = "HAND_OVER"; //Сдан
    public static final int DAY_RENTAL = 24; //Один день проката

    @Id
    @GeneratedValue
    @Column(name = "ID_INSTANCE_OF_ITEM", unique = true, nullable = false, updatable = false)
    private Long id;

    @Min(value = 0, message = "Поле не может содержать отрицательное значение")
    @Column(name = "HOURS_OF_USE")
    private int hoursOfUse;

    @Min(value = 0, message = "Поле не может содержать отрицательное значение")
    @Column(name = "PURCHASE_PRICE")
    private double purchasePrice;

    @Column(name = "ORDER_STATUS")
    private String order_status;

    //Соединение с Items
    @ManyToOne
    @JoinColumn(name = "ID_ITEM")
    @JsonIgnoreProperties("instance")
    private Items items;

    //Соединение с Orders
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instance")
    @JsonIgnoreProperties("instance")
    private List<Orders>  orders;

    //Получение по статусу, его название
    public String getStatusOrder(InstanceOfItem instanceOfItem){
        String str = null;
        switch (instanceOfItem.getOrder_status()){
            case "PENDING": str = "Ожидает выдачи";
                break;
            case "ISSUED": str = "Выдан";
                break;
            case "EXPIRED": str = "Просрочен";
                break;
            case "HAND_OVER": str = "Сдан";
                break;
        }
        return str;
    }
}
