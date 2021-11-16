package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.PenjagaDB;
import apap.tutorial.cineplux.rest.PenjagaDetail;
import apap.tutorial.cineplux.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PenjagaRestServiceImpl implements PenjagaRestService {

    private final WebClient webClient;

    @Autowired
    private PenjagaDB PenjagaDB;

    @Override
    public PenjagaModel createPenjaga(PenjagaModel Penjaga) {
        return PenjagaDB.save(Penjaga);
    }

    @Override
    public List<PenjagaModel> retrieveListPenjaga() {
        return PenjagaDB.findAll();
    }

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga) {
        Optional<PenjagaModel> Penjaga = PenjagaDB.findByNopenjaga(noPenjaga);

        if(Penjaga.isPresent()) {
            return Penjaga.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updatePenjaga(Long noPenjaga, PenjagaModel PenjagaUpdate) {
        LocalTime now = LocalTime.now();
        PenjagaModel Penjaga = getPenjagaByNoPenjaga(noPenjaga);
        Penjaga.setNamaPenjaga(PenjagaUpdate.getNamaPenjaga());
        Penjaga.setJenisKelamin(PenjagaUpdate.getJenisKelamin());

        if((now.isBefore(Penjaga.getBioskop().getWaktuBuka()) || now.isAfter(Penjaga.getBioskop().getWaktuTutup()))) {
            PenjagaDB.save(Penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop is still open!");
        }
    }

    @Override
    public void deletePenjaga(Long noPenjaga) {
        LocalTime now = LocalTime.now();
        PenjagaModel Penjaga = getPenjagaByNoPenjaga(noPenjaga);

        if((now.isBefore(Penjaga.getBioskop().getWaktuBuka()) || now.isAfter(Penjaga.getBioskop().getWaktuTutup()))) {
            PenjagaDB.delete(Penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop is still open!");
        }
    }

//    public PenjagaRestServiceImpl(WebClient.Builder webclientBuilder){
//        this.webClient = webclientBuilder.baseUrl(Setting.PenjagaUrl).build();
//    }

//    @Override
//    public Mono<String> getStatus(Long noPenjaga){
//        return this.webClient.get().uri( "/rest/Penjaga/" + noPenjaga + "/status")
//                .retrieve()
//                .bodyToMono(String.class);
//
//    }
//

    public PenjagaRestServiceImpl(WebClient.Builder webclientBuilder){
        this.webClient = webclientBuilder.baseUrl(Setting.agifyUrl).build();
    }

    @Override
    public PenjagaModel predictAge(PenjagaModel penjaga){
        LocalTime now = LocalTime.now();
        if((now.isBefore(penjaga.getBioskop().getWaktuBuka()) || now.isAfter(penjaga.getBioskop().getWaktuTutup()))) {
            String namaPenjaga = penjaga.getNamaPenjaga();
            if (namaPenjaga.contains(" ")){
                String[] namaString = penjaga.getNamaPenjaga().split(" ");
                namaPenjaga = namaString[0];
            }
            Mono<PenjagaDetail> umur =  this.webClient.get().uri("?name=" + namaPenjaga)
                    .retrieve()
                    .bodyToMono(PenjagaDetail.class);

            penjaga.setUmur(Integer.toString(umur.block().getAge()));
            PenjagaDB.save(penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop is still open!");
        }
        return penjaga;
    }

    @Override
    public List<PenjagaModel> orderedListPenjaga() {
        return PenjagaDB.findAllByOrderByNamaPenjagaAsc();
    }


}
