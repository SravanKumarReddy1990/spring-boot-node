package com.server.websocket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String lat=req.getParameter("lat");
		String lon=req.getParameter("long");
        ServletOutputStream out = resp.getOutputStream();
        try {
            String url = "jdbc:postgresql://ec2-174-129-10-235.compute-1.amazonaws.com:5432/daa2h5bidd8sco";
            Connection conn = 							DriverManager.getConnection(url,"zpuyymbaveuprf","2e6978c5c36ba71b91e94210d6f3f9316282d26cf2168ea759c1ff2bbf875317");
            Statement stmt = conn.createStatement();
Statement stmt1 = conn.createStatement();
Statement stmt2 = conn.createStatement();
//Statement stmt3 = conn.createStatement();
Statement stmt4 = conn.createStatement();
Statement stmt5 = conn.createStatement();
Statement stmt6 = conn.createStatement();
out.println("before queries executed");
            ResultSet rs= stmt.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"THIRUVARUR_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+lon+")',4326),geom)");out.println("1queries executed");
  ResultSet rs1= stmt1.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"VILLPURAM_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+lon+")',4326),geom)");out.println("2queries executed");
  ResultSet rs2= stmt2.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"TIRUVELLORE_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+lon+")',4326),geom)");out.println("3queries executed");
 // ResultSet rs3= stmt3.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"THIRUVANNAMALAI_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+lon+")',4326),geom)");out.println("4queries executed");
  ResultSet rs4= stmt4.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"Tirunelveli_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+lon+")',4326),geom)");out.println("5queries executed");
  ResultSet rs5= stmt5.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"TUTICORIN_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+lon+")',4326),geom)");out.println("6queries executed");
  ResultSet rs6= stmt6.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"VELLORE_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+lon+")',4326),geom)");
out.println("queries executed");
            while ( rs.next() ) {
                String description = rs.getString("description");
		String geom = rs.getString("geom");
                JSONObject obj=new JSONObject();    
  		obj.put("description",description);    
  		obj.put("geom",geom);       
   		out.print(obj.toString());  
            }
	while ( rs1.next() ) {
                String description = rs1.getString("description");
		String geom = rs1.getString("geom");
                JSONObject obj=new JSONObject();    
  		obj.put("description",description);    
  		obj.put("geom",geom);       
   		out.print(obj.toString());  
            }
	while ( rs2.next() ) {
                String description = rs2.getString("description");
		String geom = rs2.getString("geom");
                JSONObject obj=new JSONObject();    
  		obj.put("description",description);    
  		obj.put("geom",geom);       
   		out.print(obj.toString());  
            }
	//while ( rs3.next() ) {
        //        String description = rs3.getString("description");
	//	String geom = rs3.getString("geom");
        //        JSONObject obj=new JSONObject();
  	//	obj.put("description",description);
  	//	obj.put("geom",geom);
   	//	out.print(obj.toString());
           // }
	while ( rs4.next() ) {
                String description = rs4.getString("description");
		String geom = rs4.getString("geom");
                JSONObject obj=new JSONObject();
  		obj.put("description",description);
  		obj.put("geom",geom);
   		out.print(obj.toString());
            }
	while ( rs5.next() ) {
                String description = rs5.getString("description");
		String geom = rs5.getString("geom");
                JSONObject obj=new JSONObject();
  		obj.put("description",description);
  		obj.put("geom",geom);
   		out.print(obj.toString());
            }
	while ( rs6.next() ) {
                String description = rs6.getString("description");
		String geom = rs6.getString("geom");
                JSONObject obj=new JSONObject();
  		obj.put("description",description);
  		obj.put("geom",geom);
   		out.print(obj.toString());
            }
            conn.close();
        } catch (Exception e) {
            out.println("Got an exception! ");
            out.println(e.getMessage());
        }
    }

}
