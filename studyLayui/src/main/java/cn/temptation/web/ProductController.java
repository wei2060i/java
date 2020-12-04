package cn.temptation.web;

import cn.temptation.dao.CategoryDao;
import cn.temptation.dao.ProductDao;
import cn.temptation.domain.Category;
import cn.temptation.domain.Product;
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
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/product")
    public String index() {
        return "product";
    }

    @RequestMapping("/product_list")
    @ResponseBody
    public Map<String, Object> productList(@RequestParam Map<String, Object> queryParams) {
        Map<String, Object> result = new HashMap<>();

        try {
            Integer page = Integer.parseInt(queryParams.get("page").toString());
            Integer limit = Integer.parseInt(queryParams.get("limit").toString());
            String keyword = (String) queryParams.get("keyword");

            // 创建查询规格对象
            Specification<Product> specification = (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                Predicate predicate = null;

                if (keyword != null && !"".equals(keyword)) {
                    Path path = root.get("productname");
                    predicate = cb.like(path, "%" + keyword + "%");
                }

                return predicate;
            };

            Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.ASC, "productid");

            Page<Product> products = productDao.findAll(specification, pageable);

            result.put("code", 0);
            result.put("msg", "查询OK");
            result.put("count", products.getTotalElements());
            result.put("data", products.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "服务器内部错误");
            result.put("count", 0);
            result.put("data", new ArrayList());
        }

        return result;
    }

    @RequestMapping("/product_delete")
    @ResponseBody
    public Integer productDelete(@RequestParam String productid) {
        try {
            productDao.deleteById(Integer.parseInt(productid));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @RequestMapping("/product_view")
    public String view(Integer productid, Model model) {
        Product product = new Product();
        if (productid != null) {
            product = productDao.findByProductid(productid);
        }
        model.addAttribute("product", product);
        return "product_view";
    }

    @RequestMapping("/category_load")
    @ResponseBody
    public List<Category> categoryList() {
        return categoryDao.findAll();
    }

    @RequestMapping("/product_update")
    @ResponseBody
    public Integer productUpdate(Product product) {
        System.out.println("66666666666");
        System.out.println(product.getProductid());
        System.out.println(product.getProductname());
        System.out.println(product.getCategory().getCategoryid());
        try {
            productDao.save(product);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}