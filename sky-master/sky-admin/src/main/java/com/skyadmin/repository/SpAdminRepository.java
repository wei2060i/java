package com.skyadmin.repository;


import com.skyadmin.entity.SpAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
* @author Wei
*/
@Repository
public interface SpAdminRepository extends JpaRepository<SpAdminEntity, Long> , JpaSpecificationExecutor<SpAdminEntity> {

}