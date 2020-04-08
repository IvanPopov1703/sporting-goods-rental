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
@Table(name = "TYPE_OF_GOODS")
public class ViewOfItem {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_TYPE", unique = true, nullable = false, updatable = false)
    private Long id;

    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с Item
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viewOfItem")
    @JsonIgnoreProperties("viewOfItem")
    private List<Item> typeGoods;
}
