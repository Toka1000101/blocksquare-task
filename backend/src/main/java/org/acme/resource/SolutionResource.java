package org.acme.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.acme.dto.SolutionDto;
import org.acme.service.solution.SolutionServiceImpl;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/solutions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class SolutionResource {

    private final SolutionServiceImpl solutionService;

    @GET
    @Operation(summary = "get all solutions")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public List<SolutionDto> getAllSolutions() {
        return solutionService.getAllSolutions();
    }

    @GET
    @Path("/problem/{id}")
    @Operation(summary = "get solution by problemId")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public SolutionDto getSolutionById(@PathParam("id") Long id) {
        return solutionService.getSolutionByProblemId(id);
    }
}
