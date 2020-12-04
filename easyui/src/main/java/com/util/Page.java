package com.util;

import java.util.List;

public class Page<T> {
	private Integer pageno;
	private Integer pagesize;
	private List<T> rows;
	private Integer total;
	private Integer totalno;
	public Page(Integer pageno, Integer pagesize) {
		if(pageno <=0) {
			this.pageno=1;
		}else {
			this.pageno = pageno;
		}
		if(pagesize <=0) {
			this.pagesize=1;
		}else {
			this.pagesize = pagesize;
		}
	}
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<T> getRows() {
		return rows;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
		this.totalno=(total%pagesize)==0?(total/pagesize):(total/pagesize+1);
	}
	public Integer getTotalno() {
		return totalno;
	}
	private void setTotalno(Integer totalno) {
		this.totalno = totalno;
	}
	//获取开始索引
	public Integer getStartIndex() {
		return (this.pageno-1)*pagesize;
	}
	
}
