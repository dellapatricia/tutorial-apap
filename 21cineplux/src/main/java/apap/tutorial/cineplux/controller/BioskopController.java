package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.model.BioskopModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class BioskopController {
    @Autowired
    private BioskopService bioskopService;

    @RequestMapping("/bioskop/add")
    public String addBioskop(
        @RequestParam(value = "idBioskop", required = true) String idBioskop,
        @RequestParam(value = "namaBioskop", required = true) String namaBioskop,
        @RequestParam(value = "alamat", required = true) String alamat,
        @RequestParam(value = "noTelepon", required = true) String noTelepon,
        @RequestParam(value = "jumlahStudio", required = true) int jumlahStudio,
        Model model
    ) {
        BioskopModel bioskopModel = new BioskopModel(idBioskop, namaBioskop, alamat, noTelepon, jumlahStudio);
        bioskopService.addBioskop(bioskopModel);
        model.addAttribute("idBioskop", idBioskop);
        return "add-bioskop";
    }

    @RequestMapping("/bioskop/viewall")
    public String listBioskop(Model model){
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();
        model.addAttribute("listBioskop", listBioskop);
        return "viewall-bioskop";
    }


    @RequestMapping("/bioskop/view")
    public String detailBioskop(
            @RequestParam(value = "idBioskop", required = true) String idBioskop,
            Model model
    ) {
        List<BioskopModel> bioskopModel = bioskopService.getBioskopBaru(idBioskop);
        model.addAttribute("bioskop", bioskopModel);
        return "view-bioskop";
    }


    @GetMapping(value="/bioskop/view/id-bioskop/{id}")
    public String getBioskopById(
            @PathVariable(value= "id") String id, Model model) {
        if (bioskopService.getBioskopList().contains(bioskopService.getBioskopByIdBioskop(id))) {
            return detailBioskop(id, model);
        }
        return "errorpage";
    }

    @GetMapping(value="bioskop/update/id-bioskop/{id}/jumlah-studio/{jumlah}")
    public String updateJumlahStudio(
            @PathVariable String id, @PathVariable int jumlah, Model model) {
        if (bioskopService.getBioskopList().contains(bioskopService.getBioskopByIdBioskop(id))){
            BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(id);
            bioskopModel.setJumlahStudio(jumlah);
            return "updatedpage";
        }

        return "errorpage";
    }

    @GetMapping(value="/bioskop/delete/id-bioskop/{id}")
    public String deleteBioskop(
            @PathVariable String id, Model model){
        if (bioskopService.getBioskopList().contains(bioskopService.getBioskopByIdBioskop(id))) {
            List<BioskopModel> listBioskopdel = bioskopService.deleteBioskop(id);
            model.addAttribute("listBioskop", listBioskopdel);
            return "deletedpage";
        }
        return "errorpage";
    }











}
