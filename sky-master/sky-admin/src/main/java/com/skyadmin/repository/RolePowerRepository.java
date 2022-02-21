package com.skyadmin.repository;


import com.skyadmin.entity.RolePowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
* @author Wei
*/
@Repository
public interface RolePowerRepository extends JpaRepository<RolePowerEntity, Long> , JpaSpecificationExecutor<RolePowerEntity> {

}