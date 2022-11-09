package sptech.befitapi.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;

import java.util.List;

@Repository
public interface IngredientesDietaRepository extends JpaRepository<IngredientesDieta, Integer> {

    List<IngredientesDieta> findIngredientesDietaByDietaId(Integer id);
}
