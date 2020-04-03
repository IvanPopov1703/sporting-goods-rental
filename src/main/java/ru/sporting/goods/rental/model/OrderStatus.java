package ru.sporting.goods.rental.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_STATUS")
public class OrderStatus {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_STATUS", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с Order
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderStatus")
    @JsonIgnoreProperties("orderStatus")
    private List<Order> orders;*/
}
