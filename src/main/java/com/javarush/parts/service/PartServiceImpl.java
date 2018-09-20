package com.javarush.parts.service;

import com.javarush.parts.dao.PartRepository;
import com.javarush.parts.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author dubetskyi_ov on 17.09.2018
 */

@Service("partService")
@Repository
@Transactional

public  class PartServiceImpl implements PartService {
    @Autowired
    private PartRepository partRepository;


    @Override
    @Transactional(readOnly=true)
    public List<Part> findAll() {
        return (List<Part>) partRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Part> findById(Long id) {
        return partRepository.findById (id);
    }

    @Override
    public Part save(Part part) {
        return partRepository.save (part);
    }





    @Override
    public Part update(Part part, Long id) {
        Optional<Part> entity = partRepository.findById (id);
        if (entity.isPresent()) {
            Part opart = entity.get ();

            if (part.getTitle () != null) opart.setTitle (part.getTitle ());
            if (part.getAmount () != null) opart.setAmount (part.getAmount  ());

            opart.setMust (part.getMust ());

            return partRepository.save (opart);
        }
        else return null;
    }

    @Override
    public Page<Part> search(String term, Pageable pageable) {
        return partRepository.findBySearchParams(term, pageable);
    }

    @Override
    public Page<Part> search(String term, boolean isMust, Pageable pageable) {
        return partRepository.findBySearchParamsAndIsMust(term, isMust, pageable);
    }

    @Override
    public void delete(Part part) {
        partRepository.delete (part);
    }


}
