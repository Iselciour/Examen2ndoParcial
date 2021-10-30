package dominio;

public class Compra {
    
    private int idcliente;
    private int idcompra;
    private float monto;

    public Compra() {
    }

    public Compra(int idcompra) {
        this.idcompra = idcompra;
    }

    public Compra(int idcliente, float monto) {
        this.idcliente = idcliente;
        this.monto = monto;
    }

    public Compra(int idcompra, int idcliente, float monto) {
        this.idcompra = idcompra;
        this.idcliente = idcliente;
        this.monto = monto;
    }

    public int getIdCompra() {
        return idcompra;
    }

    public void setIdCompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIdCliente() {
        return idcliente;
    }

    public void setIdCliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    @Override
    public String toString() {
        return "Compra{" + "idCompra = " + idcompra + ", idCliente = " + idcliente + ", monto = " + monto + '}';
    }
    
}
