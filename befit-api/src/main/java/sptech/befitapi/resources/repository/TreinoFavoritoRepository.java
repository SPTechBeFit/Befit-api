package sptech.befitapi.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sptech.befitapi.resources.repository.entity.TreinoFavorito;

import java.util.List;

@Repository
public interface TreinoFavoritoRepository extends JpaRepository<TreinoFavorito, Integer> {

    List<TreinoFavorito> findTreinoByUsuarioId(int id);

    @Transactional
    @Modifying
    void deleteByUsuarioIdAndTreinoId(int usuarioId, int treinoId);
}
