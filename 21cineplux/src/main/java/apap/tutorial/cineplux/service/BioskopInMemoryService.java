package apap.tutorial.cineplux.service;
import apap.tutorial.cineplux.model.BioskopModel;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class BioskopInMemoryService implements BioskopService{

    private List<BioskopModel> listBioskop;

    public BioskopInMemoryService(){
        listBioskop = new ArrayList<>();
    }

    @Override
    public void addBioskop(BioskopModel bioskop){
        listBioskop.add(bioskop);
    }

    @Override
    public List<BioskopModel> getBioskopList() {
        return listBioskop;
    }

    @Override
    public BioskopModel getBioskopByIdBioskop(String idBioskop) {
        BioskopModel getIdB = null;
        for (BioskopModel b : listBioskop) {
            if (idBioskop.equals(b.getIdBioskop())) {
                getIdB = b;
            }
        }
        return getIdB;
    }

    public List<BioskopModel> getBioskopBaru (String idBioskop) {
        List<BioskopModel> bioskopBaru = new LinkedList<>();
        for (BioskopModel b : listBioskop) {
            if (idBioskop.equals(b.getIdBioskop())) {
                bioskopBaru.add(b);
            }
        }
        return bioskopBaru;
    }

    @Override
    public List<BioskopModel> deleteBioskop(String idBioskop){
        for (BioskopModel b : listBioskop){
            if (idBioskop.equals(b.getIdBioskop())) {
                listBioskop.remove(b);
                break;
            }
        }
        return listBioskop;
    }



}
