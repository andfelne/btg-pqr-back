package com.bgt.pqr.controllers;

import com.bgt.pqr.dtos.FiltersDto;
import com.bgt.pqr.entities.Request;
import com.bgt.pqr.mapper.FiltersMapper;
import com.bgt.pqr.services.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PutMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Request create(@RequestBody Request request) {
        return requestService.save(request);
    }

    @GetMapping("/query/{requestType}")
    public List<Request> findByFilters(
            @PathVariable("requestType") final String requestType,
            @RequestParam final Map<String, String> requestParams) {
        final FiltersDto filtersDto = FiltersMapper.paramsToFiltersDto(requestParams);
        return requestService.findByRequestTypeAndFilters(requestType, filtersDto);
    }

    @GetMapping("/query/parent/{id}")
    public Request findClimByRequestParentId(
            @PathVariable("id") final String requestParentId) {
        return requestService.findClimByRequestParentId(requestParentId);
    }

}
