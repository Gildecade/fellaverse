package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Course;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Override
    public String purchase(Course course) {
        return course.getVideo_url();
    }
}
