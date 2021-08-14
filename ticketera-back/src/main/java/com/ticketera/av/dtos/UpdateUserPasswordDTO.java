package com.ticketera.av.dtos;


import lombok.Data;

@Data
public class UpdateUserPasswordDTO {
    private Long userId;
    private String password;
}
