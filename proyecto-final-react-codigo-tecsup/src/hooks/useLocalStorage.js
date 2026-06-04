import { useState, useEffect } from "react";

export default function useLocalStorage(key, initialValue) {
  // Estado inicial: lee desde localStorage o usa el valor inicial
  const [storedValue, setStoredValue] = useState(() => {
    try {
      const item = localStorage.getItem(key);
      // Si existe en localStorage, lo parsea, sino usa el valor inicial
      return item ? JSON.parse(item) : initialValue;
    } catch (error) {
      console.error("Error al leer localStorage:", error);
      return initialValue;
    }
  });

  // Cada vez que cambie el estado, lo guarda en localStorage
  useEffect(() => {
    try {
      localStorage.setItem(key, JSON.stringify(storedValue));
    } catch (error) {
      console.error("Error al guardar en localStorage:", error);
    }
  }, [key, storedValue]);

  return [storedValue, setStoredValue];
}
