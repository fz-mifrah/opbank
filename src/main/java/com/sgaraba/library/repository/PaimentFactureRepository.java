package com.sgaraba.library.repository;

import com.sgaraba.library.domain.PaimentFacture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PaimentFacture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaimentFactureRepository extends JpaRepository<PaimentFacture, Long> {}
