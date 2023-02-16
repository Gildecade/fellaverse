package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.dto.AdminFindAllDTO;
import org.springframework.stereotype.Component;

@Component
public class AdminFindAllMapperImpl implements AdminFindAllMapper{

    @Override
    public AdminFindAllDTO toAdminFindAllDTO(Admin admin)  {
        AdminFindAllDTO adminFindAllDTO = new AdminFindAllDTO();
        adminFindAllDTO.setId(admin.getId())
                .setUsername(admin.getUsername())
                .setPassword(admin.getPassword())
                .setEmail(admin.getEmail())
                .setPhoneNumber(admin.getPhoneNumber());
        return adminFindAllDTO;
    }
}
