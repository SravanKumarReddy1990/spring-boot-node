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
import com.excel.Sample.Actions.ExcelActionXssf;
import com.excel.Sample.Model.Directory;

import java.net.*;
import java.io.*;

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
					ExcelActionXssf ea = new ExcelActionXssf();
					List<Directory> e = ea.readData(modelName, item
							.getInputStream());
					String data=readJsonFromUrl("http://sravankumar1990.herokuapp.com/displayUrl.php");
					out.println(data);
					out.println("<table border='1'>");
					for (int i = 1; i < e.size(); i++) {
						Directory ex = (Directory) e.get(i);
						
						//String date = new SimpleDateFormat("dd/MM/yyyy")
							//	.format(ex.getDateFormate());
						out.println("<tr><td>" + ex.getContainedFiles()
								+ " </td><td> " + ex.getDirectory()
								+ " </td><td> " + ex.getPath() + " </td><td> "
								+ ex.getName() + "</td><td> " + 
								 "</td></tr>");
					}
					out.println("</table>");
				} catch (Exception e) {
					System.out.println("Excel Sample : "+e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public static String readJsonFromUrl(String urls) throws IOException, JSONException {
    StringBuilder content = new StringBuilder();
    try
    {
      URL url = new URL(theUrl);
      URLConnection urlConnection = url.openConnection();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      String line;
      while ((line = bufferedReader.readLine()) != null)
      {
        content.append(line + "\n");
      }
      bufferedReader.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return content.toString();
	}
}
