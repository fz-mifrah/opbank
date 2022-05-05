package com.sgaraba.library.repository;

import com.sgaraba.library.domain.Recharge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Recharge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RechargeRepository extends JpaRepository<Recharge, Long> {}
