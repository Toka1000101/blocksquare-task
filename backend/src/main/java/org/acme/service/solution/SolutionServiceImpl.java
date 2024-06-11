package org.acme.service.solution;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.acme.dto.SolutionDto;
import org.acme.entity.Solution;
import org.acme.exception.custom.SolutionNotFoundException;
import org.acme.repository.SolutionRepository;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class SolutionServiceImpl implements SolutionService {

    private final SolutionRepository solutionRepository;

    @Override
    public List<SolutionDto> getAllSolutions() {
        return solutionRepository.listAll().stream()
                .map(x -> new SolutionDto(x.getProblem().id, x.getSolutionMatrix()))
                .toList();
    }

    @Override
    public SolutionDto getSolutionByProblemId(Long id) {
        Solution s = solutionRepository.getSolutionByProblemId(id)
                .orElseThrow(() -> new SolutionNotFoundException(id));
        return new SolutionDto(s.id, s.getSolutionMatrix());
    }
}

