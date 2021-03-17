package com.accenture.flight.model;

import lombok.AccessLevel;
import lombok.*;

@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class Topten {
    private int count;
    private String code;
    private String name;
}
