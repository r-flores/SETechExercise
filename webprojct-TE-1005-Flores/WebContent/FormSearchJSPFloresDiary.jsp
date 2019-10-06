<%@ page language="java" import="java.sql.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diary Search</title>
<style>
h1 {
    background-color:#cc99ff;
    color:white;
    text-align:center;
    padding:5px;	 
}
h3 {
    background-color:#f2e6ff;  	      
}
section {
    width:350px;
    float:left;
    padding:10px;	 	 
}
h2 {
    background-color:#cc99ff;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;	 	 
}
</style>
</head>
<body>

	<h1 align="center"> Search Results </h1>
	<h3>
	<%!String Firstname;%>
	<%Firstname = request.getParameter("keywordFN");%>
	<%!String Lastname;%>
	<%Lastname = request.getParameter("keywordLN");%>
	<%=runMySQL()%>

	<%!String runMySQL() throws SQLException {
		System.out.println("User entered First: " + Firstname);
		System.out.println("User entered Last: " + Lastname);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://ec2-13-59-224-79.us-east-2.compute.amazonaws.com:3306/MyDBFloresTE", "rickyremote", "xenoblade7");
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return null;
		}

		PreparedStatement query = null;
		StringBuilder sb = new StringBuilder();

		try {
			connection.setAutoCommit(false);

			if (Firstname.isEmpty()) {
				String selectSQL = "SELECT * FROM DiaryTable";
				query = connection.prepareStatement(selectSQL);
			} else {
				String selectSQL = "SELECT * FROM DiaryTable WHERE Fname = '" + Firstname + "' AND Lname = '" + Lastname + "'";
				query = connection.prepareStatement(selectSQL);
			}
			ResultSet rs = query.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("entryID");
				String Fname = rs.getString("Fname").trim();
				String Lname = rs.getString("Lname").trim();
				String email = rs.getString("EmailTE").trim();
				String date = rs.getString("DiaryDate").trim();
				String entry = rs.getString("DiaryEntry").trim();

				// Display values to webpage.
				sb.append("EntryID: " + id + "<br>");
				sb.append("First Name: " + Fname + "<br>");
				sb.append("Last Name: " + Lname + "<br>");
				sb.append("Email: " + email + "<br>");
				sb.append("Date: " + date + "<br>");
				sb.append("Entry: " + entry + "<br>");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}%>
	</h3>
	<h2>
	<a href=/webprojct-TE-1005-Flores/FormSearchFloresDiary.html>Search for another entry</a> <br>
	or
	<a href=/webprojct-TE-1005-Flores/FormInsertFloresDiary.html>Insert another entry</a> <br>
	</h2>
</body>
</html>