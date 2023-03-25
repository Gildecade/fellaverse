package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Order;
import com.fellaverse.backend.bean.Product;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.CourseDTO;
import com.fellaverse.backend.dto.OrderDTO;
import com.fellaverse.backend.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDTO.getId() );
        order.setQuantity( orderDTO.getQuantity() );
        order.setPurchaseDateTime( orderDTO.getPurchaseDateTime() );
        order.setAmount( orderDTO.getAmount() );
        order.setUser( userDTOToUser( orderDTO.getUser() ) );
        order.setProduct( courseDTOToProduct( orderDTO.getProduct() ) );

        return order;
    }

    @Override
    public OrderDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setUser( userToUserDTO( order.getUser() ) );
        orderDTO.setProduct( productToCourseDTO( order.getProduct() ) );
        orderDTO.setQuantity( order.getQuantity() );
        orderDTO.setPurchaseDateTime( order.getPurchaseDateTime() );
        orderDTO.setAmount( order.getAmount() );

        return orderDTO;
    }

    @Override
    public Order partialUpdate(OrderDTO orderDTO, Order order) {
        if ( orderDTO == null ) {
            return order;
        }

        if ( orderDTO.getId() != null ) {
            order.setId( orderDTO.getId() );
        }
        if ( orderDTO.getQuantity() != null ) {
            order.setQuantity( orderDTO.getQuantity() );
        }
        if ( orderDTO.getPurchaseDateTime() != null ) {
            order.setPurchaseDateTime( orderDTO.getPurchaseDateTime() );
        }
        if ( orderDTO.getAmount() != null ) {
            order.setAmount( orderDTO.getAmount() );
        }
        if ( orderDTO.getUser() != null ) {
            if ( order.getUser() == null ) {
                order.setUser( new User() );
            }
            userDTOToUser1( orderDTO.getUser(), order.getUser() );
        }
        if ( orderDTO.getProduct() != null ) {
            if ( order.getProduct() == null ) {
                order.setProduct( new Product() );
            }
            courseDTOToProduct1( orderDTO.getProduct(), order.getProduct() );
        }

        return order;
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setEmail( userDTO.getEmail() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );
        user.setWallet( userDTO.getWallet() );
        user.setStatus( userDTO.getStatus() );

        return user;
    }

    protected Product courseDTOToProduct(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( courseDTO.getId() );
        product.setProductName( courseDTO.getProductName() );
        product.setDescription( courseDTO.getDescription() );
        product.setImageUrl( courseDTO.getImageUrl() );
        product.setPrice( courseDTO.getPrice() );
        product.setCreatedDateTime( courseDTO.getCreatedDateTime() );
        product.setProductStatus( courseDTO.getProductStatus() );

        return product;
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        userDTO.setWallet( user.getWallet() );
        userDTO.setStatus( user.getStatus() );

        return userDTO;
    }

    protected CourseDTO productToCourseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId( product.getId() );
        courseDTO.setProductName( product.getProductName() );
        courseDTO.setDescription( product.getDescription() );
        courseDTO.setImageUrl( product.getImageUrl() );
        courseDTO.setPrice( product.getPrice() );
        courseDTO.setCreatedDateTime( product.getCreatedDateTime() );
        courseDTO.setProductStatus( product.getProductStatus() );
        courseDTO.setVideoUrl(product.getCourse().getVideoUrl());
        courseDTO.setUserId(product.getCourse().getUserId());

        return courseDTO;
    }

    protected void userDTOToUser1(UserDTO userDTO, User mappingTarget) {
        if ( userDTO == null ) {
            return;
        }

        mappingTarget.setId( userDTO.getId() );
        mappingTarget.setUsername( userDTO.getUsername() );
        mappingTarget.setPassword( userDTO.getPassword() );
        mappingTarget.setEmail( userDTO.getEmail() );
        mappingTarget.setPhoneNumber( userDTO.getPhoneNumber() );
        mappingTarget.setWallet( userDTO.getWallet() );
        mappingTarget.setStatus( userDTO.getStatus() );
    }

    protected void courseDTOToProduct1(CourseDTO courseDTO, Product mappingTarget) {
        if ( courseDTO == null ) {
            return;
        }

        mappingTarget.setId( courseDTO.getId() );
        mappingTarget.setProductName( courseDTO.getProductName() );
        mappingTarget.setDescription( courseDTO.getDescription() );
        mappingTarget.setImageUrl( courseDTO.getImageUrl() );
        mappingTarget.setPrice( courseDTO.getPrice() );
        mappingTarget.setCreatedDateTime( courseDTO.getCreatedDateTime() );
        mappingTarget.setProductStatus( courseDTO.getProductStatus() );
    }

}
