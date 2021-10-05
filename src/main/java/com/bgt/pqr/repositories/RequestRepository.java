package com.bgt.pqr.repositories;

import com.bgt.pqr.dtos.FiltersDto;
import com.bgt.pqr.entities.Request;

import java.util.List;

public interface RequestRepository  {

    List<Request> findByRequestTypeAndFilters(String requestType, FiltersDto filtersDto);

    Request save(Request request);

    long getNextSequence(String seqName);

    Request setRequestChildToRequestParent(Request requestChild);

    Request findClimByRequestParentId(String requestParentId);
}
