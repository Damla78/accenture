package com.accenture.flight.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDto {
    int id;
    String code;
    String name;
    String continent;
    String wikipedia_link;
    String keywords;
}
