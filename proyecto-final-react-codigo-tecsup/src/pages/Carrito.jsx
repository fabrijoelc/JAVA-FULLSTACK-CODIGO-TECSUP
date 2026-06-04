import useLocalStorage from "../hooks/useLocalStorage";
import './Carrito.css';
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

export default function Carrito() {
  const navigate = useNavigate();
  const [carrito, setCarrito] = useLocalStorage("carrito", []);

  const eliminarProducto = (uid) => {
    const nuevoCarrito = carrito.filter((p) => p.uid !== uid);
    setCarrito(nuevoCarrito);
    localStorage.setItem("carrito", JSON.stringify(nuevoCarrito));
    window.dispatchEvent(new Event("carritoUpdated"));
  };

  const vaciarCarrito = () => {
    setCarrito([]);
    localStorage.removeItem("carrito");
    window.dispatchEvent(new Event("carritoUpdated"));
  };

  const confirmarCompra = () => {
    if (carrito.length === 0) {
      Swal.fire({
        icon: "warning",
        title: "Carrito vacío",
        text: "Agrega productos antes de confirmar.",
        confirmButtonColor: "#007bff"
      });
      return;
    }

    // Obtener pedidos anteriores
    const pedidosPrevios = JSON.parse(localStorage.getItem("pedidos")) || [];

    // Crear nuevo pedido
    const nuevoPedido = {
      id: pedidosPrevios.length + 1,
      fecha: new Date().toLocaleString(),
      total: carrito.reduce((sum, prod) => sum + prod.precio, 0),
      estado: "Pendiente",
      productos: carrito
    };

    // Guardar en localStorage
    localStorage.setItem("pedidos", JSON.stringify([...pedidosPrevios, nuevoPedido]));

    // Vaciar carrito
    vaciarCarrito();

    // SweetAlert con redirección
    Swal.fire({
      icon: "success",
      title: "✅ Pedido registrado con éxito",
      text: "Serás redirigido a 'Mis Pedidos'...",
      showConfirmButton: false,
      timer: 2000,
      timerProgressBar: true
    }).then(() => {
      navigate("/pedidos");
    });
  };

  const total = carrito.reduce((acc, p) => acc + p.precio, 0);

  return (
    <div className="carrito-container">
      <h2 className="carrito-titulo">🛒 Carrito de Compras</h2>

      {carrito.length === 0 ? (
        <div className="carrito-vacio">
          <p>Tu carrito está vacío</p>
          <span className="emoji">🛍️</span>
        </div>
      ) : (
        <>
          <div className="carrito-lista">
            {carrito.map((p) => (
              <div key={p.uid} className="carrito-item">
                <div className="carrito-info">
                  <strong>{p.nombre}</strong>
                  <span className="precio">S/. {p.precio.toLocaleString()}</span>
                </div>
                <button
                  onClick={() => eliminarProducto(p.uid)}
                  className="btn-eliminar"
                >
                  ❌ Eliminar
                </button>
              </div>
            ))}
          </div>

          <div className="carrito-total">
            <h4>
              Total: <span className="precio-total">S/. {total.toLocaleString()}</span>
            </h4>
            <div className="carrito-botones">
              <button onClick={vaciarCarrito} className="btn-vaciar">
                Vaciar Carrito
              </button>
              <button onClick={confirmarCompra} className="btn-confirmar">
                Confirmar Compra
              </button>
            </div>
          </div>
        </>
      )}
    </div>
  );
}




