package com.fellaverse.backend.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdminFindAllDTO {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private List<String> roles;


}
