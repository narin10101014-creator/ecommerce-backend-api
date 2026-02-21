package com.narin.ecommerce.dto;

import com.narin.ecommerce.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleRequest {
    private Role role;
}
