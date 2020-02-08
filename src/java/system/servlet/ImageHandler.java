/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import system.bean.User;
import system.dao.UserAvatarDao;
import system.dao.UserDao;
import system.logger.Console;

/**
 *
 * @author MacMuffin
 */
@WebServlet(name = "ImageHandler", urlPatterns = {"/avatar"})
@MultipartConfig
public class ImageHandler extends HttpServlet {

	@Resource(name = "jdbc/inventory")
	private DataSource datasource;
	private Connection con;

	private static final Gson GSON = new Gson();

	public void init() throws ServletException {
		super.init();
		try {
			con = datasource.getConnection();
		} catch (SQLException ex) {
			Logger.getLogger(BrandController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {

		} catch (Exception e) {

		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(User.ATTR_ACTIVE_USER);

		response.setContentType(getServletContext().getMimeType("png"));
		if (user.getAvatar() != null) {
			byte[] avatar = user.getAvatar();
			response.setContentLength(avatar.length);
			response.getOutputStream().write(avatar);
		} else {
			try (InputStream resource = getServletContext().getResourceAsStream("/assets/img/default.png")) {
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				int nRead;
				byte[] data = new byte[5000000]; // max is 5mb

				while ((nRead = resource.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}

				byte[] avatar = buffer.toByteArray();
				response.setContentLength(avatar.length);
				response.getOutputStream().write(avatar);
			}
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json");
		Map result = new HashMap();
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(User.ATTR_ACTIVE_USER);

		Part filePart = request.getPart("image"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

		try (PrintWriter out = response.getWriter()) {

			try (InputStream fileContent = filePart.getInputStream()) {
				User userUpdated = UserDao.updateAvatar(con, user, fileContent);
				session.setAttribute(User.ATTR_ACTIVE_USER, userUpdated);

				result.put("response", "Avatar saved successfully!");
				result.put("status",1);
			} catch (SQLException e) {
				result.put("response", e.getMessage());
				result.put("status",1);
				throw new ServletException("Error at SQL/DB level.", e);
			}
			
			out.println(GSON.toJson(result));
		}

	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
