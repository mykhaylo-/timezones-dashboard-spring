package com.michael.timezones.service;

import com.michael.timezones.model.TimeZone;

public interface TimeZoneService {

        Iterable<TimeZone> getAll();

        TimeZone getById(Long id);

        TimeZone save(TimeZone timeZone);

        TimeZone update(TimeZone timeZone);

        void delete(Long id);
}
