package com.news.api.model;

import java.io.Serializable;

import com.news.api.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllPublicationsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String titleDescription;
    private String imageUrl;
    private String status;
    private Category category;
}
