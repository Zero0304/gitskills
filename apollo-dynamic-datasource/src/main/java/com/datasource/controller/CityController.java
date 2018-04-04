package com.datasource.controller;

import com.datasource.pojo.City;
import com.datasource.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ranai on 2018/4/2 0002.
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/findone/{id}")
    public City findOneById(@PathVariable Integer id){
        return cityService.findOneById(id);
    }
}
