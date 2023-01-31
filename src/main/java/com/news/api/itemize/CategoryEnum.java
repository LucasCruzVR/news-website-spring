package com.news.api.itemize;

public enum CategoryEnum {
    ACTIVE(0, "Active"),
    INACTIVE(1, "Inactive");

    private int code;
    private String description;

    private CategoryEnum(int cod, String description) {
        this.code = cod;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static CategoryEnum toEnum(Integer code) {
        if (code == null) {
            return CategoryEnum.ACTIVE;
        }
        for (CategoryEnum value : CategoryEnum.values()) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + code);
    }
}
