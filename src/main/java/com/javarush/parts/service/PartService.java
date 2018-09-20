package com.javarush.parts.service;

import com.javarush.parts.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author dubetskyi_ov on 15.09.2018
 */

public interface PartService {
    List<Part> findAll();
    Optional<Part> findById(Long id);
    Part save(Part part);

    Part update(Part part, Long id);
    Page<Part> search(String term,  Pageable pageable);
    Page<Part> search(String term,  boolean isMust, Pageable pageable);


    void delete(Part part);
}
