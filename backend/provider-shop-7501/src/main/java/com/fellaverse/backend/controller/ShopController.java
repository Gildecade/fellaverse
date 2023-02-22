package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.bean.Order;
import com.fellaverse.backend.dto.CourseBuyDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.CourseBuyMapper;
import com.fellaverse.backend.mapper.OrderMapper;
import com.fellaverse.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.fellaverse.backend.validator.ValidGroup;

@RestController
@RequestMapping("/api/shop")  // any requests under token will be proceeded
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private CourseManageService courseManageService;

    @Autowired
    private AdminManageUserInfoService adminManageUserInfoService;

    @JWTCheckToken(function = "buy")
    @PostMapping("/course")
    public String purchase(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) CourseBuyDTO courseBuyDTO) {
        if(balanceService.checkBalance(courseBuyDTO.getUserId(), courseBuyDTO.getAmount())){
            Course course = courseManageService.findCourseById(courseBuyDTO.getId());

            String courseUrl = shopService.purchase(course);
            // update user account
            balanceService.updateBalance(courseBuyDTO.getUserId(), -1 * courseBuyDTO.getAmount()); // note that price should be negative
            // create a new order
            Order order = new Order();
            order.setUser(adminManageUserInfoService.findUserById(courseBuyDTO.getUserId()))
                    .setQuantity(courseBuyDTO.getQuantity())
                    .setAmount(courseBuyDTO.getAmount())
                    .setPurchaseDateTime(courseBuyDTO.getPurchaseDateTime())
                    .setProduct(course);
            orderService.addOrder(order);
            // return link to the course video
            return courseUrl;
        }else{
            return "Not enough Balance!";
        }
    }
}

