package com.accenture.flight.model;

import com.accenture.flight.api.CountryDto;
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

    public CountryDto toCountryDto(){
        return CountryDto.builder()
                .id(this.id)
                .code(this.code)
                .name(this.name)
                .continent(this.continent)
                .wikipedia_link(this.wikipedia_link)
                .keywords(this.keywords).build();
    }
}

