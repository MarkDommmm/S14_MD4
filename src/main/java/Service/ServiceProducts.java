package Service;

import Database.ConnectDB;
import Model.Products;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServiceProducts implements IServiceProducts<Products, Long> {

    private List<Products> productsList;

    public ServiceProducts() {
        productsList = new ArrayList<>();
    }

    @Override
    public List findAll() {
        List<Products> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = Database.ConnectDB.getConnection();
            call = conn.prepareCall("{call show_product}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setImg(rs.getString("img"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Products products) throws SQLException {
        Connection conn = ConnectDB.getConnection();
        try {
            if (products.getId() == 0) {
                CallableStatement callst = conn.prepareCall("{Call add_product(?,?,?,?,?) }");
                callst.setString(1, products.getName());
                callst.setString(2, products.getDescription());
                callst.setDouble(3, products.getPrice());
                callst.setInt(4, products.getStock());
                callst.setString(5, products.getImg());
                callst.executeUpdate();
            } else {
                CallableStatement callst = conn.prepareCall("{Call update_products(?,?,?,?,?,?)}");
                callst.setInt(1, (int) products.getId());
                callst.setString(2, products.getName());
                callst.setString(3, products.getDescription());
                callst.setDouble(4, products.getPrice());
                callst.setInt(5, products.getStock());
                callst.setString(6, products.getImg());

                callst.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Products findById(Long aLong) {
        Connection conn = ConnectDB.getConnection();
        Products p = null;
        try {
            CallableStatement callst = conn.prepareCall("{Call find_product_by_id(?)}");
            callst.setLong(1, aLong);
            ResultSet rs = callst.executeQuery();
            while (rs.next()) {
                p = new Products();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setImg(rs.getString("img"));
                p.setStatus(rs.getBoolean("status"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return p;
    }

    @Override
    public void deleteBYe(Long aLong) {
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement callst = conn.prepareCall("{Call delete_product(?)}");
            callst.setLong(1, aLong);
            callst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public List<Products> sortProducts() {
        List<Products> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = Database.ConnectDB.getConnection();
            call = conn.prepareCall("{call sort_products}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setImg(rs.getString("img"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.ConnectDB.closeConnection(conn);
        }
        return list;
    }


}
