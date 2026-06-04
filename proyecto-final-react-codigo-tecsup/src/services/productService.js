export async function getProductos() {
  try {
    const response = await fetch("/src/assets/productos.json");
    if (!response.ok) {
      throw new Error("Error al cargar los productos");
    }
    return await response.json();
  } catch (error) {
    console.error("Error en getProductos:", error);
    return [];
  }
}
