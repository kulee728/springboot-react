package com.kh.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class NaverUser {
    int user_id;
    String id;
    String email;
    String name;
    String password;
    String profileImage;
    String created_at;
}
