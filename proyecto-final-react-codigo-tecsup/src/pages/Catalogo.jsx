import { useEffect, useState } from "react";
import ProductCard from "../components/ui/ProductCard";
import Loader from "../components/ui/Loader";
import useLocalStorage from "../hooks/useLocalStorage";
import productosData from "../assets/productos.json";
import "./catalogo.css";

export default function Catalogo() {
  const [productos, setProductos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [carrito, setCarrito] = useLocalStorage("carrito", []);

  // 🔍 Estados para búsqueda y filtro
  const [search, setSearch] = useState("");
  const [precioFiltro, setPrecioFiltro] = useState("todos");

  useEffect(() => {
    setTimeout(() => {
      setProductos(productosData);
      setLoading(false);
    }, 500);
  }, []);

const agregarAlCarrito = (producto) => {
  // Crear un UID único para diferenciar cada unidad
  const productoConUid = { ...producto, uid: Date.now() + Math.random() };

  const nuevoCarrito = [...carrito, productoConUid];
  setCarrito(nuevoCarrito);
  localStorage.setItem("carrito", JSON.stringify(nuevoCarrito));

  // Notificar al Navbar que cambió el carrito
  window.dispatchEvent(new Event("carritoUpdated"));
};

  // 🔍 Filtrar productos por búsqueda y precio
  const productosFiltrados = productos.filter((p) => {
    const coincideBusqueda = p.nombre.toLowerCase().includes(search.toLowerCase());

    let coincidePrecio = true;
    if (precioFiltro === "bajo") coincidePrecio = p.precio < 1500;
    if (precioFiltro === "medio") coincidePrecio = p.precio >= 1500 && p.precio <= 3000;
    if (precioFiltro === "alto") coincidePrecio = p.precio > 3000;

    return coincideBusqueda && coincidePrecio;
  });

  if (loading) return <Loader />;

  return (
    <div>
      {/* Barra de búsqueda y filtro */}
      <div className="mb-4 d-flex flex-wrap gap-3 align-items-center">
        <input
          type="text"
          className="form-control"
          placeholder="Buscar producto..."
          style={{ maxWidth: "300px" }}
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

        <select
          className="form-select"
          style={{ maxWidth: "200px" }}
          value={precioFiltro}
          onChange={(e) => setPrecioFiltro(e.target.value)}
        >
          <option value="todos">Todos los precios</option>
          <option value="bajo">Menos de S/. 1500</option>
          <option value="medio">S/. 1500 - S/. 3000</option>
          <option value="alto">Más de S/. 3000</option>
        </select>
      </div>

      {/* Lista de productos */}
      <div className="row">
        {productosFiltrados.length > 0 ? (
          productosFiltrados.map((p, index) => (
            <div className="col-md-4 mb-3" key={index}>
              <ProductCard producto={p} onAddToCart={() => agregarAlCarrito(p)} />
            </div>
          ))
        ) : (
          <p>No se encontraron productos.</p>
        )}
      </div>
    </div>
  );
}

