package com.bgt.pqr.mapper;

import com.bgt.pqr.dtos.FiltersDto;
import com.bgt.pqr.utils.ParamsMapper;

import java.util.Map;

public final class FiltersMapper {

    public FiltersMapper() {
    }

    public static FiltersDto paramsToFiltersDto(final Map<String, String> requestParam) {
        FiltersDto dto = new FiltersDto();
        dto.setSequence(ParamsMapper.mapLong(requestParam, "sequence"));
        dto.setContains(ParamsMapper.mapString(requestParam, "contains"));
        dto.setDateFrom(ParamsMapper.mapDate(requestParam, "dateFrom"));
        dto.setDateTo(ParamsMapper.mapDate(requestParam, "dateTo"));
        return dto;
    }
}
