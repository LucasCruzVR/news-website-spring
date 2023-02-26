package com.news.api.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.news.api.itemize.PublicationEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "publications")
@Data
@NoArgsConstructor
public class Publication implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title can't be blank")
    @NotNull(message = "Title is required")
    private String title;

    @NotBlank(message = "Title description can't be blank")
    @NotNull(message = "Title description is required")
    private String titleDescription;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PublicationEnum status = PublicationEnum.ANALYSIS;
    
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
