<%@page language="java" import ="java.sql.*" %>  
 <%  
 String name=request.getParameter("count");  
 String buffer="<div>";  
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();  
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "schinta2","qwe123"); 
 Statement stmt = con.createStatement();  
 ResultSet rs = stmt.executeQuery("Select Source from Flights where Source LIKE '"+name+"%'");  
   while(rs.next()){
   buffer=buffer+rs.getString(1)+"<br>";  
   }  
 buffer=buffer+"</div>";  
 response.getWriter().println(buffer);  
 %>