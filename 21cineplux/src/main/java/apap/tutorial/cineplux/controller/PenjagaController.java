package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.PenjagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
public class PenjagaController {

    @Qualifier("penjagaServiceImpl")
    @Autowired
    PenjagaService penjagaService;

    @Qualifier("bioskopServiceImpl")
    @Autowired
    BioskopService bioskopService;

    @GetMapping("penjaga/add/{noBioskop}")
    public String addPenjagaForm(@PathVariable Long noBioskop, Model model) {
        PenjagaModel penjaga = new PenjagaModel();
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        penjaga.setBioskop(bioskop);
        model.addAttribute("penjaga", penjaga);
        return "form-add-penjaga";
    }

    @PostMapping("/penjaga/add")
    public String addPenjagasubmit(
                @ModelAttribute PenjagaModel penjaga,
                Model model
    ){
        penjagaService.addPenjaga(penjaga);
        model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
        model.addAttribute("namaPenjaga", penjaga.getNamaPenjaga());
        return "add-penjaga";
    }

    @GetMapping("/penjaga/update/{nopenjaga}")
    public String updatePenjagaForm(
            @PathVariable Long nopenjaga,
            Model model
    ){
        PenjagaModel penjaga = penjagaService.getPenjagaByNoPenjaga(nopenjaga);
        BioskopModel bioskop = penjaga.getBioskop();
        boolean is_tutup = false;
        if(!((LocalTime.now().isAfter(bioskop.getWaktuBuka()))&&(LocalTime.now().isBefore(bioskop.getWaktuTutup())))){
            is_tutup = true;
        }
        model.addAttribute("is_tutup", is_tutup);
        model.addAttribute("penjaga",penjaga);
        model.addAttribute("noBioskop",bioskop.getNoBioskop());
        return"form-update-penjaga";
    }
    @PostMapping("/penjaga/update")
    public String updatePenjagaSubmit(
            @ModelAttribute PenjagaModel penjaga,
            Model model
    ){
        penjagaService.updatePenjaga(penjaga);
        model.addAttribute( "namaPenjaga",penjaga.getNamaPenjaga());
        model.addAttribute( "noBioskop",penjaga.getBioskop().getNoBioskop());
        return "update-penjaga";
    }


    @PostMapping("/penjaga/delete")
    public String deletePenjagaSubmit(
            @ModelAttribute BioskopModel bioskop, Model model
    ){
        boolean is_tutup = false;
        for (PenjagaModel penjaga: bioskop.getListPenjaga()){
            if(!((LocalTime.now().isAfter(bioskop.getWaktuBuka()))&&(LocalTime.now().isBefore(bioskop.getWaktuTutup())))){
                is_tutup = true;
                penjagaService.deletePenjaga(penjaga);
            }
        }
        model.addAttribute("is_tutup", is_tutup);
        model.addAttribute("noBioskop", bioskop.getNoBioskop());
        return "delete-penjaga";
    }

}