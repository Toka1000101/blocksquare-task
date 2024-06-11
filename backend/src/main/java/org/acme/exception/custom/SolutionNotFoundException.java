package org.acme.exception.custom;

public class SolutionNotFoundException extends RuntimeException{

    public SolutionNotFoundException(Long id) {
        super("Solution for problem  (id: " + id + ") is not present");
    }
}
