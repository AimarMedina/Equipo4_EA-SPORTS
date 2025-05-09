# 📚 Índice

- [Descripción](#-gestión-de-competiciones-e-sport)
- [Colaboradores](#-colaboradores)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Capturas](#-capturas)
- [Tests](#-tests)
- [Estado del Proyecto](#-estado-del-proyecto)

---

# 🎮 Gestión de Competiciones E-Sport

Aplicación pensada para gestionar competiciones de E-Sport. Permite registrar equipos y jugadores, generar automáticamente el calendario de enfrentamientos y guardar los resultados de cada jornada.

Tiene dos tipos de usuarios:

- 🛠️ **Administradores**: gestionan equipos, jugadores, calendario y resultados.
- 👥 **Usuarios normales**: pueden consultar informes.

---

# 👨‍💻 Colaboradores

- Unax Iriondo  
- Yassir Santiago  
- Imanol Manero  
- Aimar Medina  

---

# 🧰 Tecnologías Utilizadas

![Java](https://img.shields.io/badge/Java-red?logo=coffeescript&logoColor=white)  ![SQL](https://img.shields.io/badge/Oracle_SQL-white?logo=liquibase&logoColor=black)  ![JUnit5](https://img.shields.io/badge/JUnit5-darkgreen?logo=jekyll&logoColor=white)  
![Trello](https://img.shields.io/badge/Trello-darkblue?logo=trello&logoColor=white)  ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-darkviolet?logo=intellijidea&logoColor=white)  ![ChatGPT](https://img.shields.io/badge/ChatGPT-white?logo=openai&logoColor=black)  
![Discord](https://img.shields.io/badge/Discord-blue?logo=discord&logoColor=white)  ![WhatsApp](https://img.shields.io/badge/WhatsApp-darkgreen?logo=whatsapp&logoColor=white) ![Figma](https://img.shields.io/badge/Figma-white?logo=figma&logoColor=darkblue)

Enlace para visualizar interfaz grafica hecha con <a href="https://www.figma.com/design/REcHbXQkO5UzW3m2oZdxm0/Interfaz-Grafica-EA-Sports?node-id=0-1&t=ygrGDAUtUF6U2EPF-1">Figma<a/>

---

# 🔧 Instalación

1. Clona el repositorio:
   ```bash
   git clone git@github.com:AimarMedina/Equipo4_EA-SPORTS.git
   ```

2. Abre el proyecto en IntelliJ IDEA o cualquier IDE compatible.

3. Configura la conexión a la base de datos Oracle.

4. Ejecuta la clase principal para iniciar la aplicación.

---

# ▶️ Uso

- **Inicio de sesión** según tipo de usuario:
  - Usuario administrador:
    - Contraseña: admin1
    - Nombre: admin1
  - Usuario normal:
    - Contraseña: usr1
    - Nombre: usr1
- **Administradores** pueden:
  - Registrar/editar equipos y jugadores.
  - Generar calendario de partidos.
  - Ingresar resultados.
- **Usuarios normales**:
  - Pueden consultar informes y resultados.

---

# 📸 Capturas

Esta es la pantalla principal de la aplicación, donde se muestra el logo de EA Sports y el botón para comenzar.

![Pantalla principal](../PRUEBA-MAVEN-main/imgReadme/inicioSesion.png)

En esta ventana se muestran dos opciones
  - Inicio de sesion como usuario
  - Inicio de sesion como adminstrador

![Selección de usuario](../PRUEBA-MAVEN-main/imgReadme/seleccionUsuario.png)


---

# 🧪 Tests

- Se usó **JUnit 5** para pruebas unitarias.
- Clases testeadas:
> CompeticionDAO
> EnfrentamientoDAO
> JornadaDAO
> JugadorDAO
> EquipoDAO

---

## 🚀 Estado del Proyecto

✅ Proyecto en construcción

---








