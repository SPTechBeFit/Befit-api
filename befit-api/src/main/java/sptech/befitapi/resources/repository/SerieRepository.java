package sptech.befitapi.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.befitapi.resources.repository.entity.Serie;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer> {
    List<Serie> findByTreinoId(int id);
}
