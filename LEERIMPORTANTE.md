
# Clínica Veterinaria "Patitas Sanas" pu

Proyecto de escritorio en Java + JavaFX para la gestión de fichas clínicas, tutores y costos estimados de atención médica.

## Integrantes
- Matías
- Gabriel

---

## 1. Primera vez: abrir el proyecto desde GitHub en IntelliJ IDEA

1. **Aceptar la invitación:** entra a tu correo o a GitHub y acepta la invitación de colaborador al repositorio.
2. **Copiar URL del repositorio:** en GitHub, haz clic en el botón verde **Code** y copia la URL HTTPS (`https://github.com/USUARIO/Patitas_sanas_PO.git`).
3. **Clonar en IntelliJ IDEA:**
   - Abre IntelliJ IDEA.
   - En la pantalla de inicio haz clic en **Get from VCS** (o en el menú superior: **Git > Clone...**).
   - Pega la URL en el campo **URL**, elige dónde guardar la carpeta en tu PC y haz clic en **Clone**.
4. **Cargar dependencias Maven:** espera unos segundos a que IntelliJ descargue automáticamente las dependencias del `pom.xml` (si aparece un ícono flotante de Maven arriba a la derecha con una flecha circular, haz clic en **Load Maven Changes**).

---

## 2. Flujo de trabajo diario (para trabajar ambos en el proyecto)

### Paso A: Antes de empezar a programar (traer cambios del compañero)
Siempre ejecuta esto antes de tocar código para descargar lo último que subió tu compañero:
```bash
git pull origin main
Paso B: Al terminar un avance (guardar y subir tus cambios)
Revisa qué archivos cambiaste:

Bash
git status
Agrega los cambios:

Bash
git add .
Crea el commit con un mensaje claro de lo que hiciste:

Bash
git commit -m "Descripción breve del cambio realizado"
Sube tus cambios a GitHub:

Bash
git push origin main
3. Reglas rápidas para evitar conflictos
Avisarse antes de editar el mismo archivo (por ejemplo, si uno edita MainController.java y el otro main_view.fxml, no habrá conflictos).

Hacer commits pequeños y frecuentes cada vez que terminen una clase, método o vista funcionando.

Nunca olvidar git pull origin main antes de hacer git push origin main.
             