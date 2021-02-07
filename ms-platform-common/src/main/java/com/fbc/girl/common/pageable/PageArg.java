package com.fbc.girl.common.pageable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageArg {
    private Integer page = 1;
    private Integer limit = 10;
}
