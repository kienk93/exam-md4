package com.codegym.exammd4.service.city;

import com.codegym.exammd4.model.City;
import com.codegym.exammd4.service.IGeneralService;

public interface ICityService extends IGeneralService<City> {
    City findCityByName(String cityName);
}
