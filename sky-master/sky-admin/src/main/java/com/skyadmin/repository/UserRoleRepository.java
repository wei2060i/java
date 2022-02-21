package com.skyadmin.repository;


import com.skyadmin.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
* @author Wei
*/
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> , JpaSpecificationExecutor<UserRoleEntity> {

}