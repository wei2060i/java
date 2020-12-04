<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工表</title>
<%
pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<script src="${APP_PATH }/static/js/jquery-1.11.0.min.js"></script>
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<!-- -------------修改----------- -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">员工修改</h4>
      </div>
      <div class="modal-body">
           <form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-2 control-label">empName</label>
					    <div class="col-sm-10">
					      <p class="form-control-static" id="empName_update_static"></p>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label">email</label>
					    <div class="col-sm-10">
					      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email">
					    	<span class="help-block"></span>
					    </div>
					  </div>
					 <div class="form-group">
					    <label class="col-sm-2 control-label">gender</label>
					    <div class="col-sm-10">
					      		<label class="radio-inline">
								  <input type="radio" name="gender" id="gender1_update_input" value="M">男
								</label>
								<label class="radio-inline">
								  <input type="radio" name="gender" id="gender2_update_input" value="S" checked="checked">女
								</label>
					    </div>
					  </div>
					   <div class="form-group">
					    <label class="col-sm-2 control-label">deptName</label>
					    <div class="col-sm-4">
					      		<select class="form-control" name="dId">
								</select>
					    </div>
					  </div>
				</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_update_btn">update</button>
      </div>
    </div>
  </div>
</div>
<!-- ------------------添加------------- -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
      </div>
      <div class="modal-body">
           <form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-2 control-label">empName</label>
					    <div class="col-sm-10">
					      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
					      <span class="help-block"></span>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label">email</label>
					    <div class="col-sm-10">
					      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email">
					    	<span class="help-block"></span>
					    </div>
					  </div>
					 <div class="form-group">
					    <label class="col-sm-2 control-label">gender</label>
					    <div class="col-sm-10">
					      		<label class="radio-inline">
								  <input type="radio" name="gender" id="gender1_add_input" value="M">男
								</label>
								<label class="radio-inline">
								  <input type="radio" name="gender" id="gender2_add_input" value="S" checked="checked">女
								</label>
					    </div>
					  </div>
					   <div class="form-group">
					    <label class="col-sm-2 control-label">deptName</label>
					    <div class="col-sm-4">
					      		<select class="form-control" name="dId">
								</select>
					    </div>
					  </div>
				</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_save_btn">Save</button>
      </div>
    </div>
  </div>
</div>









<div class="container">
<!-- 标题 -->
<div class="row">
	<div class="col-md-12">
		<h1>SSM-CRUD</h1>
	</div>
</div>
<!--按钮-->
<div class="row">
	<div class="col-md-4 col-md-offset-8">
		<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
		<button class="btn btn-danger" id="emp_delete_all">删除</button>
	</div>
</div>
<!--显示数据-->
<div class="row">
	<div class="col-md-12">
		<table class="table table-hover" id="emps_tables">
			<thead>
			<tr>
				<th>
				<input type="checkbox" id="check_all">
				</th>
				<th>#</th>
				<th>empName</th>
				<th>gender</th>
				<th>eamil</th>
				<th>deptName</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
</div>
<!-- 分页 -->
<div class="row">
<!-- 分页文字信息 -->
<div class="col-md-6" id="page_info_area">
</div>
<div class="col-md-6" id="page_nav_area">

</div>
</div>
</div>
<script type="text/javascript">
var totalRecord=1,currentPage=1;
$(function(){
	to_page(1);
});	
	function to_page(pn){
		$.ajax({
			url:"${APP_PATH}/emps",
			data:"pn="+pn,
			type:"GET",
			success:function(result){
				//解析并显示员工数据
				build_emps_table(result);
				build_page_info(result);
				build_page_nav(result);
			}
		});
	}
	//分页条信息
	function build_page_nav(result){
		$("#page_nav_area").empty();
		var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
		if(result.extend.pageInfo.hasPreviousPage==false){
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		}else{
			firstPageLi.click(function(){
				to_page(1);
			});
			prePageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum-1);
			});
		}
		var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;"));
		var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		if(result.extend.pageInfo.hasNextPage==false){
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		}else{
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
			});
			nextPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum+1);
			});
		}
		var ul=$("<ul></ul>").addClass("pagination");
		ul.append(firstPageLi).append(prePageLi);
		$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
			var numLi=$("<li></li>").append($("<a></a>").append(item));
			if(result.extend.pageInfo.pageNum==item)
				numLi.addClass("active");
			numLi.click(function(){
				to_page(item);
			});
			ul.append(numLi);
		});
		ul.append(nextPageLi).append(lastPageLi);
		var nav=$("<nav></nav>").append(ul);
		nav.appendTo("#page_nav_area");
	}
	//分页信息
	function build_page_info(result){
		$("#page_info_area").empty();
		$("#page_info_area").append("当前页"+result.extend.pageInfo.pageNum+"页,总"+
				result.extend.pageInfo.pages+"页,总"+result.extend.pageInfo.total+"记录");
		totalRecord=result.extend.pageInfo.total;
		currentPage=result.extend.pageInfo.pageNum;
	}
	function build_emps_table(result){
		//清空
		$("#emps_tables tbody").empty();
		var emps=result.extend.pageInfo.list;
		$.each(emps,function(index,item){
			var checkBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
			var empIdTd=$("<td></td>").append(item.empId);
			var empNameTd=$("<td></td>").append(item.empName);
			var genderTd=$("<td></td>").append(item.gender=='M'?"男":"女");
			var emailTd=$("<td></td>").append(item.email);
			var deptNameTd=$("<td></td>").append(item.department.deptName);
			var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
			//编辑按钮  添加员工id
			editBtn.attr("edit-id",item.empId);
			var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-align-left")).append("删除");
			//删除用
			delBtn.attr("del-id",item.empId);
			var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(checkBoxTd)
			.append(empIdTd)
			.append(empNameTd)
			.append(genderTd)
			.append(emailTd)
			.append(deptNameTd)
			.append(btnTd).appendTo("#emps_tables tbody");
		});
	}
//重置表单 和样式
function reset_form(ele){
	//表单重置 转dom对象
	$(ele)[0].reset();
	$(ele).find("*").removeClass("has-error has-success");
	//清空span
	$(ele).find(".help-block").text("");
}	
	
$("#emp_add_modal_btn").click(function(){
	reset_form("#empAddModal form");
	getDepts("#empAddModal select");
	//弹出模态框
	$("#empAddModal").modal({
		backdrop: "static"
	});
});
//查出所有信息并显示下拉列表
function getDepts(ele){
	$(ele).empty();
	$.ajax({
		url:"${APP_PATH}/depts",
		type:"GET",
		success:function(result){	
			$.each(result.extend.depts,function(){
				var optionE=$("<option></option>").append(this.deptName).attr("value",this.deptId);
				optionE.appendTo(ele);
			});
		}
	});
}
//校验表单数据
function validate_add_form(){
	//正则校验
	var empName=$("#empName_add_input").val();
	var regName=/(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
	if(!regName.test(empName)){
		show_validate_msg("#empName_add_input","error","用户名必须是2-5位或者6-16位英文数字组合");
		return false;
	}else{
		show_validate_msg("#empName_add_input","success","");
	}
	var email=$("#email_add_input").val();
	var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if(!regEmail.test(email)){
		show_validate_msg("#email_add_input","error","邮箱格式不对");
		return false;
	}else{
		show_validate_msg("#email_add_input","success","");
	}
	return true;
}
function show_validate_msg(ele,status,msg){
	$(ele).parent().removeClass("has-success has-error");
	$(ele).next("span").text("");
	if("success"==status){
		$(ele).parent().addClass("has-success");
		$(ele).next("span").text(msg);
	}else if("error"==status){
		$(ele).parent().addClass("has-error");
		$(ele).next("span").text(msg);
	}
}
$("#emp_save_btn").click(function(){
	//先对提交给服务器的数据校验
	//绕过前端
	/*if(!validate_add_form()){
		return false;
	}*/
	//按钮判断 用户名校验是否成功
	if($(this).attr("ajax-va")=="error"){
		return false;
	}
	$.ajax({
		url:"${APP_PATH}/emp",
		type:"POST",
		data:$("#empAddModal form").serialize(),
		success:function(result){
			if(result.code==100){
				alert(result.msg);
				//关闭模态框
				$("#empAddModal").modal("hide");
				//最后一页 ，大于页码时 查最后一页
				to_page(totalRecord);
			}else{
				if(undefined!=result.extend.errorFields.email){
					show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
				}
				if(undefined!=result.extend.errorFields.empName){
					show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName);
				}
			}
		}
	});
});

$("#empName_add_input").change(function(){
	var empName=this.value;
	$.ajax({
		url:"${APP_PATH}/checkuser",
		data:"empName="+empName,
		type:"POST",
		success:function(result){
			if(result.code==100){
				show_validate_msg("#empName_add_input","success","用戶名可用");
				$("#emp_save_btn").attr("ajax-va","success");
			}else{
				show_validate_msg("#empName_add_input","error",result.extend.va_msg);
				$("#emp_save_btn").attr("ajax-va","error");
			}
		}
	});
});
/* 
 * 按钮创建之前绑定了click 所以绑定不上
 1、创建按钮的时候绑定  2、使用live  on也可以
 */
$(document).on("click",".edit_btn",function(){
	//显示员工信息
	//获取部门名称
	getDepts("#empUpdateModal select");
	//查员工信息
	getEmp($(this).attr("edit-id"));
	//员工ID传递给模态框
	$("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
	//弹出模态框
	$("#empUpdateModal").modal({
		backdrop: "static"
	});
});
function getEmp(id){
	$.ajax({
		url:"${APP_PATH}/emp/"+id,
		type:"GET",
		success:function(result){
			var empDate=result.extend.emp;
			$("#empName_update_static").text(empDate.empName);
			$("#email_update_input").val(empDate.email);
			$("#empUpdateModal input[name=gender]").val([empDate.gender]);
			$("#empUpdateModal select").val([empDate.dId]);
		}
	});
}
$("#emp_update_btn").click(function(){
	var email=$("#email_update_input").val();
	var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if(!regEmail.test(email)){
		show_validate_msg("#email_update_input","error","邮箱格式不对");
		return false;
	}else{
		show_validate_msg("#email_update_input","success","");
	}
	$.ajax({
		url:"${APP_PATH}/emp/"+$(this).attr("edit-id"),
		type:"PUT",
		data:$("#empUpdateModal form").serialize(),
		success:function(res){
			alert(res.msg);
			$("#empUpdateModal").modal("hide");
			to_page(currentPage);
		}
	});
	
});
//删除
$(document).on("click",".delete_btn",function(){
	//拿名字 
	var empName=$(this).parents("tr").find("td:eq(2)").text();
	if(confirm("确认删除"+empName+"吗")){
		$.ajax({
			url:"${APP_PATH}/emp/"+$(this).attr("del-id"),
			type:"DELETE",
			success:function(request){
				alert(request.msg);
				to_page(currentPage);
			}
		});
	}
});
$("#check_all").click(function(){
	/*attr 获取checked 是undefined
	  attr获取自定义 属性的值
	  prop修改 和读取原生属性值
	*/
	$(".check_item").prop("checked",$(this).prop("checked"));
});
$(document).on("click",".check_item",function(){
	//判断是否选择了5 个
	var flag=$(".check_item:checked").length==$(".check_item").length;
	$("#check_all").prop("checked",flag);
	
});
$("#emp_delete_all").click(function(){
	var empNames="",del_ids="";
	$.each($(".check_item:checked"),function(){
		empNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
		del_ids+=$(this).parents("tr").find("td:eq(1)").text()+"-";
		
	});
	empNames=empNames.substring(0,empNames.length-1);
	del_ids=del_ids.substring(0,del_ids.length-1);
	if(confirm("确认删除["+empNames+"]吗")){
		$.ajax({
			url:"${APP_PATH}/emp/"+del_ids,
			type:"DELETE",
			success:function(request){
				alert(request.msg);
				to_page(currentPage);
			}
		});
	}
});



</script>
</body>
</html>