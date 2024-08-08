package com.kh.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserProfile {
    private int userId;
    private String username;
    private String profileImageUrl;
    private String createdAt;
}
