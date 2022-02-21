package com.skyadmin.repository;


import com.skyadmin.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
* @author Wei
*/
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> , JpaSpecificationExecutor<RoleEntity> {

}