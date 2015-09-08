package com.michael.timezones.facade;

import com.michael.timezones.model.TimeZone;
import com.michael.timezones.repository.TimeZoneRepository;
import com.michael.timezones.service.TimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/timezones")
public class TimeZoneController {

    @Resource
    private TimeZoneService timeZoneService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<TimeZone> timeZones() {
        return timeZoneService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public TimeZone timeZone(@PathVariable Long id) {
        return timeZoneService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TimeZone addTimeZone(@Valid @RequestBody TimeZone timeZone) {
        return timeZoneService.save(timeZone);
    }

    @RequestMapping(method = RequestMethod.PUT, value="{id}")
    public TimeZone updateTimeZone(@RequestBody TimeZone timeZone, @PathVariable Long id) {
        return timeZoneService.update(timeZone);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        timeZoneService.delete(id);
    }
}
