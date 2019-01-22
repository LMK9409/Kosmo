package com.kosmo.protest1;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


public class Clawler {
	public static String getASD() {
		Connection.Response response;
		String result = "";
		try {
			response = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%98%A4%EC%82%AC%EC%B9%B4")
					.method(Connection.Method.GET)
					.execute();
			Document doc;
			doc = response.parse();
			Elements el = doc.select("#main_pack > div.sp_review.section._prs_rvw > ul > li > div.review_thumb ");
			System.out.println(el.size());
			System.out.println(el.text());
			System.out.println(el.html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getASD();
	}

}
