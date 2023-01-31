package com.news.api.domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.news.api.itemize.CategoryEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "categories")
public class Category implements Serializable {

     private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name can't be blank")
    @NotNull(message = "Name is required")
    private String name;
    
    private Integer status;

    public Category(Integer id, String name, CategoryEnum code) {
        this.id = id;
        this.name = name;
        this.status = code.getCode();
    }

    public void setStatus(CategoryEnum code) {
        this.status = code.getCode();
    }

    public CategoryEnum getStatus() {
        return CategoryEnum.toEnum(status);
    }
}
