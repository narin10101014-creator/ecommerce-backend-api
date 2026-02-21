package com.narin.ecommerce.dto.request;

import com.narin.ecommerce.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleRequest {
    private Role role;
}
