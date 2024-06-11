package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProblemDto {
    private Long id;
    private List<Boolean> description;
    private int size;
}
