package aventura.app;

import Exceptions.*;
import domain.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    private final Jugador j;
    private int habitacionActual;//Habitación en la que empezaremos el juego
    private Habitacion[] habitaciones;
    private String descripcionJuego;


    public Juego(int habitacionActual) {
        this.j = new Jugador();
        this.habitacionActual = habitacionActual;
        this.habitaciones = new Habitacion[3];
    }

    public void inicializar() {
        descripcionJuego = "Seis meses después del primer día del apocalipsis, eres J.A Bermudo, un exdetective \n" +
                "obsesionado con encontrar el \"Protocolo P.R.C\", la clave del brote, de la Dra. Sonia Joao. Tu pista te \n" +
                "lleva al sellado edificio de la Corporación Miravent, un laberinto silencioso lleno de peligros.\n" +
                "En la recepción, tu objetivo es encontrar información en las distintas salas para avanzar. Te enfrentas \n" +
                "a tu primera elección crucial: sala del servicio de mantenimiento (Izquierda) o las escaleras de servicio (Derecha) \n" +
                "hacia niveles inferiores. Tu supervivencia depende de la información que encuentres en cada sala. \n";

        Habitacion h0 = new Habitacion("SALA DE DESCANSO:Una cafetera queda encendida, burbujeando un café quemado con olor rancio. Sillas volcadas y bandejas con comida a medio comer sugieren una interrupción brusca.\n" +
                " Hay casilleros abiertos: dentro hay pertenencias personales, fotos de familia y tarjetas de acceso.\n" +
                " En una mesa, un móvil vibra sin parar, mostrando una notificación repetida:\n" +
                "\n" + "“Protocolo interno de emergencia activado. No abandonar el edificio.”");

        habitaciones[0] = h0;
        LlaveOxidada l = new LlaveOxidada("Una llave que parece oxidada", "Llave oxidada", true);
        h0.addObjeto(l);
        Habitacion h1 = new Habitacion("RECEPCIÓN:estas en la recepción inicial de la corporación miravent.Un gran mostrador de metal domina la entrada, cubierto de polvo y papeles amarillentos. \n" +
                "El logotipo de la corporación —medio borrado— adorna la pared del fondo, con luces que parpadean débilmente.\n" +
                "El suelo está lleno de huellas secas y trozos de cristales rotos; una silla caída sugiere que alguien salió con prisa. \n" +
                "En una esquina, una planta marchita aún permanece en su maceta, junto a una pantalla que muestra el mensaje: “MANTÉNGASE TRANQUILO. LA SITUACIÓN ESTÁ BAJO CONTROL.\n");
        habitaciones[1] = h1;
        Nota nota1 = new Nota("Nota", "Un papel arrugado", "El doctor Bermudo esta experimentando con algo bastante raro necesito que alguien lo pare. \n Dr Joao ");
        Mueble escritorio = new Mueble("Un escritorio que parece antiguo lleno de polvo. Tiene una nota encima", "Escritorio", true);
        Llave llaveDorada = new Llave("Una llave de oro con una calabera en el mango","Llave dorada", true,"123");
        Contenedor cofre = new Contenedor("Cofre","Un cofre que parece reforzado con un candado", "11", llaveDorada);
        h1.addObjeto(nota1);
        h1.addObjeto(escritorio);
        h1.addObjeto(cofre);
        Habitacion h2 = new Habitacion("LABORATORIO DE INVESTIGACIÓN:la puerta está trabada a medias, dejando un espacio estrecho para entrar. Luces rojas pulsantes bañan la sala. Tubos de ensayo rotos y frascos marcados con símbolos biológicos cubren las mesas. En el fondo, una cámara de contención de vidrio está agrietada desde dentro.\n" +
                "Un monitor reproduce una grabación detenida en una frase:\n" +
                "\n" + "“¡Aún no está listo para la exposición humana!”");
        habitaciones[2] = h2;
        Lija lija = new Lija("Una lija aspera que parece que sirve para quitar oxido","Lija", true);
        Contenedor ropero = new Contenedor("Ropero","Un ropero de madera que parece cerrado",null,lija);
        h2.addObjeto(ropero);
    }

    public String getDescripcionJuego() {
        return descripcionJuego;
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public Jugador getJ() {
        return j;
    }

    public void leer() throws ObjetoNoEncontradoException, InventarioVacioException {
        Scanner sc = new Scanner(System.in);
        Objeto encontrado = null;
        System.out.println(j.mostrarObjetosLeibles());
        System.out.println("¿Que objeto quieres leer?");
        String nombre = sc.nextLine();
        encontrado = buscarObjetoEnElInventario(nombre);
        if (encontrado != null) {
            if (encontrado instanceof Leible nota) {
                System.out.println(encontrado.getDescripcion());
                System.out.println("En el papel pone: " + nota.leer());
            }
        }
        // 3. Si al final del for 'encontrado' sigue siendo null, es que no lo tenemos
        if (encontrado == null) {
            throw new ObjetoNoEncontradoException("No tienes ningun objeto de ese tipo en tu inventario. \n" +
                    "Encuentralo primero.");
        }

    }

    /**
     * Busca un objeto por su nombre en la habitación actual y en el inventario.
     *
     * @param nombre El nombre del objeto a buscar.
     * @return El objeto encontrado o null si no existe en ningún sitio.
     */
    private Objeto buscarObjeto(String nombre) {
        int posActual = j.getHabitacionActual();
        Habitacion sala = habitaciones[posActual];

        for (Objeto obj : sala.getObjetosHabitacion()) {
            if (obj != null && obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }

        for (Objeto obj : j.getInventario()) {
            if (obj != null && obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }
        return null;
    }

    public Objeto buscarObjetoHabitacion(String nombre) throws ObjetoNoEncontradoException {
        // 1. Obtener la habitación donde está el jugador
        int posActual = j.getHabitacionActual();
        Habitacion sala = habitaciones[habitacionActual];


        Objeto[] obj = sala.getObjetosHabitacion();
        for (int i = 0; i < sala.getObjetosHabitacion().length; i++) {
            if (obj[i] != null && obj[i].getNombre().equalsIgnoreCase(nombre)) {
                return obj[i]; // Encontrado en el suelo
            }
        }
        throw new ObjetoNoEncontradoException("Ese objeto no esta en la habitacion");
    }

    public String mirar() {
        System.out.println(habitaciones[habitacionActual].getDescripcion());
        Objeto[] objetosHabitacion = habitaciones[habitacionActual].getObjetosHabitacion();
        int contadorObjetos = 0;
        for (int i = 0; i < objetosHabitacion.length; i++) {
            if (objetosHabitacion[i] != null) {
                contadorObjetos++;
                break;
            }
        }
        if (contadorObjetos == 0) {
            System.out.println("No hay objetos en la habitacion");
        }
        return Arrays.toString(objetosHabitacion);
    }

    /**
     * Metodo para ir a la derecha
     */
    public void derecha() throws NoHayMasHabitacionesException {
        if (habitacionActual == habitaciones.length - 1) {
            throw new NoHayMasHabitacionesException("No hay mas habitaciones a la derecha. Solo puedes ir a la izquierda");
        } else {
            System.out.println(habitaciones[habitacionActual + 1].getDescripcion());
            habitacionActual = habitacionActual + 1;
        }

    }

    /**
     * Metodo para ir a la izquierda
     */
    public void izquierda() throws NoHayMasHabitacionesException {
        if (habitacionActual == 0) {
            throw new NoHayMasHabitacionesException("No hay mas habitaciones a la izquierda. Solo puedes ir a la derecha");
        } else {
            System.out.println(habitaciones[habitacionActual - 1].getDescripcion());
            habitacionActual = habitacionActual - 1;
        }
    }


    public Objeto buscarObjetoEnElInventario(String nombre) throws ObjetoNoEncontradoException {
        Objeto[] obj = j.getInventario();
        for (int i = 0; i < j.getInventario().length; i++) {
            if (obj[i] != null && obj[i].getNombre().equalsIgnoreCase(nombre)) {
                return obj[i]; // Encontrado en el suelo
            }
        }
        throw new ObjetoNoEncontradoException("Ese objeto no esta en el inventario");
    }

    public void examinar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del objeto que quieras examinar:  \n");
        String objeto = sc.nextLine();
        Objeto aux = buscarObjeto(objeto);
        if (aux == null){
            System.out.println("Ese objeto no se encuentra en el inventario");
        }
        else {
            System.out.println("Descripción:");
            System.out.println(aux.getDescripcion());
            if (aux instanceof Leible l) {
                System.out.println("Contenido: \n");
                System.out.println(l.leer());
            }
        }
    }

    public void combinar() throws ObjetoNoEncontradoException, InventarioVacioException {
        // 1. Mostrar inventario y verificar si está vacío
        if (j.inventarioVacio()) {
            System.out.println("El inventario está vacío.");
            j.inventarioActual();
            return;
        }

        j.inventarioActual();
        Scanner sc = new Scanner(System.in);

        // 2. Pedir nombres de los objetos (Strings)
        System.out.println("¿Qué objeto quieres combinar?");
        String nombre1 = sc.nextLine();

        System.out.println("¿Con qué objeto quieres combinar " + nombre1 + "?");
        String nombre2 = sc.nextLine();

        // 3. Buscar los OBJETOS reales usando los nombres
        // IMPORTANTE: buscarObjeto debe devolver el objeto, no ser void
        Objeto obj1 = buscarObjetoEnElInventario(nombre1);
        Objeto obj2 = buscarObjetoEnElInventario(nombre2);

        // 4. Lógica de validación
        if (obj1 != null && obj2 != null) {

            if (!obj1.equals(obj2)) {

                if (obj1 instanceof Combinable c1) {// Java 16+ Pattern Matching
                    try {
                        // Asegúrate de que el metodo combinar acepte el tipo de obj2
                        Objeto resultante = c1.combinar(obj2);

                        eliminarObjetoDelInventario(obj1);
                        eliminarObjetoDelInventario(obj2);
                        guardarObjeto(resultante);

                        System.out.println("Los objetos se han combinado con éxito.");

                    } catch (ObjetoNoCombinableException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                } else {
                    System.err.println("El objeto '" + nombre1 + "' no es combinable.");
                }

            } else {
                System.err.println("No se puede combinar el objeto consigo mismo.");
            }

        } else {
            System.err.println("Uno de los dos objetos no se ha encontrado en tu inventario.");
        }
    }



    /*+
     * Metodo para coger objetos
     */
    public void cogerObjetos() throws ObjetoNoEncontradoException, ObjetoNoCogibleException {
        Scanner sc = new Scanner(System.in);
        //llamar a listar objetos
        int numeroObjetos = listarObjetos();
        if (numeroObjetos == 0) { //Si no hay objetos en la sala decirlo y sacarlo de aqui
            System.out.println("No hay objetos aquí");
            return;
        }
        //almacenar el objeto que el jugador quiere coger
        System.out.println("¿Que objeto quieres coger?");
        String nombreObjeto = sc.nextLine();
        Objeto objeto = buscarObjetoHabitacion(nombreObjeto);
        if (objeto != null) {
            if (objeto instanceof Inventariable) {
                if (guardarObjeto(objeto)) {
                    System.out.println("✅ Has cogido " + objeto);
                }
                eliminarObjetoDeHabitacion(objeto); //Eliminamos el objeto del mapa
            } else {
                throw new ObjetoNoCogibleException("El objeto: " + objeto.getNombre() + " no se puede guardar");
            }
        } else {
            System.out.println("Ese objeto no esta en esta habitación");
        }

    }

    public void abrir() throws ObjetoNoEncontradoException {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Qué quieres abrir?");
        String nombre = sc.nextLine();
        Objeto obj = buscarObjetoHabitacion(nombre);

        // 2. Comprobamos si se puede abrir (Interfaz Abrible)
        if (obj instanceof Abrible) {
            Abrible contenedor = (Abrible) obj;

            // 3. Llavero automático: busca una llave en tu mochila
            Llave llaveJugador = null;
            Objeto[] inv = j.getInventario();
            for (int i = 0; i < inv.length; i++) {
                if (inv[i] != null && inv[i] instanceof Llave) {
                    llaveJugador = (Llave) inv[i];
                    break; // Cogemos la primera que encontremos
                }
            }

            // 4. Pedimos al objeto que se abra y recibimos la RespuestaAccion
            RespuestaAccion respuesta = contenedor.abrir(llaveJugador);
            System.out.println(respuesta.mensaje());

            // 5. Si tuvo éxito y es un contenedor, le robamos el contenido
            if (respuesta.exito() && obj instanceof Contenedor) {
                Contenedor c = (Contenedor) obj;
                Objeto premio = c.getObjetoContenido();
                if (premio != null) {
                    if (guardarObjeto(premio)) {
                        System.out.println("🎁 ¡Has encontrado " + premio.getNombre() + " y lo guardas!");
                    } else {
                        System.out.println("Ves un " + premio.getNombre() + " dentro, pero no tienes espacio.");
                    }
                }
            }
        } else {
            System.out.println("No puedes abrir eso, no tiene cerradura.");
        }
    }

    /**
     * Elimina un objeto específico del array de la habitación donde está el jugador.
     */
    private void eliminarObjetoDeHabitacion(Objeto objABorrar) {
        // 1. Obtenemos el array de objetos de la sala actual
        Objeto[] objetosEnSala = habitaciones[j.getHabitacionActual()].getObjetosHabitacion();

        // 2. Buscamos el objeto exacto por referencia
        for (int i = 0; i < objetosEnSala.length; i++) {
            if (objetosEnSala[i] == objABorrar) {
                // 3. Ponemos la posición a null para que "desaparezca"
                objetosEnSala[i] = null;
                return;
            }
        }
    }

    private void eliminarObjetoDelInventario(Objeto objABorrar){
        Objeto[] objetosEnInventario = j.getInventario();

        for (int i = 0; i < objetosEnInventario.length; i++) {
            if (objetosEnInventario[i] == objABorrar) {
                // 3. Ponemos la posición a null para que "desaparezca"
                objetosEnInventario[i] = null;
                return;
            }
        }
    }

    private int listarObjetos() {
        int contador = 0;
        for (int i = 0; i < habitaciones[habitacionActual].getObjetosHabitacion().length; i++) {
            if (habitaciones[habitacionActual].getObjetosHabitacion()[i] != null) {
                System.out.println(habitaciones[habitacionActual].getObjetosHabitacion()[i]);
                contador++;
            }
        }

        return contador;
    }

    private boolean guardarObjeto(Objeto objeto) {
        int ocupado = 0;
        for (int i = 0; i < j.getInventario().length; i++) {
            if (j.getInventario()[i] != null) ocupado++;
        }
        if (ocupado == j.getInventario().length) {
            System.out.println("❌ No tienes espacio en el inventario");
            return false;
        }

        for (int i = 0; i < j.getInventario().length; i++) {
            if (j.getInventario()[i] == null) {
                j.getInventario()[i] = objeto;
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Puedes utilizar la clase MiEntradaSalida, que viviría en el paquete io
        Scanner sc = new Scanner(System.in);
        boolean jugando = true;
        System.out.println("'LA CURA'");
        System.out.println("------------------------------------------");

        Juego t = new Juego(1);
        t.inicializar();

        System.out.println(t.descripcionJuego);

        System.out.println(t.habitaciones[t.habitacionActual].getDescripcion());

        System.out.println("Las opciones son ayuda, mirar, inventario, \n" +
                "ir derecha, ir izquierda, coger [objeto] y salir");

        // TODO 2: Iniciar el bucle principal del juego (game loop)
        while (jugando) {

            // TODO 3: Leer el comando del usuario por teclado
             System.out.println("¿Qué quieres hacer ahora?: ");
            String comando = sc.nextLine();

            /*
            TODO 4: Crear un 'switch' o una estructura 'if-else if'
             para procesar el 'comando' del usuario.
             Debe gestionar como mínimo: "ayuda", "mirar", "inventario",
             "ir derecha", "ir izquierda", "coger [objeto]" y "salir".
             */

            switch (comando.toLowerCase()) {

                case "ir derecha":
                    try {
                        t.derecha();
                        break;
                    } catch (NoHayMasHabitacionesException e) {
                        System.out.println(e.getMessage());
                    }
                case "ir izquierda":
                    try {
                        t.izquierda();
                        break;
                    } catch (NoHayMasHabitacionesException e) {
                        System.out.println(e.getMessage());
                    }
                case "mirar":
                    t.mirar();
                    t.listarObjetos();
                    break;
                case "salir":
                    jugando = false;
                    break;
                case "ayuda":
                    System.out.println("Las opciones son ayuda, mirar, inventario,\n" +
                            "ir derecha, ir izquierda, coger [objeto] y salir \n");
                    break;
                case "coger":
                    try {
                        t.cogerObjetos();
                    } catch (ObjetoNoEncontradoException | ObjetoNoCogibleException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "inventario":
                    try {
                        System.out.println(t.getJ().inventarioActual());
                        break;
                    }catch (InventarioVacioException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "leer":
                    try {
                        t.leer();
                        break;
                    } catch (ObjetoNoEncontradoException | InventarioVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "combinar":
                    try {
                        t.combinar();
                        break;
                    }catch (ObjetoNoEncontradoException | InventarioVacioException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "abrir":
                    try {
                        t.abrir();
                        break;
                    } catch (ObjetoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "examinar":
                    t.examinar();
                    break;
            }

        }

        System.out.println("¡Gracias por jugar!");
        sc.close();
    }

    /*
    (Opcional - Buenas Prácticas)
    Si el 'switch' se vuelve muy grande, podéis crear métodos privados
    para organizar el código, por ejemplo:
    private static void procesarComandoCoger(String comando) { ... }
    private static void mostrarInfoHabitacion() { ... }
    */
}