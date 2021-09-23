package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util. ArrayList;
import java.util.List;
import java.time.LocalTime;

@Controller
public class BioskopController {

    @Qualifier("bioskopServiceImpl")
    @Autowired
    private BioskopService bioskopService;

    @GetMapping("/bioskop/add")
    public String addBioskopForm(Model model) {
        model.addAttribute("bioskop",new BioskopModel());
        return "form-add-bioskop";
    }

    @PostMapping("/bioskop/add")
    public String addBioskopsubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ){
        bioskopService.addBioskop(bioskop);
        model.addAttribute( "noBioskop", bioskop.getNoBioskop());
        return "add-bioskop";
    }

    @GetMapping("/bioskop/viewall")
    public String listBioskop(Model model) {
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();
        model.addAttribute ( "listBioskop",listBioskop);
        return "viewall-bioskop" ;
    }

    @GetMapping("/bioskop/view")
    public String viewDetailBioskop(
            @RequestParam(value = "noBioskop") Long noBioskop,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        List<PenjagaModel> listPenjaga = bioskop.getListPenjaga();

        model.addAttribute( "bioskop", bioskop);
        model.addAttribute("listPenjaga", listPenjaga);
        model.addAttribute("now",LocalTime.now());

        return "view-bioskop";
    }

    @GetMapping("/bioskop/update/{noBioskop}")
    public String updateBioskopForm(
            @PathVariable Long noBioskop,
            Model model
    ){
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        model.addAttribute( "bioskop",bioskop);
        return"form-update-bioskop" ;
    }

    @PostMapping("/bioskop/update")
    public String updateBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ){
        bioskopService.updateBioskop(bioskop);
        model.addAttribute( "noBioskop",bioskop.getNoBioskop());
        return "update-bioskop";
    }

    @GetMapping("/bioskop/delete/{noBioskop}")
    public String deleteBioskop(
            @PathVariable Long noBioskop,
            Model model
    ){
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        boolean is_nopenjaga = false;
        boolean is_tutup = false;
        if (bioskop.getListPenjaga().size() < 1){
            is_nopenjaga = true;
            if(!((LocalTime.now().isAfter(bioskop.getWaktuBuka()))&&(LocalTime.now().isBefore(bioskop.getWaktuTutup())))){
                is_tutup = true;
                bioskopService.deleteBioskop(bioskop);
            }
        }
        model.addAttribute("noBioskop",bioskop.getNoBioskop());
        model.addAttribute("is_nopenjaga",is_nopenjaga);
        model.addAttribute("is_tutup",is_tutup);
        return "delete-bioskop";
    }

}