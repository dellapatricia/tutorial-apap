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
    public void updateBioskop(BioskopModel bioskop) {
    }

    @Override
    public List<BioskopModel> getBioskopList() {
        return listBioskop;
    }

    @Override
    public BioskopModel getBioskopByNoBioskop(Long noBioskop) {
        return null;
    }

    @Override
    public void deleteBioskop(BioskopModel bioskop) {

    }

//    public BioskopModel getBioskopByIdBioskop(String idBioskop) {
//        BioskopModel getIdB = null;
//        for (BioskopModel b : listBioskop) {
//            if (idBioskop.equals(b.getNoBioskop())) {
//                getIdB = b;
//            }
//        }
//        return null;
//    }

//    public List<BioskopModel> deleteBioskop(String idBioskop){
//        System.out.println("oke: " + listBioskop.get(0));
//        for (BioskopModel b : listBioskop){
//            if (idBioskop.equals(b.getIdBioskop())) {
//                listBioskop.remove(b);
//                break;
//            }
//        }
//        return listBioskop;
//    }

}
