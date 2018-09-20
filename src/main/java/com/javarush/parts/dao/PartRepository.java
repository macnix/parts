package com.javarush.parts.dao;

import com.javarush.parts.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author dubetskyi_ov on 13.09.2018
 */
@Repository
public interface PartRepository extends PagingAndSortingRepository<Part, Long> {
    @Query("SELECT t FROM Part t WHERE " +
            "(LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Part> findBySearchParams(
            @Param("searchTerm") String searchTerm,
            Pageable pageRequest
    );

    @Query("SELECT t FROM Part t WHERE " +
            "(LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +

            " AND t.must = :isMust)")
    Page<Part> findBySearchParamsAndIsMust(
            @Param("searchTerm") String searchTerm,
            @Param("isMust") boolean isMust,
            Pageable pageRequest
    );



}
