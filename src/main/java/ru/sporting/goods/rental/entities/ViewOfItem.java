package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VIEW_OF_ITEM")
public class ViewOfItem {

    @ApiModelProperty
    @Id
    @GeneratedValue
    @Column(name = "ID_VIEW_OF_ITEM", unique = true, nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30)
    @ApiModelProperty
    @Column(name = "NAME")
    private String name;

    //Соединение с Items
    @OneToMany(mappedBy = "viewOfItem")
    @JsonIgnoreProperties("viewOfItem")
    private List<Items> items;
}
