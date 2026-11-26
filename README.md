PARKLIBRE - Sistema de Gestión de Parqueadero
Información del estudiante
Nombre del estudiante: [Escribe tu nombre completo]
Proyecto: ParkLibre
Variante: Parqueadero ParkLibre
Patrón de diseño: Factory Method
Motor de base de datos
Base de datos: SQLite
Conexión: JDBC
Librería: SQLite JDBC
Archivo de base de datos: parklibre.db
Tecnologías utilizadas
Java 21
Java Swing
Maven
SQLite
JDBC
Git y GitHub
Estructura del proyecto
modelo: clases y lógica de los vehículos.
patron: implementación de Factory Method.
persistencia: conexión y operaciones DAO.
excepciones: excepciones personalizadas.
vista: interfaz gráfica.
servicio: operaciones del negocio.
resources: archivos de recursos y script SQL.
Requisitos para ejecutar
Tener instalado Java JDK 21.
Tener instalado Apache Maven.
Tener configurada la variable de entorno JAVA_HOME.
Cómo ejecutar la aplicación
Descargar o clonar el repositorio:
bash
git clone URL_DEL_REPOSITORIO

Entrar a la carpeta del proyecto:
bash
cd ParkLibre

Compilar el proyecto:
bash
mvn clean install

Ejecutar la aplicación:
bash
mvn exec:java

Patrón de diseño

El proyecto implementa el patrón Factory Method, utilizado para crear los diferentes tipos de vehículos del parqueadero mediante fábricas especializadas.

Funcionalidades
Registrar vehículos.
Consultar vehículos.
Editar registros.
Eliminar registros.
Calcular el valor del estacionamiento.
Persistir información en SQLite.
