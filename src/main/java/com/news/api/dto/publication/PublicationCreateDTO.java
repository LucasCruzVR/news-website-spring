package com.news.api.dto.publication;
import java.io.Serializable;

import com.news.api.domain.Category;

import com.news.api.dto.category.CategoryIdDTO;
import com.news.api.itemize.PublicationEnum;
import lombok.Data;

@Data
public class PublicationCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String titleDescription;
    private String imageUrl;
    private String content;
    private PublicationEnum status;
    private CategoryIdDTO category;
}
