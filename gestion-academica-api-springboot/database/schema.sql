CREATE TABLE profesor (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE asignatura (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    profesor_dni VARCHAR(20),
    asignatura_id INT,
    FOREIGN KEY (profesor_dni) REFERENCES profesor(dni),
    FOREIGN KEY (asignatura_id) REFERENCES asignatura(id)
);

CREATE TABLE estudiante (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fechaNacimiento DATE
);

CREATE TABLE matricula (
    matricula_id SERIAL PRIMARY KEY,
    estudiante_dni VARCHAR(20),
    curso_id INT,
    fechaInscripcion DATE,
    FOREIGN KEY (estudiante_dni) REFERENCES estudiante(dni),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);
