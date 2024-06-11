package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
public class Solution extends PanacheEntity {

    public Solution(Problem p, List<Boolean> solutionMatrix) {
        this.problem = p;
        this.solutionMatrix = solutionMatrix;
    }


    @OneToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    @MapsId
    private Problem problem;

    @Column(nullable = false)
    private List<Boolean> solutionMatrix;
}
