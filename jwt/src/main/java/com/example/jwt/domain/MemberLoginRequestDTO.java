package com.example.jwt.domain;

import lombok.Data;

@Data
public class MemberLoginRequestDTO {
    private String memberId;
    private String password;

}
