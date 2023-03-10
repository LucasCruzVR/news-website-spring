package com.news.api.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;
}