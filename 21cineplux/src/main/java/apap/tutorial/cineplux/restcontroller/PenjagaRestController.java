package apap.tutorial.cineplux.restcontroller;

import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.rest.PenjagaDetail;
import apap.tutorial.cineplux.service.PenjagaRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/v1")
public class PenjagaRestController {

    @Autowired
    private PenjagaRestService penjagaRestService;

    @PostMapping(value = "/penjaga")
    private ResponseEntity<String> createPenjaga(@Valid @RequestBody PenjagaModel Penjaga, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            penjagaRestService.createPenjaga(Penjaga);
            return ResponseEntity.ok("Create Penjaga Success");
        }
    }

    @GetMapping(value="/penjaga/{noPenjaga}")
    private PenjagaModel retrievePenjaga(@PathVariable("noPenjaga") Long noPenjaga){
        try{
            return penjagaRestService.getPenjagaByNoPenjaga(noPenjaga);
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Penjaga" + String.valueOf(noPenjaga) + " Not Found."
            );
        }
    }

    @DeleteMapping(value="/penjaga/{noPenjaga}")
    private ResponseEntity deletePenjaga(@PathVariable("noPenjaga") Long noPenjaga){
        try{
            penjagaRestService.deletePenjaga(noPenjaga);
            return ResponseEntity.ok("Penjaga has been deleted!");
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Penjaga Not Found."
            );
        }catch (UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bioskop is still open!"
            );
        }
    }

    @PutMapping(value="/penjaga/{noPenjaga}")
    private ResponseEntity<String> updatePenjaga(@PathVariable("noPenjaga") Long noPenjaga, @RequestBody PenjagaModel Penjaga){
        try{
            penjagaRestService.updatePenjaga(noPenjaga, Penjaga);
            return ResponseEntity.ok("Update Penjaga Success");
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Penjaga" + String.valueOf(noPenjaga) + " Not Found."
            );
        }
    }

    @GetMapping(value="/list-penjaga")
    private List<PenjagaModel> retrieveListPenjaga(){
        return penjagaRestService.retrieveListPenjaga();
    }

    @GetMapping(value="/list-penjaga/order-by-name")
    private List<PenjagaModel> orderedListPenjaga() {
        return penjagaRestService.orderedListPenjaga();
    }

    @GetMapping(value="/penjaga/umur/{noPenjaga}")
    private PenjagaModel predictAge(@PathVariable("noPenjaga") Long noPenjaga){
        try{
            PenjagaModel penjaga = penjagaRestService.getPenjagaByNoPenjaga(noPenjaga);
            return penjagaRestService.predictAge(penjaga);
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Penjaga" + String.valueOf(noPenjaga) + " Not Found."
            );
        }
    }

}
