package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;

import java.util.List;

public interface PenjagaService {
    void addPenjaga(PenjagaModel penjaga);
    void updatePenjaga(PenjagaModel penjaga);
    void deletePenjaga(Long nopenjaga);
    PenjagaModel getPenjagaByNoBioskop(Long noBioskop);
    PenjagaModel getPenjagaByNoPenjaga(Long nopenjaga);
}
