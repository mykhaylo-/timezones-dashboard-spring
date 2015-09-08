package com.michael.timezones.service;

import com.michael.timezones.model.Role;
import com.michael.timezones.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultRoleService implements RoleServive{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRole(Integer id) {
        return roleRepository.findOne(id);
    }
}
