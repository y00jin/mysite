package com.douzone.mysite.vo;

public class PageVo {

	// 한 페이지에 보열줄 행
	private int displayRow = 5;
	// 시작하는 행 번호
	private int startRow;
	// 끝나는 행 번호
	private int endRow;

	// 전체 페이지
	private int totalPage = 0;
	// 전체 게시글 갯수
	private int totalRow = 0;
	// 현재 페이지
	private int thisPage = 1;
	// 시작하는 페이지
	private int startPage;
	// 끝나는 페이지
	private int endPage;
	
	public int getDisplayRow() {
		return displayRow;
	}

	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getThisPage() {
		return thisPage;
	}

	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public void page() {
		totalPage = totalRow / displayRow;

		if (totalRow % displayRow > 0)
			totalPage++;
		if(totalRow == 0) 
			totalPage++;

		if (totalPage < thisPage)
			thisPage = totalPage;

		setStartPage((getThisPage() - 1) * getDisplayRow() + 1);
		setEndPage(getStartPage() + getTotalPage() - 1);
	}

}
