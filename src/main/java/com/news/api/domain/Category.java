package com.news.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.news.api.itemize.CategoryEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name can't be blank")
    @NotNull(message = "Name is required")
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Publication> publications = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private CategoryEnum status = CategoryEnum.ACTIVE;
}
