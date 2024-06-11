package org.acme.exception.custom;

public class SolutionDoesNotExistException extends RuntimeException{

    public SolutionDoesNotExistException() {
        super("Solution does not exist");
    }
}
