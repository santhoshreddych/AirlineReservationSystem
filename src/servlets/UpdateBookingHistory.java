package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javamodel.BankModel;
import javamodel.Bookings;
import javamodel.Flights;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateBookingHistory
 */
@WebServlet("/UpdateBookingHistory")
public class UpdateBookingHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookingHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ArrayList<Flights> ss=(ArrayList<Flights>) session.getAttribute("FlightDetailsList");
		Bookings b=new Bookings();
		String unamesession=(String) session.getAttribute("Username");
		try {
			b.updatedatabase(ss,unamesession);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
