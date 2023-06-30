package com.jimmy.studyspring.relationaldataaccess;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;

}
