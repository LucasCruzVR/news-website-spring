package com.news.api.domain;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Integer status;
    
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    public void setStatus(PublicationEnum code) {
        this.status = code.getCode();
    }

    public PublicationEnum getStatus() {
        return PublicationEnum.toEnum(status);
    }

    public void setCategory(Optional<Category> category) {
        this.category = category.get();
    }
}
