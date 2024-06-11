package org.acme.service.solution;

import org.acme.dto.SolutionDto;

import java.util.List;

public interface SolutionService {
    List<SolutionDto> getAllSolutions();
    SolutionDto getSolutionByProblemId(Long id);
}

