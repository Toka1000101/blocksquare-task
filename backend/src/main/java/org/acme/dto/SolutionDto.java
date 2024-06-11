package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SolutionDto {
    private Long problemId;
    private List<Boolean> solutionMatrix;
}
