package com.server.websocket;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject; 
@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
String lat=req.getParameter("lat");
String long=req.getParameter("long");
        ServletOutputStream out = resp.getOutputStream();
        try {
            String url = "jdbc:postgresql://ec2-174-129-10-235.compute-1.amazonaws.com:5432/daa2h5bidd8sco";
            Connection conn = DriverManager.getConnection(url,"zpuyymbaveuprf","2e6978c5c36ba71b91e94210d6f3f9316282d26cf2168ea759c1ff2bbf875317");
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT description,ST_AsText(geom) as geom FROM \"THIRUVARUR_RV\" where ST_Within(ST_GeomFromText('POINT("+lat+" "+long+")',4326),geom)");
            while ( rs.next() ) {
                String description = rs.getString("description");
		String geom = rs.getString("geom");
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
