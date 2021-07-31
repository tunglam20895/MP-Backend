package itsol.mp.app.services;

import itsol.mp.app.entities.Divisons;
import itsol.mp.app.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {
    @Autowired
    private DivisionRepository divisionRepository;

    public List<Divisons> getAllDivision(){
        return divisionRepository.findAll();
    }

    public Divisons findById(long id){
        return divisionRepository.findDivisonsById(id);
    }
}
