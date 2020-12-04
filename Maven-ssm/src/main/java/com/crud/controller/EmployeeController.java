package com.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.bean.Employee;
import com.crud.bean.Msg;
import com.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpByI(@PathVariable("ids") String ids) {
		if(ids.contains("-")) {
			List<Integer> id=new ArrayList<Integer>();
			String[] strs = ids.split("-");
			for (String string : strs) {
				id.add(Integer.parseInt(string));
			}
			System.out.println(id);
			employeeService.deleteBatch(id);
		}else {
			int id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}
	/*直接发送 ajax put形式请求
	 *封装的数据 Employee
	 *[empId=1024, empName=null, gender=null, email=null, dId=null]
	 *请求体中有数据 但Employee封装不进去
	 *发送sql错误 update tbl_emp  where emp_id=1024
	 *原因  tomcat
	 *		1请求体中的数据 封装成一个map
	 *		2request.getParameter("empName") 就会从这个map中取值
	 *		3springmvc封装POJO对象的时候 
	 *			会把POJO中每个属性的值 request.getParameter("empName")
	 *ajax发送put请求 ,请求中的数据拿不到 request.getParameter("empName")拿不到
	 *tomcat 不会把put请求体里面的数据封装成map,只有post形式的请求才封装请求体里面的数据
	 *解决方式 1ajax发送post  请求体数据后面加 &method=put
	 *或者2在web.xml配置HttpPutFormContentFilter过滤器
	 *他的作用 将请求体中的数据解析包装成 一个map
	 *request被重新包装   request.getParameter被重写 
	 * 就会从自己封装成的map里面 取数据
	 */
	//更新
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee){
		System.out.println(employee.toString());
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName") String empName) {
		String regx="(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg","用户名必须是2-5位或者6-16位英文数字组合");
		}
		boolean b=employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg","用户名不可用");
		}
	}
	//保存用post
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		//jsr303校验
		if(result.hasErrors()) {
			Map<String,Object> map=new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				System.out.println("错误字段名"+error.getField());
				System.out.println("错误信息"+error.getDefaultMessage());
				map.put(error.getField(),error.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1") Integer pn) {
		//引入pageHelper  传入页码和每页大小
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps=employeeService.getAll();
		//PageInfo包装封装后的查询结果，将PageInfo交给页码就可以
		//传入联续显示的页码5
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
	}
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model) {
		//引入pageHelper  传入页码和每页大小
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps=employeeService.getAll();
		//PageInfo包装封装后的查询结果，将PageInfo交给页码就可以
		//传入联续显示的页码5
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		return "list";
	}
}
