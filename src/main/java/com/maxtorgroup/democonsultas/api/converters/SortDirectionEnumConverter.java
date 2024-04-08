package com.maxtorgroup.democonsultas.api.converters;

import com.maxtorgroup.democonsultas.domain.dto.PageRequestSortDirection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortDirectionEnumConverter implements Converter<String, PageRequestSortDirection> {

    @Override
    public PageRequestSortDirection convert(String source) {
        return PageRequestSortDirection.valueOf(source.toUpperCase());
    }
}
