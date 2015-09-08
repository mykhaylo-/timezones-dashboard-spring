package com.michael.timezones.repository;

import com.michael.timezones.model.TimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(itemResourceRel = "timezone", collectionResourceRel = "timezones", path = "datatimezones")
public interface TimeZoneRepository extends PagingAndSortingRepository<TimeZone, Long> {

}
