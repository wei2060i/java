package com.${projectName}.repository;


import com.${projectName}.entity.${domain?cap_first}Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
* @author Wei
*/
@Repository
public interface ${domain?cap_first}Repository extends JpaRepository<${domain?cap_first}Entity, ${ID}> , JpaSpecificationExecutor<${domain?cap_first}Entity> {

}