package com.biz.shop;

public class ShopPicVO {
	private int pseq;
	private String ppath;
	private String pname;
	private String sysname;
	private int sseq;
	private String pchk_yn;
	
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getPchk_yn() {
		return pchk_yn;
	}
	public void setPchk_yn(String pchk_yn) {
		this.pchk_yn = pchk_yn;
	}
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public String getPpath() {
		return ppath;
	}
	public void setPpath(String ppath) {
		this.ppath = ppath;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getSseq() {
		return sseq;
	}
	public void setSseq(int sseq) {
		this.sseq = sseq;
	}
	
	
}
