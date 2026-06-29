# Development Environment Setup

Esta guía explica cómo configurar el entorno de desarrollo de Hypatia para compilar y ejecutar el proyecto correctamente.

## Tabla de contenido:

| Sección | Subsecciones |
|---------|--------------|
| [Development Environment Setup](#development-environment-setup) | <br>• [Prerrequisitos](#prerrequisitos) |
| [Formas de importar el proyecto](#formas-de-importar-el-proyecto) | <br>• [Método 1: Clonar el repositorio e importarlo localmente](#método-1-clonar-el-repositorio-e-importarlo-localmente)<br>&nbsp;&nbsp;&nbsp;└ [Clonarlo](#para-clonarlo)<br>&nbsp;&nbsp;&nbsp;└ [Importar el proyecto a Eclipse](#importar-el-proyecto-en-eclipse)<br><br>• [Método 2: Clonar el proyecto desde Eclipse (Colaboradores)](#método-2-clonar-el-proyecto-desde-eclipse-colaboradores)<br>&nbsp;&nbsp;&nbsp;└ [Importarlo desde Eclipse](#importarlo-desde-eclipse) |
| [Configuración de JavaFX](#configuración-de-javafx) | <br>• [Configuracion de los VM Arguments](#configuracion-de-los-vm-arguments) |
| [Configurar Scene Builder (Opcional)](#configurar-scene-builder-opcional) |  |
| [Ejecutar la aplicación](#ejecutar-la-aplicación) |  |
| [Problemas comunes](#problemas-comunes) | <br>• [JavaFX runtime components are missing](#javafx-runtime-components-are-missing)<br>&nbsp;&nbsp;&nbsp;└ [Explicación](#explicación)<br>&nbsp;&nbsp;&nbsp;└ [Solución](#solución)<br><br>• [Maven marca errores en el pom.xml](#maven-marca-errores-en-el-pomxml)<br>• [Eclipse no detecta JavaFX](#eclipse-no-detecta-javafx) |

---

## Prerrequisitos

Antes de comenzar, asegúrate de contar con las siguientes herramientas instaladas:

| Herramienta | Versión recomendada | Descarga |
|-------------|---------------------|----------|
| Java Development Kit (JDK) | 21 | [Sitio oficial](https://www.oracle.com/java/technologies/downloads/#jdk21-windows) |
| Eclipse IDE | Última estable | [Eclipse Foundation](https://www.eclipse.org/downloads/packages/) |
| Apache Maven | Incluido en eclipse | *Opcional / [Apache Maven 3.9+](https://maven.apache.org/download.cgi)* |
| JavaFX SDK | 21 | [OpenJFX](https://gluonhq.com/products/javafx/openjfx-21-release-notes/#21.0.11) |
| Scene Builder | 21 | [Gluon](https://github.com/gluonhq/scenebuilder/releases/tag/21.0.1) |



---

# Formas de importar el proyecto

## Método 1: Clonar el repositorio e importarlo localmente

### Para clonarlo:

Clona el repositorio utilizando Git:

```bash
git clone https://github.com/Crynda/Hypatia.git
```

---

### Importar el proyecto en Eclipse

1. Abrir Eclipse.
2. Seleccionar **Import...**
3. Elegir **Existing Maven Projects**.
4. Seleccionar la carpeta raíz del proyecto.
5. Finalizar la importación.

---

## Método 2: Clonar el proyecto desde Eclipse (Colaboradores)

Si planea contribuir al proyecto, es posible que Eclipse solicite la autenticación con su usuario de GitHub y un Personal Access Token (PAT) para realizar operaciones como push. Si no cuenta con uno, le recomendamos consultar la documentación oficial de GitHub o algún tutorial sobre su creación.

### Importarlo desde Eclipse

1. Damos click a "File" -> "Import..."
2. Damos click en "Git", seleccionamos "Projects from Git"
<img width="900" height="818" alt="image" src="https://github.com/user-attachments/assets/f7f169bf-f764-4310-afee-c259782d6cb6" />

    y despues en "Clone URI"
   <img width="900" height="818" alt="image" src="https://github.com/user-attachments/assets/35cef7be-944e-4fea-b0d0-2124fe74162c" />
3. Por ultimo pegamos el link del proyecto
   
```bash
https://github.com/Crynda/Hypatia.git
```
Y lo pegamos en el apartado de "URI", tambien habra que configurar el logueo de su usuario para el apartado de
autenticacion. En este sera necesario escribir su usuario y su Personal Access Token (PAT), le recomendamos ver un tutorial para esto
<img width="900" height="818" alt="image" src="https://github.com/user-attachments/assets/3277d84e-67f1-4431-b17a-91bb38bfd2dc" />
4. Y por ultimo damos next a todo y configuramos segun lo que busquemos.


Le recordamos que esta manera de clonarlo esta pensada principalemente si planea ser un contribuyente al proyecto, de otra manera le recomenamos la primera opcion

# Configuración de JavaFX

## Configuracion de los VM Arguments

Para poder ejecutar el programa como *Java Application* necesitaremos hacer unas modificaciones:
1. Entra al apartado de "Run" -> "Run Configurations..."
2. Seleccionamos el archivo Main de nuestro proyecto, en el apartado "Project" Verificaremos que sea de este ultimo:
<img width="1398" height="950" alt="image" src="https://github.com/user-attachments/assets/7c1d52a5-59b7-40fd-b879-838577a0a3f7" />

3. En el apartado "Arguments" Copiaremos y pegaremos la siguiente linea en "VM Arguments":
  
> **Ejemplo:** En este caso se descargó el **JavaFX SDK 21** y se instaló dentro de la carpeta **Documentos**.

> **Nota:** Asegúrate de haber instalado previamente todos los requisitos indicados en la sección [Prerrequisitos](#prerrequisitos).

```bash
--module-path "C:\Users\TU_USUARIO\Documents\javafx-sdk-21.0.11\lib" --add-modules javafx.controls,javafx.fxml
```

Ten en cuenta que la ruta mostrada es únicamente un ejemplo. Puedes instalar el JavaFX SDK en cualquier ubicación de tu preferencia; únicamente deberás reemplazar la ruta que aparece entre comillas por la correspondiente en tu equipo.

4. Damos en Apply y Close, y listo.


# Configurar Scene Builder (Opcional)

Una vez instalado **Scene Builder** (Puede ser encontrado en la sección [Prerrequisitos](#prerrequisitos) si aún no lo has instalado), sigue los pasos que se muestran a continuación para asociarlo correctamente con Eclipse y habilitar la edición visual de los archivos FXML.

1. Entraremos al apartado "Window " -> "Preferences..."
<img width="243" height="278" alt="image" src="https://github.com/user-attachments/assets/e27d519f-fa91-4bf8-bf97-406c7a354cea" />

2. Seleccionamos "JavaFX" y en el campo de "SceneBuilder executable" introduciremos la ruta en la que se instalo el programa"
<img width="1155" height="1010" alt="image" src="https://github.com/user-attachments/assets/ab004bd6-e1e3-4522-bbe9-da9c0c604dd8" />
3. Damos click en "Apply" y "Apply and close"


# Ejecutar la aplicación

---

Una vez completada la configuración de los **VM Arguments** ver sección: [Configuracion de los VM Arguments](#configuracion-de-los-vm-arguments), siga los siguientes pasos para ejecutar la aplicación:

1. Abra el proyecto en su IDE (por ejemplo, Eclipse o IntelliJ IDEA).
2. Diríjase a la ruta:  
   `src/application/`
3. Localice la clase principal (`Main`).
4. Haga clic derecho sobre el archivo y seleccione la opción **Run As > Java Application**  
   (o simplemente presione el botón de *Run* si ya está configurado como proyecto ejecutable).

---

La aplicación debería iniciarse correctamente después de estos pasos.

# Problemas comunes

## JavaFX runtime components are missing

### Explicación

Este error aparece cuando la aplicación no puede encontrar las librerías necesarias para ejecutar JavaFX en tiempo de ejecución.

En otras palabras, el entorno de ejecución no tiene disponibles los componentes del runtime de JavaFX, por lo que la aplicación no puede iniciarse correctamente.

---

### Solución

En la mayoría de los casos, este problema ocurre debido a que el proyecto no ha resuelto correctamente sus dependencias.

Esto suele estar relacionado con que el **pom.xml de Maven no termina de descargar o enlazar correctamente todas las librerías necesarias de JavaFX**, provocando que el runtime quede incompleto.

Para solucionarlo, es necesario asegurarse de que el proyecto **actualice correctamente sus dependencias de Maven** y vuelva a sincronizar el entorno de ejecución.

1. Daremos click derecho sobre el archivo pom.xml ubicado en la carpeta raiz
2. Seleccionamos "Maven" -> "Update Project..."
<img width="996" height="960" alt="image" src="https://github.com/user-attachments/assets/87884fc1-560c-4f77-a876-0589ecf5ddab" />
3. Seleccionamos la carpeta del proyecto y marcaremos la opcion "Force Update of Snapshots/Releases"
<img width="900" height="1010" alt="image" src="https://github.com/user-attachments/assets/7bc2f9cb-bd61-451f-ac35-054c66846072" />
4. Damos en "Ok"

En caso de seguir presentando errores le recomendamos abrir y visualizar el archivo "pom.xml", en caso de marcar errores le recomendamos ver la seccion a continuacion:


## Maven marca errores en el pom.xml

En caso de presentar errores en la siguientes lineas:
<img width="1051" height="93" alt="image" src="https://github.com/user-attachments/assets/778afb8e-e98e-4381-bddb-01c7b8ea830a" />

Haremos lo siguiente:
1. Daremos click en "Window" -> "Preferences..."
2. Iremos al apartado "Install/Update" -> "Automatic Updates"
3. Verificaremos que en el apartado "Donwload Options" la opcion "Download new updates..." este marcada
   <img width="1155" height="1010" alt="image" src="https://github.com/user-attachments/assets/c775077e-f602-41ac-adf6-7fd838e5be3c" />
4. Despues iremos "Maven" y marcaremos todas las opciones de download y la opcion de "Update Maven projects on startup"
<img width="1155" height="1010" alt="image" src="https://github.com/user-attachments/assets/b3c9b7a3-cbe5-4561-bdf5-a5765964b0f8" />


---

## Eclipse no detecta JavaFX

Le recomendamos entrar en la tienda de elcipse e instalar Javafx desde ahi, siguiendo estos pasos:

1. Abrimos la tienda de eclipse dando click en: "Help" -> "Eclipse Marketplace"
<img width="471" height="582" alt="image" src="https://github.com/user-attachments/assets/de6d8e61-abc3-452c-be35-7b92908455f9" />

2. Buscamos "Javafx" y nos aseguramos de instalarlo
<img width="1223" height="1010" alt="image" src="https://github.com/user-attachments/assets/ce42c1bf-fcac-4b61-9c37-1f2051415048" />

3. Una vez terminado sera necesario reiniciar el programa.


