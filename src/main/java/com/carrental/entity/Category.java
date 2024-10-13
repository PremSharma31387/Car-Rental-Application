package com.carrental.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private int categoryLevel;
    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList;
    private double discountPercent;
    private boolean isDiscountEnable=true;

}
