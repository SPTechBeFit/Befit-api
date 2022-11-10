package sptech.befitapi.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sptech.befitapi.resources.repository.entity.Treino;
import sptech.befitapi.resources.repository.entity.TreinoFavorito;

import java.util.List;

@Repository
public interface TreinoFavoritoRepository extends JpaRepository<TreinoFavorito, Integer> {

    List<TreinoFavorito> findTreinoByUsuarioId(int id);
}
