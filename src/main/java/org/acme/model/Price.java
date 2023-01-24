package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
@AllArgsConstructor
public class Price implements Serializable {
    private final String id;
    private final Double value;
}
