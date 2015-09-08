package com.michael.timezones.service;

import com.michael.timezones.model.TimeZone;
import com.michael.timezones.repository.TimeZoneRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultTimeZoneService implements TimeZoneService {

        @Resource
        private TimeZoneRepository timeZoneRepository;

        @Override
        public Iterable<TimeZone> getAll() {
                return timeZoneRepository.findAll();
        }

        @Override
        public TimeZone getById(Long id) {
                return timeZoneRepository.findOne(id);
        }

        @Override
        public TimeZone save(TimeZone timeZone) {
                return timeZoneRepository.save(timeZone);
        }

        @Override
        public TimeZone update(TimeZone timeZone) {
                return timeZoneRepository.save(timeZone);
        }

        @Override
        public void delete(Long id) {
                timeZoneRepository.delete(id);
        }
}
