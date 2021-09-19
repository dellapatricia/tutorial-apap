package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;

import java.util.List;

public interface BioskopService {
    void addBioskop(BioskopModel bioskop);

    List<BioskopModel> getBioskopList();

    List<BioskopModel> getBioskopBaru(String idBioskop);

    List<BioskopModel> deleteBioskop(String idBioskop);

    public BioskopModel getBioskopByIdBioskop(String idBioskop);
}
