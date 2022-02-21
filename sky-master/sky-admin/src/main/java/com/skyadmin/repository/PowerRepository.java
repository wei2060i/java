package com.skyadmin.repository;


import com.skyadmin.entity.PowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
* @author Wei
*/
@Repository
public interface PowerRepository extends JpaRepository<PowerEntity, Long> , JpaSpecificationExecutor<PowerEntity> {

}