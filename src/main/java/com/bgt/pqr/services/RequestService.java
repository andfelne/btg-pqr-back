package com.bgt.pqr.services;

import com.bgt.pqr.dtos.FiltersDto;
import com.bgt.pqr.entities.Request;

import java.util.List;

public interface RequestService {

    Request save(Request request);

    List<Request> findByRequestTypeAndFilters(String requestType, FiltersDto filtersDto);

    Request findClimByRequestParentId(String requestParentId);
}
