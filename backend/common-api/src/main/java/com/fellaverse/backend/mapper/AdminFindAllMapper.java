package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.dto.AdminFindAllDTO;

public interface AdminFindAllMapper {
    AdminFindAllDTO toAdminFindAllDTO(Admin admin);
}
