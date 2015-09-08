package com.michael.timezones.repository;

import com.michael.timezones.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);
}
