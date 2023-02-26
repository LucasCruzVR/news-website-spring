package com.news.api.dto.publication;

import com.news.api.domain.Category;
import com.news.api.dto.category.CategoryDetailDTO;
import com.news.api.itemize.PublicationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublicationDetailDTO {
    private Long id;
    private String title;
    private String titleDescription;
    private String imageUrl;
    private PublicationEnum status;
    private String content;
    private CategoryDetailDTO category;
}
