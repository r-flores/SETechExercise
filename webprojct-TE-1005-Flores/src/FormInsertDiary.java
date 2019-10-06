import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormInsertDiary")
public class FormInsertDiary extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public FormInsertDiary() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String FName = request.getParameter("FirstName");
      String LName = request.getParameter("LastName");
      String email = request.getParameter("Email");
      String Date = request.getParameter("Date");
      String Entry = request.getParameter("Entry");

      Connection connection = null;
      String insertSql = " INSERT INTO DiaryTable (Fname, Lname, EmailTE, DiaryDate, DiaryEntry) values (?, ?, ?, ?, ?)";

      try {
         DBConnectionFloresDiary.getDBConnection();
         connection = DBConnectionFloresDiary.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, FName);
         preparedStmt.setString(2, LName);
         preparedStmt.setString(3, email);
         preparedStmt.setString(4, Date);
         preparedStmt.setString(5, Entry);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "New Diary Entry Added!";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f2e6ff\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Name: </b> " + FName + " " + LName + "\n" + //
            "  <li><b>Email: </b> " + email + "\n" + //
            "  <li><b>Date: </b> " + Date + "\n" + //
            "  <li><b>Body: </b>" + Entry + "\n" + //

            "</ul>\n");

      out.println("<a href=/webprojct-TE-1005-Flores/FormSearchFloresDiary.html>Search Diary</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
