package com.news.api.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryIdDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
}
