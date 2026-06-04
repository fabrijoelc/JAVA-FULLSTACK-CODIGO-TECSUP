import { useState, useEffect } from "react";
import "./Pedidos.css";

export default function Pedidos() {
  const [pedidos, setPedidos] = useState([]);

  // Cargar pedidos desde localStorage
  useEffect(() => {
    const pedidosGuardados = JSON.parse(localStorage.getItem("pedidos")) || [];
    setPedidos(pedidosGuardados);
  }, []);

  // Guardar pedidos en localStorage
  const guardarPedidos = (nuevosPedidos) => {
    setPedidos(nuevosPedidos);
    localStorage.setItem("pedidos", JSON.stringify(nuevosPedidos));
  };

  // Cambiar estado de un pedido
  const cambiarEstado = (id, nuevoEstado) => {
    const actualizados = pedidos.map((pedido) =>
      pedido.id === id ? { ...pedido, estado: nuevoEstado } : pedido
    );
    guardarPedidos(actualizados);
  };

  // Alternar visualización de productos
  const toggleProductos = (id) => {
    const actualizados = pedidos.map((pedido) =>
      pedido.id === id ? { ...pedido, mostrarProductos: !pedido.mostrarProductos } : pedido
    );
    setPedidos(actualizados);
  };

  return (
    <div className="container mt-4">
      <h2>📦 Mis Pedidos</h2>
      {pedidos.length === 0 ? (
        <p>No tienes pedidos registrados.</p>
      ) : (
        <table className="table table-striped pedidos-table">
          <thead>
            <tr>
              <th># Pedido</th>
              <th>Fecha</th>
              <th>Total</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pedidos.map((pedido) => (
              <>
                <tr key={pedido.id}>
                  <td>{pedido.id}</td>
                  <td>{pedido.fecha}</td>
                  <td>S/. {pedido.total.toLocaleString()}</td>
                  <td>
                    <select
                      value={pedido.estado}
                      onChange={(e) => cambiarEstado(pedido.id, e.target.value)}
                      className={`estado-select estado-${pedido.estado.toLowerCase().replace(" ", "-")}`}
                    >
                      <option>Pendiente</option>
                      <option>En camino</option>
                      <option>Entregado</option>
                    </select>
                  </td>
                  <td>
                    <button
                      className="btn-ver-productos"
                      onClick={() => toggleProductos(pedido.id)}
                    >
                      {pedido.mostrarProductos ? "Ocultar" : "Ver productos"}
                    </button>
                  </td>
                </tr>

                {pedido.mostrarProductos && (
                  <tr className="productos-row">
                    <td colSpan="5">
                      <div className="productos-lista">
                        {pedido.productos && pedido.productos.length > 0 ? (
                          pedido.productos.map((prod, i) => (
                            <div key={i} className="producto-item">
                              <div>
                                <strong>{prod.nombre}</strong>
                                <p>S/. {prod.precio.toLocaleString()}</p>
                              </div>
                            </div>
                          ))
                        ) : (
                          <p>No hay productos en este pedido</p>
                        )}
                      </div>
                    </td>
                  </tr>
                )}
              </>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}




