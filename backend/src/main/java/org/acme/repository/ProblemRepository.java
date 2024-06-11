package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Problem;

import java.util.Optional;

@ApplicationScoped
public class ProblemRepository implements PanacheRepository<Problem> {
    public Optional<Problem> getById(Long id) {
        return find("id", id).firstResultOptional();
    }
}
