package com.biz.common;

import java.util.*;


import com.biz.shop.ShopVO;

public interface JDBC {
	public abstract ArrayList<ShopVO> select(int seq);
	public abstract ShopVO select();
	public abstract int insert(ShopVO svo);
	public abstract int update(ShopVO svo);
	public abstract int delete(int seq);
}
