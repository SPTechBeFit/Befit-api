package sptech.befitapi.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.befitapi.resources.repository.entity.Treino;

import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {
}
