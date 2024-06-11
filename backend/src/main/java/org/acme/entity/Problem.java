package org.acme.entity;

import io.quarkus.arc.All;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "problem")
public class Problem extends PanacheEntity {

    public Problem(List<Boolean> description, Solution solution, int matrixSize) {
        this.description = description;
        this.solution = solution;
        this.matrixSize = matrixSize;
    }

    @Column(nullable = false)
    private List<Boolean> description;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "problem")
    private Solution solution;

    @Column(nullable = false)
    private int matrixSize;

    public Problem(List<Boolean> problem) {
        this.description = problem;
    }
}
