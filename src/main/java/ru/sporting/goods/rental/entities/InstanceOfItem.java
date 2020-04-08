package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "GOODS_COPY")
public class InstanceOfItem {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_COPY", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "HOURS_OF_USE")
    private int hoursOfUse;

    @ApiModelProperty
    @Column(name = "PURCHASE_PRICE")
    private double purchasePrice;

    //Соединение с Item
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GOODS_ID")
    @JsonIgnoreProperties("product")
    private Item item;
}
