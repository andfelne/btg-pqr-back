package com.bgt.pqr.services.impl;

import com.bgt.pqr.dtos.FiltersDto;
import com.bgt.pqr.entities.Request;
import com.bgt.pqr.repositories.RequestRepository;
import com.bgt.pqr.services.RequestService;
import com.bgt.pqr.utils.DateUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request save(Request request) {
        if ("R".equals(request.getRequestType())) {
            return saveClaimAndModifyRequest(request);
        }
        return saveRequest(request);
    }

    @Override
    public List<Request> findByRequestTypeAndFilters(String requestType, FiltersDto filtersDto) {
        return requestRepository.findByRequestTypeAndFilters(requestType, filtersDto);
    }

    @Override
    public Request findClimByRequestParentId(String requestParentId) {
        return requestRepository.findClimByRequestParentId(requestParentId);
    }

    private Request saveRequest(Request request) {
        if (null == request.getId()) {
            final String sequenceName = String.format("%s_%s", Request.SEQUENCE_NAME, request.getRequestType());
            request.setSequence(requestRepository.getNextSequence(sequenceName));
            request.setFilingDate(DateUtil.getCurrentDate());
        }
        request = requestRepository.save(request);
        return request;
    }

    public Request saveClaimAndModifyRequest(Request request) {
        request = saveRequest(request);
        Request requestParent = request.getRequestParent();
        requestParent.setHaveClaim(true);
        saveRequest(requestParent);
        return request;
    }

}
