import React from "react";
import "./ProductCard.css";

export default function ProductCard({ producto, onAddToCart }) {
  const imageUrl = new URL(
    `../../assets/img/${producto.imagen}`,
    import.meta.url
  ).href;

  return (
    <div className="product-card shadow-sm">
      <div className="product-image-container">
        <img src={imageUrl} alt={producto.nombre} className="product-image" />
      </div>

      <div className="product-info">
        <h5 className="product-title">{producto.nombre}</h5>
        <p className="product-price">
          S/. {producto.precio.toLocaleString()}
        </p>

        <button
          className="add-to-cart-btn"
          onClick={() => onAddToCart(producto)}
        >
          Agregar al carrito
        </button>
      </div>
    </div>
  );
}


