package com.accenture.flight.model;

import lombok.*;

@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Country {
    int id;
    String code;
    String name;
    String continent;
    String wikipedia_link;
    String keywords;
}

