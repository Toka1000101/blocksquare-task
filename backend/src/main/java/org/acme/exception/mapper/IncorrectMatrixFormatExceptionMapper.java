package org.acme.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.ErrorResponse;
import org.acme.exception.custom.IncorrectMatrixFormatException;

@Provider
public class IncorrectMatrixFormatExceptionMapper implements ExceptionMapper<IncorrectMatrixFormatException> {
    @Override
    public Response toResponse(IncorrectMatrixFormatException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(e.getMessage()))
                .build();
    }
}
