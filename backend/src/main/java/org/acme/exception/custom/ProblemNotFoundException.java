package org.acme.exception.custom;

public class ProblemNotFoundException extends RuntimeException {

    public ProblemNotFoundException(Long id) {
        super("Problem with id: " + id + " is not present");
    }

}
