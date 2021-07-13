package com.codegym.exammd4.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull
    String name;
    @ManyToOne
    Country country;
    @NotNull
    Double area;
    @NotNull
    Long population;
    @NotNull
    Double gdp;
    @NotNull
    String description;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    City city = (City) target;
    double area = city.getArea();
    Long population = city.getPopulation();
    double gdp = city.getGdp();
    if (area < 1){
    errors.rejectValue("area","Wrong area");
    }
    if(population < 1){
        errors.rejectValue("population", "Wrong population");
    }
    if(gdp < 1){
        errors.rejectValue("gdp","Wrong area");
    }

    }
}