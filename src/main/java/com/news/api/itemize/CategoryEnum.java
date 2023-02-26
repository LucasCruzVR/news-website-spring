package com.news.api.itemize;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum CategoryEnum {
    ACTIVE(0, "Active"),
    INACTIVE(1, "Inactive");

    private int code;
    private String description;
}
