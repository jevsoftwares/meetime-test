package com.jaysonmm.meetime_test.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactRequest {
    private String email;
    private String firstName;
    private String lastName;
}
