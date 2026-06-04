import imageStore from "../assets/img/imageStore.jpg";
import logoStars from "../assets/img/logoStars.png";
import "./home.css";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="home-container text-center">
      {/* Logo e introducción */}
      <img src={imageStore} alt="Electron Store" className="store-logo mx-auto" />
      <h1 className="mt-3 font-bold text-3xl">Bienvenido a Electron Store</h1>
      <p className="text-lg text-gray-600">Compra productos de tecnología al mejor precio.</p>
      <Link to="/catalogo" className="btn btn-primary btn-lg mt-3">
        Ver Catálogo
      </Link>

{/* Sección especialidad */}
<div className="mt-10 mb-10 p-5 bg-light rounded shadow-sm max-w-2xl mx-auto">
  <h2 className="text-2xl font-semibold mb-3">Nuestra Especialidad</h2>
  <p className="text-gray-700 mb-4">
    Ofrecemos lo mejor en tecnología, con atención personalizada y garantía de satisfacción.
  </p>

  {/* Contenedor de logo y estrellas */}
  <div className="rating-container">
    <img
      src={logoStars}
      alt="Sello de calidad"
      className="rating-icon"
    />
    <div className="stars">★★★★★</div>
  </div>

  {/* Reseñas */}
  <p className="text-muted mt-2">
    Valoración promedio de 5.0 basada en más de 200 reseñas
  </p>
</div>


      {/* Sección de Contáctenos */}
      <div className="contact-section mt-5">
        <h2 className="mb-4">Contáctenos</h2>
        <div className="row justify-content-center">
          {/* Formulario */}
          <div className="col-md-5">
            <form>
              <input
                type="text"
                className="form-control mb-3"
                placeholder="Nombre completo"
              />
              <input
                type="email"
                className="form-control mb-3"
                placeholder="Correo electrónico"
              />
              <input
                type="text"
                className="form-control mb-3"
                placeholder="Asunto"
              />
              <textarea
                className="form-control mb-3"
                rows="4"
                placeholder="Mensaje o comentario"
              ></textarea>
              <button type="submit" className="btn btn-primary btn-lg w-100">
                Enviar mensaje
              </button>
            </form>
          </div>

          {/* Mapa */}
          <div className="col-md-5">
            <iframe
              title="Mapa ubicación"
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3900.7951047601!2d-77.0427938856176!3d-12.04038069146532!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9105cf2b3bbedb2b%3A0x76a9bca90e6c7b8e!2sRimac!5e0!3m2!1ses-419!2spe!4v1679427223451!5m2!1ses-419!2spe"
              width="100%"
              height="350"
              style={{ border: 0, borderRadius: "10px" }}
              allowFullScreen=""
              loading="lazy"
            ></iframe>
          </div>
        </div>
      </div>
    </div>
  );
}



