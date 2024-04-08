package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<EntityType> {
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private Integer size;
    private Integer contentSize;
    private Boolean isFirst;
    private Boolean isLast;
    private Boolean isEmpty;
    private List<EntityType> content;
}
