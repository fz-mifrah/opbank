package com.sgaraba.library.repository;

import com.sgaraba.library.domain.Operateur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Operateur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperateurRepository extends JpaRepository<Operateur, Long> {}
