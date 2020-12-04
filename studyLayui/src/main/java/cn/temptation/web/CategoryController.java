package cn.temptation.web;

import cn.temptation.dao.CategoryDao;
import cn.temptation.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/category")
    public String index() {
        return "category";
    }

    @RequestMapping("/category_list")
    @ResponseBody
    public Map<String, Object> categoryList(@RequestParam Map<String, Object> queryParams) {
        Map<String, Object> result = new HashMap<>();

        try {
            Integer page = Integer.parseInt(queryParams.get("page").toString());
            Integer limit = Integer.parseInt(queryParams.get("limit").toString());
            String keyword = (String) queryParams.get("keyword");

            // 创建查询规格对象
            Specification<Category> specification = (Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                Predicate predicate = null;

                if (keyword != null && !"".equals(keyword)) {
                    Path path = root.get("categoryname");
                    predicate = cb.like(path, "%" + keyword + "%");
                }

                return predicate;
            };

            Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.ASC, "categoryid");

            Page<Category> categories = categoryDao.findAll(specification, pageable);

            result.put("code", 0);
            result.put("msg", "查询OK");
            result.put("count", categories.getTotalElements());
            result.put("data", categories.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "服务器内部错误");
            result.put("count", 0);
            result.put("data", new ArrayList());
        }

        return result;
    }

    @RequestMapping("/category_delete")
    @ResponseBody
    public Integer categoryDelete(@RequestParam String categoryid) {
        try {
            categoryDao.deleteById(Integer.parseInt(categoryid));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @RequestMapping("/category_view")
    public String view(Integer categoryid, Model model) {
        Category category = new Category();
        if (categoryid != null) {
            category = categoryDao.getOne(categoryid);
        }
        model.addAttribute("category", category);
        return "category_view";
    }

    @RequestMapping("/category_update")
    @ResponseBody
    public Integer categoryUpdate(Category category) {
        try {
            categoryDao.save(category);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}