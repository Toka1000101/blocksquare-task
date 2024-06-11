package org.acme.service.problem;

import jakarta.ws.rs.core.Response;
import org.acme.dto.CreateProblemRequestDto;
import org.acme.dto.ProblemDto;
import java.util.List;

public interface ProblemService {

    List<ProblemDto> getAllProblems();
    ProblemDto getProblemById(Long id);
    Response createProblem(CreateProblemRequestDto createProblemRequestDto);
    Response generateRandomProblems(List<CreateProblemRequestDto> createProblemRequestDtoList);
}
