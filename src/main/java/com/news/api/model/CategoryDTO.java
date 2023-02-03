package com.news.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CategoryDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Name can't be blank")
    private String name;
    private Integer status;
}
