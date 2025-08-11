# Microservicio de Gestión de Franquicias y Productos

Este microservicio implementado con **Spring Boot** permite gestionar franquicias, sucursales y productos, ofreciendo operaciones para creación, eliminación, actualización y consulta de stock.

## 🚀 Características principales
- Crear una nueva franquicia.
- Crear una nueva sucursal asociada a una franquicia existente.
- Crear un producto en una sucursal.
- Eliminar un producto de una sucursal.
- Modificar el stock de un producto.
- Consultar el producto con mayor stock por sucursal en una franquicia dada.

## 📦 Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Data JPA**
- **Maven**
- **Docker**

## ⚙️ Instalación y ejecución

### Requisitos previos
- [Java 17](https://adoptium.net/)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

### Clonar el repositorio
```bash
git clone https://github.com/usuario/repositorio.git
cd repositorio
```

### Levantar la aplicación

Este proyecto incluye un archivo docker-compose.yml que levanta:

- El microservicio de gestión de franquicias y productos.
- Una base de datos MySQL.

Pasos:

1. Generar el JAR
Desde la carpeta del proyecto (sucursales-inventario):

```bash
mvn clean install -DskipTests
```

2. Levantar el entorno con Docker Compose
Desde la carpeta donde se encuentra el archivo docker-compose.yml

```bash
docker-compose up --build -d
```

El microservicio estará disponible en: http://localhost:8081/api

### Endpoints disponibles

| Método     | Endpoint                                           | Descripción                                                          |
| ---------- | -------------------------------------------------- | -------------------------------------------------------------------- |
| **POST**   | `/franquicia/create`                               | Crear una nueva  franquicia                                           |
| **POST**   | `/sucursal/create?franquiciaId={id}`           | Crear una sucursal asociada a una franquicia                         |
| **POST**   | `/producto/create?sucursalId={id}`               | Crear un producto en una sucursal                                    |
| **DELETE** | `/producto/delete?sucursalId={id}&productId={id2}`  | Eliminar un producto de una sucursal                                 |
| **PUT**    | `/producto/update`                    | Modificar el stock de un producto                                    |
| **GET**    | `/producto/stock?franquiciaId={id}` | Consultar el producto con mayor stock por sucursal en una franquicia |


### Ejemplos de uso con cURL

- Crear una franquicia

curl --location --request POST 'http://localhost:8081/api/franquicia/create?nombre={NombreFranquicia}'

- Crear una sucursal

curl --location 'http://localhost:8081/api/sucursal/create?franquiciaId={Id}' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "NombreSucursal"
}'

- Crear un producto

curl --location 'http://localhost:8081/api/producto/create?franquiciaId={Id}' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "NombreProducto",
    "stock" 4
}'

- Eliminar un producto

curl --location --request DELETE 'http://localhost:8081/api/producto/delete?sucursalId={Id}&productId={Id2}'

- Modificar el Stock de un producto

curl --location --request PUT 'http://localhost:8081/api/producto/update' \
--header 'Content-Type: application/json' \
--data '{
    "id": 3,
    "stock":25
}'

- Consultar el producto con mayor stock por sucursal en una franquicia

curl --location 'http://localhost:8081/api/producto/stock?franquiciaId={Id}'