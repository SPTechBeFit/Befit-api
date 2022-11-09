package sptech.befitapi.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.befitapi.resources.repository.entity.Exercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {
}
