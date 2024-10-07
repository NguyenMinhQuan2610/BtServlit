/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package my.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@MultipartConfig//Chú thích cho việc cấu hình
//fileSizeThreshold lưu trên bộ nhớ hay ko
//location vị trí lưu
//Maxfile size kích thước tối đa của 1 file
//Maxrequestfilesize kích thước tối đa của một request
public class RegisterServlit extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String hoten=request.getParameter("hoten");
        String gioitinh=request.getParameter("gioitinh");
        String[] sothich=request.getParameterValues("sothich");
        Part part=request.getPart("hinh");
        
        //Xử lý uploads file
        String realPatch=request.getServletContext().getRealPath("uploads");//Đường dẫn tuyệt đối
        String filename= Paths.get(part.getSubmittedFileName()).getFileName().toString();//Tên 
        part.write(realPatch + "/" + filename); 
        
        //b3.  hồi đáp
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Thông tin đã đăng kí</h1>");
            out.println("<hr>");
            out.println("<ul>");
            out.println("<li>Họ tên <b>" + hoten + "</b>");
            out.println("<li>Giới tính <b>" + gioitinh + "</b>");
            out.println("<li>Sở thích <b>" + Arrays.toString(sothich) + "</b>");
            out.println("<li>Ảnh đại diện <br> <img src=uploads/" + filename + ">");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
