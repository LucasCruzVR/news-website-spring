package com.news.api.itemize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PublicationEnum {
    ACTIVE(0, "Active"),
    INACTIVE(1, "Inactive"),
    ANALYSIS(2, "Analysis");

    private int code;
    private String description;
}
