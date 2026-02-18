package com.narin.ecommerce.service.impl;

import com.narin.ecommerce.entity.User;
import com.narin.ecommerce.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    /*@Override
    public User create(UserRequest req) {
        return null;
    }*/

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return null;
    }
}
