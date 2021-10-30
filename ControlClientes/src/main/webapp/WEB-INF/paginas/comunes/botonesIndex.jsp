<html>
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <section id="actions" class="py-4 mb-4 bg-light">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-3">
                                <a href="clientes.jsp" class="btn btn-secondary btn-lg btn-block">Clientes</a>
                            </div>
                        </div>
                    </div>
                </section>
                <section id="actions" class="py-4 mb-4 bg-light">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-3">
                                <a href="compras.jsp" class="btn btn-secondary btn-lg btn-block">Compras</a>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="col-md-3">
                <div class="card text-center bg-dark text-white mb-3">
                    <div class="card-body">
                        <h3>Deuda Total</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${totalClientes}" type="currency" />
                        </h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Deuda Mayor</h3>
                        <h4 class="display-4">
                            ${totalClientes}
                        </h4>
                    </div>
                </div>
                        <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Deuda Menor</h3>
                        <h4 class="display-4">
                             ${totalClientes}
                        </h4>
                    </div>
                </div>  
            </div>
        </div>
    </div>  
</html>
