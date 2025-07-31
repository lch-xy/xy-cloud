package org.xy.cloud.system.api.user.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String name;

    private int age;

    private String post;
}
