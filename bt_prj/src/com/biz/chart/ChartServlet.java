package com.biz.chart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChartServlet
 */
@WebServlet("/chart")
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub





		int[] arr= {14,51,84,52,14,86,35};
		ArrayList list =new ArrayList();
		for(int i=0;i<arr.length;i++)
		{
			list.add(arr[i]);
		}


		ArrayList<chartVO> voarr=new ArrayList<chartVO>();
		

		String[] colors= {"#574B90",
				"#28a745",
				"#ffc107",
				"#dc3545",
				"#343a40","#574B90",
				"#28a745",
				"#ffc107",
				"#dc3545",
				"#343a40","#574B90",
				"#28a745",
				"#ffc107",
				"#dc3545",
				"#343a40","#574B90",
				"#28a745",
				"#ffc107",
				"#dc3545",
				"#343a40"
		};
		for(int i=0;i<5;i++)
		{
			
			chartVO vo = new chartVO();
			vo.setLabel("가전"+i);
			vo.setIntVal(54800*(i+1));
			vo.setColors(colors[i]);
			voarr.add(vo);
		}


		request.setAttribute("KEY_DATE", list);
		request.setAttribute("KEY_DATE4", voarr);
		request.getRequestDispatcher("chart_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
