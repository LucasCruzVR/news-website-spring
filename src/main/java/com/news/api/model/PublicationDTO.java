package com.news.api.model;
import java.io.Serializable;

import com.news.api.domain.Category;

import lombok.Data;

@Data
public class PublicationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String titleDescription;
    private String imageUrl;
    private String content;
    private String status;
    private Category category;
}
