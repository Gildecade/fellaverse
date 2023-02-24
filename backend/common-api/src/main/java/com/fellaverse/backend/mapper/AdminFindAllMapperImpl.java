package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.bean.Role;
import com.fellaverse.backend.dto.AdminFindAllDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AdminFindAllMapperImpl implements AdminFindAllMapper{

    @Override
    public AdminFindAllDTO toAdminFindAllDTO(Admin admin)  {
        AdminFindAllDTO adminFindAllDTO = new AdminFindAllDTO();
        adminFindAllDTO.setId(admin.getId())
                .setUsername(admin.getUsername())
                .setEmail(admin.getEmail())
                .setPhoneNumber(admin.getPhoneNumber())
                .setRoles(admin.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()));
        return adminFindAllDTO;
    }
}
