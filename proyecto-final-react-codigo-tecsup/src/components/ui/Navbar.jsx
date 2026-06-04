import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import logo from "../../assets/img/logo.jpg";
import "./Navbar.css";

export default function Navbar() {
  const [cartCount, setCartCount] = useState(0);

  const actualizarContador = () => {
    const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
    setCartCount(carrito.length);
  };

  useEffect(() => {
    actualizarContador();

    const handleCarritoUpdated = () => {
      actualizarContador();
    };

    window.addEventListener("carritoUpdated", handleCarritoUpdated);

    return () => {
      window.removeEventListener("carritoUpdated", handleCarritoUpdated);
    };
  }, []);

  return (
    <nav className="navbar navbar-expand-lg">
      <div className="container">
        {/* Logo */}
        <Link className="navbar-brand" to="/">
          <img src={logo} alt="Electron Store" width="120" />
        </Link>

        {/* Links */}
        <ul className="navbar-nav ms-auto align-items-center gap-3">
          <li className="nav-item">
            <Link className="nav-link" to="/catalogo">
              <i className="bi bi-grid me-1"></i> Catálogo
            </Link>
          </li>

          <li className="nav-item">
            <Link className="nav-link" to="/carrito">
              <i className="bi bi-cart-fill me-1"></i> Carrito
              <span className="badge">{cartCount}</span>
            </Link>
          </li>

          {/* NUEVO: Mis Pedidos */}
          <li className="nav-item">
            <Link className="nav-link" to="/pedidos">
              <i className="bi bi-box-seam me-1"></i> Mis Pedidos
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}


