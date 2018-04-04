package com.datasource.service.impl;

import com.datasource.dao.CityDao;
import com.datasource.pojo.City;
import com.datasource.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ranai on 2018/4/2 0002.
 */
@Service(value = "cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;
    @Override
    public City findOneById(Integer id) {
        return cityDao.findOneById(id);
    }
}
