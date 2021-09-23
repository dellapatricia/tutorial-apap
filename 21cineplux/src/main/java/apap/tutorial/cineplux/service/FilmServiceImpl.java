package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.FilmModel;
import apap.tutorial.cineplux.repository.FilmDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmDB filmDB;

    @Override
    public void addFilm(FilmModel bioskop) { filmDB.save(bioskop); }
}
