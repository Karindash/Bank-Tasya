package com.example.trial.banktasya.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingResponse {
    private Integer page;
    private Integer rowsPerPage;
    private Long totalRows;
    private Integer totalPages;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
