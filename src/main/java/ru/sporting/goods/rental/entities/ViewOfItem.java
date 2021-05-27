package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VIEW_OF_ITEM")
public class ViewOfItem {

    @Id
    @GeneratedValue
    @Column(name = "ID_VIEW_OF_ITEM", unique = true, nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Название должно содержать минимум 3 символа")
    @Column(name = "NAME")
    private String name;

    //Соединение с Items
    @OneToMany(mappedBy = "viewOfItem")
    @JsonIgnoreProperties("viewOfItem")
    private List<Items> items;
}
