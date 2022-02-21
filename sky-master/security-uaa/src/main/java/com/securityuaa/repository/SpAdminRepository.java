package com.securityuaa.repository;


import com.securityuaa.entity.SpAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * @author Wei
 */
@Repository
public interface SpAdminRepository extends JpaRepository<SpAdminEntity, Long>, JpaSpecificationExecutor<SpAdminEntity> {

    SpAdminEntity findByUsername(String name);

    @Query(value = "select distinct rp.power_ids\n" +
            "from t_user_role ur LEFT JOIN t_role_power rp ON ur.role_id = rp.role_id \n" +
            "where ur.uid = :uid", nativeQuery = true)
    List<String> getUserAllPowerIds(Long uid);

    @Query(value = "select p.permissions from t_power p where p.id in :powerIdSet and p.level in(1,2)", nativeQuery = true)
    List<String> getUserPermissions(Set<Long> powerIdSet);

}