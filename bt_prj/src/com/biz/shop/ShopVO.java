package com.biz.shop;

import java.util.ArrayList;

public class ShopVO {
	private int sseq;
	private String sname;
	private String placename;
	private String sinfo;
	private Double lat;
	private Double lng;
	private String regid;
	private String regdate;
	private int pseq;
	private String ppath;
	private String pname;
	private double distance;
	int topN;
	private ArrayList<ShopPicVO> plist;
	private ArrayList<ReplyVO> rlist;
	int startSeq;
	int endSeq;
	
	
	public int getStartSeq() {
		return startSeq;
	}
	public void setStartSeq(int startSeq) {
		this.startSeq = startSeq;
	}
	public int getEndSeq() {
		return endSeq;
	}
	public void setEndSeq(int endSeq) {
		this.endSeq = endSeq;
	}
	public ArrayList<ReplyVO> getRlist() {
		return rlist;
	}
	public void setRlist(ArrayList<ReplyVO> rlist) {
		this.rlist = rlist;
	}
	public int getTopN() {
		return topN;
	}
	public void setTopN(int topN) {
		this.topN = topN;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public ArrayList<ShopPicVO> getPlist() {
		return plist;
	}
	public void setPlist(ArrayList<ShopPicVO> plist) {
		this.plist = plist;
	}
	public ArrayList<ShopPicVO> getPvo() {
		return plist;
	}
	public void setPvo(ArrayList<ShopPicVO> plist) {
		this.plist = plist;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getSseq() {
		return sseq;
	}
	public void setSseq(int sseq) {
		this.sseq = sseq;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSinfo() {
		return sinfo;
	}
	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
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


}
