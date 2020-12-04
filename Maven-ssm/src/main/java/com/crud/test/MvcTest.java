package com.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MvcTest {
	//传入springmvc的ioc
	@Autowired
	private WebApplicationContext context;
	//虚拟mvc请求
	private MockMvc mockMvc;
	@Before
	public void initMockMvc() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
	//模拟请求拿到返回值	
	 MvcResult	result=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
	 //请求成功后  请求域里面有pageInfo,我们可以取出pageInfo验证
	 MockHttpServletRequest request = result.getRequest();
	 PageInfo pi=(PageInfo) request.getAttribute("pageInfo");
	 System.out.println("当前页码"+pi.getPageNum());
	 System.out.println("总页码"+pi.getPages());
	 System.out.println("总记录数"+pi.getTotal());
	 System.out.println("在页码需要联续显示的页码");
	 int[] Nums = pi.getNavigatepageNums();
	 for (int i : Nums) {
		System.out.println("\t"+i);
	}
	 List<Employee> list = pi.getList();
	 for (Employee employee : list) {
		System.out.println("id"+employee.getEmpId());
	}
	}
	@Test
	public void test1() {
		String regx="(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		System.out.println("看看k".matches(regx));
	}
	
}
