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

    @Override
    public List<BioskopModel> deleteBioskop(String idBioskop){
        System.out.println("oke: " + listBioskop.get(0));
        for (BioskopModel b : listBioskop){
            if (idBioskop.equals(b.getIdBioskop())) {
                listBioskop.remove(b);
                break;
            }
        }
        return listBioskop;
    }

}
