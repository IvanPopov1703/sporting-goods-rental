package ru.sporting.goods.rental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEST_EMPTY")
public class TestEmpty {

    @Id
    @GeneratedValue
    @Column(name = "ID_EMPTY", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    //@Column(name = "BIRCH_DAY")
    private Date birchDay;
}
