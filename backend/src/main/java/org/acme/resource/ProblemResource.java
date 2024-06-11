package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.acme.dto.CreateProblemRequestDto;
import org.acme.dto.ProblemDto;
import org.acme.service.problem.ProblemServiceImpl;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.print.attribute.standard.Media;
import java.util.List;


@Path("/problems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ProblemResource {

    private final ProblemServiceImpl problemService;

    @GET
    @Operation(summary = "List all problems")
    @APIResponse(responseCode = "200", description = "All problems returned",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public List<ProblemDto> getAllProblems() {
       return problemService.getAllProblems();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get problem by id")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public ProblemDto getProblemById(@PathParam("id") Long id) {
        return problemService.getProblemById(id);
    }
    @POST
    @Transactional
    @Operation(summary = "create problem if solution exists")
    @APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response createProblem(CreateProblemRequestDto createProblemRequestDto) {

        return problemService.createProblem(createProblemRequestDto);
    }

    @POST
    @Transactional
    @Path("/test")
    @Operation(summary = "Add test data, creates multiple problems if solution exist for all of them")
    @APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response fillDbWithTestProblems(List<CreateProblemRequestDto> createProblemRequestDtoList) {
        return problemService.generateRandomProblems(createProblemRequestDtoList);
    }
}
