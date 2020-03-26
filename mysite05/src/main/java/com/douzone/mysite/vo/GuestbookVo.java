package com.douzone.mysite.vo;

public class GuestbookVo {
//	`no`       INT UNSIGNED NOT NULL COMMENT '번호',
//	`name`     VARCHAR(50)  NOT NULL COMMENT '이름',
//	`contents` TEXT         NOT NULL COMMENT '내용',
//	`password` VARCHAR(64)  NOT NULL COMMENT '비밀번호',
//	`reg_date` DATETIME     NOT NULL COMMENT '등록일'
	
	private int no;
	private String name;
	private String contents;
	private String password;
	private String regTime;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", contents=" + contents + ", password=" + password + ", regTime="
				+ regTime + "]";
	}
	
}
