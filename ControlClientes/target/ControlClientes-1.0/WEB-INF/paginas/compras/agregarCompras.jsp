<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title">Add Compra</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="POST" class="was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">IdCliente</label>
                        <input type="text" class="form-control" name="idcliente" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Monto</label>
                        <input type="text" class="form-control" name="monto" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
    