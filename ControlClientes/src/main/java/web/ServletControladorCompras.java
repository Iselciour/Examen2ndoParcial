package web;

import datos.CompraDaoJDBC;
import dominio.Compra;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorCompras")
public class ServletControladorCompras extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
// Listado de clientes sencillo
//        List<Cliente> clientes = new ClienteDaoJDBC().listar();
//        System.out.println("clientes = "+clientes);
//        request.setAttribute("clientes", clientes);
//        request.getRequestDispatcher("clientes.jsp").forward(request, response);
//    }
//}
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCompra(request, response);
                    break;
                case "eliminar":
                    this.eliminarCompra(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Compra> compras = new CompraDaoJDBC().listar();
        System.out.println("compras = " + compras);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("compras", compras);
        sesion.setAttribute("totalCompras", compras.size());
        sesion.setAttribute("duedaTotal", this.calcularSaldoTotal(compras));
        request.getRequestDispatcher("compras.jsp").forward(request, response);
        response.sendRedirect("compras.jsp");
    }

    private double calcularSaldoTotal(List<Compra> compras) {
        double deudaTotal = 0;
        for (Compra compra : compras) {
            deudaTotal += compra.getMonto();
        }
        return deudaTotal;
    }

    private void editarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCompra = Integer.parseInt(request.getParameter("idcompra"));
        Compra compra = new CompraDaoJDBC().encontrar(new Compra(idCompra));
        request.setAttribute("compra", compra);
        String jspEditar = "/WEB-INF/paginas/compras/editarCompras.jsp";
        // se crea ruta para navegar y que despecha el servlet
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCompra(request, response);
                    break;
                case "modificar":
                    this.modificarCompra(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario agregarCompra
        int idCliente = Integer.parseInt(request.getParameter("idcliente"));
        float monto = 0;
        String montoString = request.getParameter("monto");
        if (montoString != null && !"".equals(montoString)) {
            monto = Float.parseFloat(montoString);
        }

        Compra compra = new Compra(idCliente, monto);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new CompraDaoJDBC().insertar(compra);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCompra
        int idCompra = Integer.parseInt(request.getParameter("idcompra"));
        int idCliente = Integer.parseInt(request.getParameter("idcliente"));
        float monto = 0;
        String montoString = request.getParameter("monto");
        if (montoString != null && !"".equals(montoString)) {
            monto = Float.parseFloat(montoString);
        }

        Compra compra = new Compra(idCompra, idCliente, monto);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new CompraDaoJDBC().actualizar(compra);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCompra
        int idCompra = Integer.parseInt(request.getParameter("idcompra"));
     

        Compra compra = new Compra(idCompra);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new CompraDaoJDBC().eliminar(compra);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
