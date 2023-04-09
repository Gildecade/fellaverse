package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Order;
import com.fellaverse.backend.dto.OrderFindAllDTO;
import com.fellaverse.backend.dto.OrderRequestDTO;
import com.fellaverse.backend.mapper.OrderFindAllMapper;
import com.fellaverse.backend.service.CourseManageService;
import com.fellaverse.backend.service.OrderManageService;
import com.fellaverse.backend.service.UserManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Controller for admin order control services, including add and find.
 */
@RestController
@RequestMapping("/api/management/order")
public class OrderManageController {
    @Autowired
    private OrderManageService orderManageService;

    @Autowired
    private  OrderFindAllMapper orderFindAllMapper;

    @Autowired
    private CourseManageService courseManageService;

    @Autowired
    private UserManageService userManageService;
    @GetMapping("")
    public List<OrderFindAllDTO> findAllOrder(){
        return orderManageService.findAllOrder().stream().map(orderFindAllMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderFindAllDTO findOrderById(@PathVariable("id") Long id) {
        return orderFindAllMapper.toDto(orderManageService.findOrderById(id));
    }

    @GetMapping("/product/{id}")
    public List<OrderFindAllDTO> findOrderByProductId(@PathVariable("id") Long productId) {
        return orderManageService.findOrderByProductId(productId).stream().map(orderFindAllMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public List<OrderFindAllDTO> findOrderByUserId(@PathVariable("id") Long userId) {
        return orderManageService.findOrderByUserId(userId).stream().map(orderFindAllMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("")
    public String addOrder(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) OrderRequestDTO orderRequestDTO) {
//        Order order = orderFindAllMapper.toEntity(orderRequestDTO);
        Order order = new Order();
        order.setUser(userManageService.findUserById(orderRequestDTO.getUserId()))
                .setQuantity(orderRequestDTO.getQuantity())
                .setAmount(orderRequestDTO.getAmount())
                .setPurchaseDateTime(orderRequestDTO.getPurchaseDateTime())
                .setProduct(courseManageService.findCourseById(orderRequestDTO.getProductId()));
        // todo future be product
        orderManageService.addOrder(order);
        return "Add order success!";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id")Long id) {
        orderManageService.deleteOrder(id);
        return "Delete order success!";
    }

    @PutMapping("")
    public String updateOrder(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) OrderRequestDTO orderRequestDTO) {

        Order order = orderManageService.findOrderById(orderRequestDTO.getId());
        if (orderRequestDTO == null) {
            return "Update order failed!";
        } else {
            if (orderRequestDTO.getId() != null) {
                order.setId(orderRequestDTO.getId());
            }

            if (orderRequestDTO.getQuantity() != null) {
                order.setQuantity(orderRequestDTO.getQuantity());
            }

            if (orderRequestDTO.getPurchaseDateTime() != null) {
                order.setPurchaseDateTime(orderRequestDTO.getPurchaseDateTime());
            }

            if (orderRequestDTO.getAmount() != null) {
                order.setAmount(orderRequestDTO.getAmount());
            }

            if (orderRequestDTO.getUserId() != null) {
                order.setUser(userManageService.findUserById(orderRequestDTO.getUserId()));
            }

            if (orderRequestDTO.getProductId() != null) {
                order.setProduct(courseManageService.findCourseById(orderRequestDTO.getProductId()));
            }
        }
        orderManageService.updateOrder(order);
        return "Update order success!";
    }


}
