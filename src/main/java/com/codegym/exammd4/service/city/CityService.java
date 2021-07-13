package com.codegym.exammd4.service.city;

import com.codegym.exammd4.model.City;
import com.codegym.exammd4.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CityService implements ICityService{
    @Autowired
    ICityRepository cityRepository;
    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City findCityByName(String cityName) {
        return (City) cityRepository.findCityByName(cityName);
    }
}