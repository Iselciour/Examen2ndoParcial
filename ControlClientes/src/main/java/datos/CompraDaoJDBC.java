package datos;

import dominio.Compra;
import java.sql.*;
import java.util.*;

public class CompraDaoJDBC {
    private static final String SQL_SELECT = "SELECT idcompra, idcliente, monto "
            + " FROM cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT idcompra, idcliente, monto "
            + " FROM cliente WHERE idcompra = ?";

    private static final String SQL_INSERT = "INSERT INTO compras(idcliente, monto) "
            + " VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE compras "
            + " SET idcliente=?, monto=?";

    private static final String SQL_DELETE = "DELETE FROM compras WHERE idcompra = ?";

    public List<Compra> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Compra compra = null;
        List<Compra> compras = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idcompra = rs.getInt("idcompra");
                int idcliente = rs.getInt("idcliente");
                float monto = rs.getFloat("monto");

                compra = new Compra(idcompra, idcliente, monto);
                compras.add(compra);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return compras;
    }

    public Compra encontrar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, compra.getIdCompra());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            int idcliente = rs.getInt("idcliente");
            float monto = rs.getFloat("monto");

            compra.setIdCompra(idcliente);
            compra.setMonto(monto);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return compra;
    }

    public int insertar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, compra.getIdCliente());
            stmt.setFloat(2, compra.getMonto());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, compra.getIdCliente());
            stmt.setFloat(2, compra.getMonto());
            stmt.setInt(3, compra.getIdCompra());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, compra.getIdCompra());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}