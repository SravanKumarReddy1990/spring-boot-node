package com.excel.Sample.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.excel.Sample.Actions.ExcelAction;
import com.excel.Sample.Model.Directory;
import com.excel.Sample.Model.FormModel;
import com.mfcw.Sample.Actions.FormAction;

/**
 * Servlet implementation class FormSample
 */
public class FormSample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String modelName = "com.excel.Sample.Model.FormModel";
	public static String excelmodelName = "com.excel.Sample.Model.Directory";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormSample() {
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

				FormAction fa = new FormAction();
				FormModel ex = fa.readData(modelName, null, request);
				out.println("<table>");
			
			
					out.println("<tr><td>" + ex.getAdress() + " </td><td> "
							+ ex.getFname() + " </td><td> " + ex.getGender()
							+ " </td><td> " + ex.getSname() + "</td><td>"
							+ ex.getPhoneno() + " </td></tr>");
				
				out.println("</table>");

			} else {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.getMessage();
				}

				FormAction fa = new FormAction();
				FormModel ex = fa.readData(modelName, items, null);
				out.println("<table border='1'>");
				
					
					ExcelAction ea = new ExcelAction();
					List<Directory> e1 = ea.readData(excelmodelName, ex
							.getFile());
					Directory d = (Directory) e1.get(2);
					out.println("<tr> <td>" + d.getPath() + " </td>  <td>"
							+ ex.getAdress() + " </td><td> " + ex.getFname()
							+ " </td><td> " + ex.getGender() + " </td><td> "
							+ ex.getSname() + "</td><td>" + ex.getPhoneno()
							+ " </td></tr>");

				
				out.println("</table>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			out.println(e);
		}

	}

}
