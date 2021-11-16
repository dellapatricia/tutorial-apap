package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.rest.PenjagaDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PenjagaRestService {
    PenjagaModel createPenjaga(PenjagaModel bioskop);

    List<PenjagaModel> retrieveListPenjaga();

    PenjagaModel getPenjagaByNoPenjaga(Long noBioskop);

    void updatePenjaga(Long noBioskop, PenjagaModel bioskopUpdate);

    void deletePenjaga(Long noCabang);

    PenjagaModel predictAge(PenjagaModel penjaga);

    List<PenjagaModel> orderedListPenjaga();

//    Mono<String> getStatus(Long noBioskop);
//
//    Mono<PenjagaDetail> postStatus();
}
