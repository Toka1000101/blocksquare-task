package org.acme.exception.mapper;


import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.ErrorResponse;
import org.acme.exception.custom.ProblemNotFoundException;

@Provider
public class ProblemNotFoundExceptionMapper implements ExceptionMapper<ProblemNotFoundException> {

    @Override
    public Response toResponse(ProblemNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(e.getMessage()))
                .build();
    }
}
