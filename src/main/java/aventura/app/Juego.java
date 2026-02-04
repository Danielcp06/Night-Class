package aventura.app;

import domain.Habitacion;
import domain.Jugador;
import domain.Llave;
import domain.Objeto;

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
        Llave l = new Llave("Una llave que parece oxidada", "Llave oxidada",true,null);
        h0.addObjeto(l);
        Habitacion h1 = new Habitacion("RECEPCIÓN:estas en la recepción inicial de la corporación miravent.Un gran mostrador de metal domina la entrada, cubierto de polvo y papeles amarillentos. \n" +
                "El logotipo de la corporación —medio borrado— adorna la pared del fondo, con luces que parpadean débilmente.\n" +
                "El suelo está lleno de huellas secas y trozos de cristales rotos; una silla caída sugiere que alguien salió con prisa. \n" +
                "En una esquina, una planta marchita aún permanece en su maceta, junto a una pantalla que muestra el mensaje: “MANTÉNGASE TRANQUILO. LA SITUACIÓN ESTÁ BAJO CONTROL.\n");
        habitaciones[1] = h1;
        Habitacion h2 = new Habitacion("LABORATORIO DE INVESTIGACIÓN:la puerta está trabada a medias, dejando un espacio estrecho para entrar. Luces rojas pulsantes bañan la sala. Tubos de ensayo rotos y frascos marcados con símbolos biológicos cubren las mesas. En el fondo, una cámara de contención de vidrio está agrietada desde dentro.\n" +
                "Un monitor reproduce una grabación detenida en una frase:\n" +
                "\n" + "“¡Aún no está listo para la exposición humana!”");
        habitaciones[2] = h2;
    }

    public String getDescripcionJuego() {
        return descripcionJuego;
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public void inventarioActual() {
        int contador = 0;
        for (int i = 0; i < j.getInventario().length; i++) {
            if (j.getInventario()[i] != null) {
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("El inventario está vacío");
        } else {
            for (int i = 0; i < j.getInventario().length; i++) {
                if (j.getInventario()[i] != null) {
                    System.out.println(i + 1 + ") " + j.getInventario()[i]);
                }
            }
        }
    }

    /**
     * Busca un objeto por su nombre en la habitación actual y en el inventario.
     * @param nombre El nombre del objeto a buscar.
     * @return El objeto encontrado o null si no existe en ningún sitio.
     */
    private Objeto buscarObjeto(String nombre) {
        // 1. Obtener la habitación donde está el jugador
        int posActual = j.getHabitacionActual();
        Habitacion sala = habitaciones[posActual];

        // 2. Buscar en los objetos de la HABITACIÓN
        for (Objeto obj : sala.getObjetosHabitacion()) {
            if (obj != null && obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj; // Encontrado en el suelo
            }
        }

        // 3. Buscar en el INVENTARIO del jugador
        for (Objeto obj : j.getInventario()) {
            if (obj != null && obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj; // Encontrado en la mochila
            }
        }

        // 4. Si llegamos aquí, es que no está en ningún lado
        return null;
    }

    public Objeto buscarObjetoHabitacion(String nombre){
        // 1. Obtener la habitación donde está el jugador
        int posActual = j.getHabitacionActual();
        Habitacion sala = habitaciones[posActual];

        // Buscar en los objetos de la HABITACIÓN
        for (Objeto obj : sala.getObjetosHabitacion()) {
            if (obj != null && obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj; // Encontrado en el suelo
            }

        }
        return null;
    }

    public String mirar() {
        System.out.println(habitaciones[habitacionActual].getDescripcion());
        Objeto[] objetosHabitacion = habitaciones[habitacionActual].getObjetosHabitacion();
        int contadorObjetos = 0;
        for (int i = 0; i < objetosHabitacion.length; i++) {
            if (objetosHabitacion[i] != null) {
                System.out.println(objetosHabitacion[i]);
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
    public void derecha() {
        if (habitacionActual == habitaciones.length - 1) {
            System.out.println("No hay mas habitaciones a la derecha. Solo puedes ir a la izquierda");
        } else {
            System.out.println(habitaciones[habitacionActual + 1].getDescripcion());
            habitacionActual = habitacionActual + 1;
        }

    }

    /**
     * Metodo para ir a la izquierda
     */
    public void izquierda() {
        if (habitacionActual == 0) {
            System.out.println("No hay mas habitaciones a la izquierda. Solo puedes ir a la derecha");
        } else {
            System.out.println(habitaciones[habitacionActual - 1].getDescripcion());
            habitacionActual = habitacionActual - 1;
        }
    }


        /*+
         * Metodo para coger objetos
         */
        public void cogerObjetos () {
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

                if (guardarObjeto(objeto)){
                    System.out.println("✅ Has cogido " + objeto);
                    eliminarObjetoDeHabitacion(objeto); //Eliminamos el objeto del mapa
                }
            }else{
                System.out.println("Ese objeto no esta en esta habitación");
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
                return; // Ya lo hemos borrado, salimos del método
            }
        }
    }

        private int listarObjetos () {
            int contador = 0;
            for (int i = 0; i < habitaciones[habitacionActual].getObjetosHabitacion().length; i++) {
                if (habitaciones[habitacionActual].getObjetosHabitacion()[i] != null) {
                    System.out.println(habitaciones[habitacionActual].getObjetosHabitacion()[i]);
                    contador++;
                }
            }

            return contador;
        }

        private boolean guardarObjeto (Objeto objeto){
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

        public static void main (String[]args){
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
                        t.derecha();
                        break;
                    case "ir izquierda":
                        t.izquierda();
                        break;
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
                        t.cogerObjetos();
                        break;
                    case "inventario":
                        t.inventarioActual();
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