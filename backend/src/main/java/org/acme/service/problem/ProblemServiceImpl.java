package org.acme.service.problem;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.acme.dto.CreateProblemRequestDto;
import org.acme.dto.CreateProblemResponseDto;
import org.acme.dto.ProblemDto;
import org.acme.entity.Problem;
import org.acme.entity.Solution;
import org.acme.exception.custom.ProblemNotFoundException;
import org.acme.repository.ProblemRepository;
import org.acme.service.solution.SolutionUtils;
import org.acme.utils.LightsOutSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemServiceImpl.class);
    private final ProblemRepository problemRepository;

    public List<ProblemDto> getAllProblems() {

        return problemRepository.listAll().stream().
                map(x -> new ProblemDto(x.id, x.getDescription(), x.getMatrixSize()))
                .toList();
    }

    public ProblemDto getProblemById(Long id) {
        Problem p = problemRepository.getById(id)
                .orElseThrow(() -> new ProblemNotFoundException(id));
        return new ProblemDto(p.id, p.getDescription(), p.getMatrixSize());
    }

    public Response createProblem(CreateProblemRequestDto createProblemRequestDto) {
        int[][] matrix = createProblemRequestDto.getMatrix();
        ProblemUtils.validateMatrix(matrix);
        LightsOutSolver solver = new LightsOutSolver(matrix);
        int[] solution = solver.solve();

        LOGGER.info("Time to solve: " + solver.getTimeToRun());
        LOGGER.info("Number of moves: " + solver.getNumberOfMoves());
        // save solution and problem
        saveProblemAndSolution(matrix, solution);

        return Response.status(Response.Status.CREATED)
                .entity(new CreateProblemResponseDto(solution))
                .build();
    }


    private void saveProblemAndSolution(int[][] matrix, int[] solution){
        List<Boolean> problem = ProblemUtils.mapToBooleanList(matrix);
        List<Boolean> boolSolution = SolutionUtils.mapToBooleanList(solution);

        Solution s = Solution.builder()
                .solutionMatrix(boolSolution)
                .build();

        Problem p = Problem.builder()
                .description(problem)
                .solution(s)
                .matrixSize(matrix.length)
                .build();
        s.setProblem(p);

        PanacheEntityBase.persist(p);

    }

    public Response generateRandomProblems(List<CreateProblemRequestDto> createProblemRequestDtoList) {

        createProblemRequestDtoList
                .forEach(this::createProblem);

        return Response.status(Response.Status.CREATED).build();
    }
}
