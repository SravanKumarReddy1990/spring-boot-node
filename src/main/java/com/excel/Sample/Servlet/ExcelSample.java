package com.excel.Sample.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.excel.Sample.Actions.ExcelAction;
import com.excel.Sample.Model.Directory;

/**
 * Servlet implementation class ExcelSample
 */
public class ExcelSample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String modelName = "com.excel.Sample.Model.Directory";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExcelSample() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
			} else {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.getMessage();
				}
				FileItem item = (FileItem) items.get(0);
				try {
					ExcelAction ea = new ExcelAction();
					List<Directory> e = ea.readData(modelName, item
							.getInputStream());
					out.println("<table>");
					for (int i = 1; i < e.size(); i++) {
						Directory ex = (Directory) e.get(i);
						
						String date = new SimpleDateFormat("dd/MM/yyyy")
								.format(ex.getDateFormate());
						out.println("<tr><td>" + ex.getContainedFiles()
								+ " </td><td> " + ex.getDirectory()
								+ " </td><td> " + ex.getPath() + " </td><td> "
								+ ex.getName() + "</td><td> " + date
								+ "</td></tr>");
					}
					out.println("</table>");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
