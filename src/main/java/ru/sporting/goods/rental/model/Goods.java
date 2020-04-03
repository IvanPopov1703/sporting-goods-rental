package ru.sporting.goods.rental.model;

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
@Table(name = "GOODS")
public class Goods {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_GOODS", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty
    @Column(name = "NUMBER_OF_COPIES")
    private int numberOfCopies;

    @ApiModelProperty
    @Column(name = "COST_ONE_HOUR_RENTAL")
    private double сostOneHourRental;

    //Соединение с TypeOfGoods
    @ManyToOne(cascade = CascadeType.ALL) //Многие к одному, значит что таблица слабая
    @JoinColumn(name = "TYPE_GOODS_ID") //имя поля, которое будет храниться в БД
    @JsonIgnoreProperties("typeGoods") //Имя поля которое отвечает за связь (какая-то хуйня)
    private TypeOfGoods typeOfGoods;

    //Соединение с GoodsCopy
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goods")
    @JsonIgnoreProperties("goods")
    private List<GoodsСopy> product;
}
