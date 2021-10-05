package com.bgt.pqr.repositories.impl;

import com.bgt.pqr.dtos.FiltersDto;
import com.bgt.pqr.entities.DatabaseSequence;
import com.bgt.pqr.entities.Request;
import com.bgt.pqr.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestRepositoryImpl implements RequestRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public List<Request> findByRequestTypeAndFilters(String requestType, FiltersDto filtersDto) {

        Query query = new Query();
        query.addCriteria(Criteria.where("requestType").is(requestType));

        if (null != filtersDto.getSequence()) {
            query.addCriteria((Criteria.where("sequence").is(filtersDto.getSequence())));
        }
        if (null != filtersDto.getDateFrom()) {
            query.addCriteria(Criteria.where("filingDate").gt(filtersDto.getDateFrom()));
        }
        if (null != filtersDto.getDateTo()) {
            query.addCriteria(Criteria.where("filingDate").lt(filtersDto.getDateTo()));
        }

        return mongoOperations.find(query, Request.class);
    }

    @Override
    public Request save(Request request) {
        return mongoOperations.save(request);
    }

    @Override
    public long getNextSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return counter.getSeq();
    }

    @Override
    public Request setRequestChildToRequestParent(Request requestChild) {
        Request request = mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").is(requestChild.getRequestParent().getId())),
                new Update().addToSet("requestChild", requestChild.getId()),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                Request.class);
        return request;
    }

    @Override
    public Request findClimByRequestParentId(String requestParentId) {
        return mongoOperations.findOne(Query.query(Criteria.where("requestParent.id")
                .is(requestParentId)), Request.class);
    }


}
