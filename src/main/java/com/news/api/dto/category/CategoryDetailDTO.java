package com.news.api.dto.category;

import com.news.api.itemize.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategoryDetailDTO {

    private Integer id;
    private String name;
    private CategoryEnum status;
}
