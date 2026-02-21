package com.narin.ecommerce.service;

import com.narin.ecommerce.entity.User;
import com.narin.ecommerce.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User getById(Long id);
    List<User> getAll();
    User update(Long id, User user);
    void delete(Long id);
    Page<User> getAll(Pageable pageable);
    void updateRole(Long id, Role role);
}
