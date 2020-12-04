package cn.temptation.dao;

import cn.temptation.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductDao extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Query(value = "SELECT * FROM product WHERE productid = ?1", nativeQuery = true)
    Product findByProductid(Integer productid);
}