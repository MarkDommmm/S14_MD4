import Database.ConnectDB;
import Model.Products;
import Service.ServiceProducts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductsServlet", value = "/ProductsServlet")
public class ProductsServlet extends HttpServlet {
    protected ServiceProducts serviceProducts;

    public void init() {
        serviceProducts = new ServiceProducts();
    }
    boolean check = false;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "GETALL":
                    check = false;
                    break;
                case "CREATE":
                    request.getRequestDispatcher("/View/newproduct.jsp").forward(request, response);
                    break;
                case "DELETE":
                    long id = Long.pasrseLong(request.getParameter("id"));
                    serviceProducts.deleteBYe(id);
                    break;
                case "EDIT":
                    showEditProduct(request, response);
                    break;
                case "SORT_NAME":
                    if (check) {
                        showProduct(serviceProducts.findAll(), request, response);
                        check = false;
                        request.setAttribute("check", check);
                    } else {
                        SortProduct(serviceProducts.findAll(), request, response);
                        check = true;
                        request.setAttribute("check", check);
                    }

                    break;
                default:
                    break;
            }
            showProduct(serviceProducts.findAll(), request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            switch (action) {
                case "ADD":
                    String name = request.getParameter("name");
                    String des = request.getParameter("description");
                    Double price = Double.parseDouble(request.getParameter("price"));
                    int stock = Integer.parseInt(request.getParameter("stock"));
                    String img = request.getParameter("img");
                    Products newproduct = new Products(0, name, des, price, stock, img);
                    try {
                        serviceProducts.save(newproduct);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    showProduct(serviceProducts.findAll(), request, response);
                    break;
                case "UPDATE":
                    String idParam = request.getParameter("id");
                    System.out.println(idParam);
                    if (idParam != null) {
                        long idupdate = Long.parseLong(idParam);
                        System.out.println(idupdate);
                        String nameupdate = request.getParameter("name");
                        String desupdate = request.getParameter("description");
                        Double priceupdate = Double.parseDouble(request.getParameter("price"));
                        int stockupdate = Integer.parseInt(request.getParameter("stock"));
                        String imgupdate = request.getParameter("img");
                        Products p = new Products((int) idupdate, nameupdate, desupdate, priceupdate, stockupdate, imgupdate);
                        try {
                            serviceProducts.save(p);
                            System.out.println(idupdate);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    showProduct(serviceProducts.findAll(), request, response);
                     break;
                default:
                     break;
            }

        }
    }

    protected void showProduct(List<Products> list, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("products", list);
        request.getRequestDispatcher("/View/List.jsp").forward(request, response);
    }

    protected void SortProduct(List<Products> list, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("products", serviceProducts.sortProducts());
        request.getRequestDispatcher("/View/List.jsp").forward(request, response);
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Products product = serviceProducts.findById(id);
        request.setAttribute("productEdit", product);
        request.getRequestDispatcher("/View/updateproduct.jsp").forward(request, response);

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}