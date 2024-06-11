package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Solution;

import java.util.Optional;

@ApplicationScoped
public class SolutionRepository implements PanacheRepository<Solution> {

    public Optional<Solution> getSolutionByProblemId(Long id) {
        return find("id", id).firstResultOptional();
    }
}
