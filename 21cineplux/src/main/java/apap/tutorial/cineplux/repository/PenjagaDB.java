package apap.tutorial.cineplux.repository;
import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PenjagaDB extends JpaRepository<PenjagaModel, Long> {
    Optional<PenjagaModel> findByBioskop_NoBioskop(Long noBioskop);
    Optional<PenjagaModel> findByNopenjaga(Long nopenjaga);
    List<PenjagaModel> findAllByOrderByNamaPenjagaAsc();
}
