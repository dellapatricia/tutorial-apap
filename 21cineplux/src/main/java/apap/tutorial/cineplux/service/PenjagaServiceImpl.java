package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.PenjagaDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PenjagaServiceImpl implements PenjagaService {
    @Autowired
    PenjagaDB penjagaDB;

    @Override
    public void addPenjaga(PenjagaModel penjaga) {
        penjagaDB.save(penjaga);
    }

    @Override
    public void updatePenjaga(PenjagaModel penjaga) {
        penjagaDB.save(penjaga);
    }

    @Override
    public void deletePenjaga(PenjagaModel penjaga) {
        penjagaDB.delete(penjaga);
    }

    @Override
    public PenjagaModel getPenjagaByNoBioskop(Long noBioskop){
        Optional<PenjagaModel> penjaga = penjagaDB.findByBioskop_NoBioskop(noBioskop);
        if (penjaga.isPresent()) {
            return penjaga.get();
        }
        return null;

    }

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long nopenjaga){
        Optional<PenjagaModel> penjaga = penjagaDB.findByNopenjaga(nopenjaga);
        if (penjaga.isPresent()) {
            return penjaga.get();
        }
        return null;

    }

}