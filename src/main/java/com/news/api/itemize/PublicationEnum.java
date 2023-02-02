package com.news.api.itemize;

public enum PublicationEnum {
    ACTIVE(0, "Active"),
    INACTIVE(1, "Inactive");

    private int code;
    private String description;

    private PublicationEnum(int cod, String description) {
        this.code = cod;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PublicationEnum toEnum(Integer code) {
        if (code == null) {
            return PublicationEnum.ACTIVE;
        }
        for (PublicationEnum value : PublicationEnum.values()) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + code);
    }
}
