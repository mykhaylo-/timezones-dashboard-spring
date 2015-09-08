package com.michael.timezones.repository;

import com.michael.timezones.model.Role;
import com.michael.timezones.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

}
