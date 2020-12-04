package cn.temptation.dao;

import cn.temptation.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryDao extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
}