package sptech.befitapi.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sptech.befitapi.resources.repository.entity.DietaFavorita;

import java.util.List;

@Repository
public interface DietaFavoritaRepository extends JpaRepository<DietaFavorita, Integer> {

    List<DietaFavorita> findDietaByUsuarioId(Integer usuarioId);

    DietaFavorita findDietaFavoritaByUsuarioIdAndDietaId(int usuarioId, int dietaId);
    @Transactional
    @Modifying
    void deleteDietaFavoritaById(int dietaFavoitaId);
}
