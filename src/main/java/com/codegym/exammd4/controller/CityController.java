package com.codegym.exammd4.controller;

import com.codegym.exammd4.model.City;
import com.codegym.exammd4.model.Country;
import com.codegym.exammd4.service.city.ICityService;
import com.codegym.exammd4.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController("/city")
public class CityController {
    @Autowired
    ICityService cityService;
    @Autowired
    ICountryService countryService;

    @GetMapping
    public ModelAndView showListCity() {
        ModelAndView modelAndView = new ModelAndView("/index", "cities", cityService.findAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("/add", "countries", countryService.findAll());
        modelAndView.addObject("cities", new City());
        return modelAndView;
    }

    @PostMapping("/add/{id}")
    public ModelAndView addNewCity(@Validated @ModelAttribute City city, BindingResult bindingResult) {
        city.validate(city,bindingResult);
        ModelAndView modelAndView;
        if(bindingResult.hasFieldErrors()){
            modelAndView = new ModelAndView("/add","countries",countryService.findAll());
            return modelAndView;
        }
        cityService.save(city);
        modelAndView = new ModelAndView("/add","countries",countryService.findAll());
        modelAndView.addObject("cities",city);
        return modelAndView;
    }
    @GetMapping("/view/{cityName}")
    public ModelAndView showOneCity(@PathVariable("cityName") String cityName) {
        City city = cityService.findCityByName(cityName);
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long cityId) {
        Optional<City> city = cityService.findById(cityId);
        ModelAndView modelAndView = new ModelAndView("/edit");
        if (city.isPresent()) {
            modelAndView.addObject("city", city.get());
            Iterable<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        return null;
    }

    @PostMapping("/edit")
    public ModelAndView updateCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        city.validate(city, bindingResult);
        ModelAndView modelAndView;
        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("/edit");
            Iterable<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        cityService.save(city);
        Iterable<Country> countries = countryService.findAll();
        modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("city", city);
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable("id") Long cityId) {
        cityService.deleteById(cityId);
        return "redirect:/city";
    }

}