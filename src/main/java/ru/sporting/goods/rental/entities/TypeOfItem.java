package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "TYPE_OF_ITEM")
public class TypeOfItem {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_TYPE_OF_ITEM", unique = true, nullable = false, updatable = false)
    private Long id;

    @Size(min = 5)
    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с Items
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeOfItem")
    @JsonIgnoreProperties("typeOfItem")
    private List<Items> items;

    public TypeOfItem() {
    }

    public TypeOfItem(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
