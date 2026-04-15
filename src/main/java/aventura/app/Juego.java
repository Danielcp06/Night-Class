package main.java.aventura.app;


import main.java.Exceptions.AventuraException;
import main.java.Exceptions.InventarioLlenoException;
import main.java.Exceptions.ObjetoNoCombinableException;
import main.java.domain.*;
import main.java.io.MiEntradaSalida;

import java.util.Locale;

/**
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD4-UD5).
 */
public class Juego {

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    private String descripcionJuego;

    // El mapa de habitaciones.
    private Habitacion[] habitaciones;

    // El inventario ahora se ha movido a la clase Jugador

    // Variable que guarda la posición actual del jugador
    private Jugador jugador;

    // --- FIN DE LA DEFINICIÓN DE DATOS ---

    /**
     * Constructor de la clase Juego.
     *
     * @param jugador El jugador que participa en el juego.
     */
    public Juego(Jugador jugador) {
        // Inicialización del mapa de habitaciones
        habitaciones = new Habitacion[3]; // Cambia el tamaño según el número de habitaciones que tengas
        this.jugador = jugador;
        inicializarJuego();
    }

    /**
     * Inicializa el juego creando las habitaciones y los objetos.
     */
    private void inicializarJuego() {

        descripcionJuego = "Seis meses después del primer día del apocalipsis, eres J.A Bermudo, un exdetective \n" +
                "obsesionado con encontrar el Protocolo P.R.C, la clave del brote, de la Dra. Sonia Joao. Tu pista te \n" +
                "lleva al sellado edificio de la Corporación Miravent, un laberinto silencioso lleno de peligros.\n" +
                "En la recepción, tu objetivo es encontrar información en las distintas salas para avanzar. Te enfrentas \n" +
                "a tu primera elección crucial: sala del servicio de mantenimiento (Izquierda) o las escaleras de servicio (Derecha) \n" +
                "hacia niveles inferiores. Tu supervivencia depende de la información que encuentres en cada sala. \n";
        //Cremos el escenario
        Habitacion descanso = new Habitacion("SALA DE DESCANSO:Una cafetera queda encendida, burbujeando un café quemado con olor rancio. Sillas volcadas y bandejas con comida a medio comer sugieren una interrupción brusca.\n" +
                " Hay casilleros abiertos: dentro hay pertenencias personales, fotos de familia y tarjetas de acceso.\n" +
                " En una mesa, un móvil vibra sin parar, mostrando una notificación repetida:\n" +
                "\n" + "“Protocolo interno de emergencia activado. No abandonar el edificio.”");
        descanso.addObjeto(new Mueble("Una estantería llena de libros y cuadernos.","Estanteria" , true));
        descanso.addObjeto(new Item("Una llave pequeña de metal.","Llave" , true));
        descanso.addObjeto(new Lija("Lija","Lija",true));
        habitaciones[0] = descanso;

        Habitacion recepcion = new Habitacion("RECEPCIÓN:estas en la recepción inicial de la corporación miravent.Un gran mostrador de metal domina la entrada, cubierto de polvo y papeles amarillentos. \n" +
                "El logotipo de la corporación —medio borrado— adorna la pared del fondo, con luces que parpadean débilmente.\n" +
                "El suelo está lleno de huellas secas y trozos de cristales rotos; una silla caída sugiere que alguien salió con prisa. \n" +
                "En una esquina, una planta marchita aún permanece en su maceta, junto a una pantalla que muestra el mensaje: “MANTÉNGASE TRANQUILO. LA SITUACIÓN ESTÁ BAJO CONTROL.\n"
        );
        recepcion.addObjeto(new Contenedor("Taquilla", "Una taquilla metálica cerrada.", "Llave123", new LlaveOxidada("Una llave llena de oxido","Llave oxidada",true)));
        habitaciones[1] = recepcion;

        Habitacion laboratorio = new Habitacion("LABORATORIO DE INVESTIGACIÓN:la puerta está trabada a medias, dejando un espacio estrecho para entrar. Luces rojas pulsantes bañan la sala. Tubos de ensayo rotos y frascos marcados con símbolos biológicos cubren las mesas. En el fondo, una cámara de contención de vidrio está agrietada desde dentro.\n" +
                "Un monitor reproduce una grabación detenida en una frase:\n" +
                "\n" + "“¡Aún no está listo para la exposición humana!”");
        laboratorio.addObjeto(new Nota("Nota", "Una nota escrita a mano","La llave está bajo la estantería."));
        laboratorio.addObjeto(new Mueble("Un escritorio con varios papeles encima.","Escritorio" , true));
        Llave llavePequeña = new Llave("Una pequeña llave de metal.", "Llave pequeña", true, "Llave123");
        laboratorio.addObjeto(new Contenedor("Cajon", "Un cajón de madera que parece roto", null ,llavePequeña));
        laboratorio.addObjeto(new Contenedor("Cofre", "Un cofre de aspecto antiguo con un candado.","11", new Llave("Una llave dorada con una calabera de rubi en el mango","Super llave",true,"Superllave")));
        habitaciones[2] = laboratorio;
    }

    public static void main(String[] args) {
        Juego juego = new Juego(new Jugador("Jugador1"));
        juego.iniciar();

        System.out.println("¡Gracias por jugar!");

    }

    public void iniciar() {
        // Aquí puedes implementar la lógica para iniciar el juego si es necesario
        boolean jugando = true;

        System.out.println("¡Bienvenido a 'NIGHT CLASS'!");
        System.out.println("------------------------------------------");
        mostrarAyuda();
        System.out.println("------------------------------------------");

        //Muestra la descripción general del juego
        System.out.println(descripcionJuego);

        //Muestra la descripción de la primera habitación
        System.out.println(getHabitacionActual().mirar());

        while (jugando) {

            //Leer el comando del usuario por teclado
            System.out.print("\n> ");
            String comando = MiEntradaSalida.solicitarCadena("¿Qué quieres hacer? ").toLowerCase(Locale.ROOT);


            switch (comando) {
                case "mirar" -> mostrarInfoHabitacion();
                case "inventario" -> mostrarObjetosInventario();
                case "ir izquierda" -> cmdIrIzquierda();
                case "ir derecha" -> cmdIrDerecha();
                case "coger" -> cmdCoger();
                case "examinar" -> cmdExaminar();
                case "abrir" -> cmdAbrir();
                case "combinar" -> cmdCombinar();
                case "salir" -> {
                    jugando = false;
                    System.out.println("Saliendo del juego...");
                }

                default -> mostrarAyuda();
            }

        }
    }

    /**
     * Mueve al jugador a la habitación de la izquierda si es posible.
     */
    private void cmdIrIzquierda() {
        if (jugador.getHabitacionActual() > 0) {
            jugador.setHabitacionActual(jugador.getHabitacionActual() - 1);
            System.out.println("Te has movido a la habitación de la izquierda.");
            mostrarInfoHabitacion();
        } else {
            System.out.println("No puedes ir más a la izquierda.");
        }
    }

    /**
     * Mueve al jugador a la habitación de la derecha si es posible.
     */
    private void cmdIrDerecha() {
        if (jugador.getHabitacionActual() < habitaciones.length - 1) {
            jugador.setHabitacionActual(jugador.getHabitacionActual() + 1);
            System.out.println("Te has movido a la habitación de la derecha.");
            mostrarInfoHabitacion();
        } else {
            System.out.println("No puedes ir más a la derecha.");
        }
    }

    /**
     * Procesa el comando de coger un objeto de la habitación actual.
     */
    private void cmdCoger() {
        if (!hayObjetosEnHabitacion()) {
            System.out.println("No hay objetos para coger en esta habitación.");
            return;
        }

        mostrarObjetosHabitacion();
        System.out.print("¿Qué objeto quieres coger? ");
        String objetoACoger = MiEntradaSalida.solicitarCadena("").trim();

        Objeto objeto = buscarObjeto(objetoACoger);
        if (objeto == null) {
            System.out.println("No se encontró ningún objeto llamado " + objetoACoger + ".");
        }
        else {
            procesarComandoCoger(objeto);
        }
    }

    /**
     * Procesa el comando de examinar un objeto.
     */
    private void cmdExaminar() {
        System.out.println("¿Qué objeto quieres examinar?");
        mostrarTodosLosObjetos();
        String objetoAExaminar = MiEntradaSalida.solicitarCadena("").trim();

        Objeto objeto = buscarObjeto(objetoAExaminar);
        if (objeto == null) {
            System.out.println("No se encontró ningún objeto llamado " + objetoAExaminar + ".");
        } else {
            System.out.println(objeto.getDescripcion());
            if (objeto instanceof Leible leible) {
                System.out.println("Lees: \n" + leible.leer());
            }
        }
    }

    /**
     * Procesa el comando de abrir un contenedor.
     */
    private void cmdAbrir() {
        System.out.println("¿Qué contenedor quieres abrir?");
        //TODO: Hacer que el método siguiente en lugar de mostrar devuelva una lista
        mostrarObjetosAbribles();
        String contenedorAAbrir = MiEntradaSalida.solicitarCadena("").trim();

        Objeto objeto = buscarObjeto(contenedorAAbrir);

        if (objeto == null) {
            System.out.println("No se encontró ningún contenedor llamado " + contenedorAAbrir + ".");
        } else {
            procesarComandoAbrir(objeto);
        }
    }

    /**
     * Procesa el comando de combinar objetos.
     */
    private void cmdCombinar() {
        System.out.println("¿Qué objetos quieres combinar?");
        mostrarTodosLosObjetos();

        String objeto1Nombre = MiEntradaSalida.solicitarCadena("Primer objeto: ").trim();
        Objeto objeto1 = buscarObjeto(objeto1Nombre);

        if (objeto1 == null){
            System.out.printf("No se encontró %s%n", objeto1Nombre);
            return;
        }

        String objeto2Nombre = MiEntradaSalida.solicitarCadena("Segundo objeto: ").trim();
        Objeto objeto2 = buscarObjeto(objeto2Nombre);

        if (objeto2 == null) {
            System.out.printf("No se encontró %s%n", objeto2Nombre);
            return;
        }

        if (objeto1 instanceof Combinable combinable1) {
            try {
                Objeto resultado = combinable1.combinar(objeto2);
                if (resultado != null) {
                    System.out.printf("Has combinado %s y %s para crear %s.%n",
                            objeto1.getNombre(), objeto2.getNombre(), resultado.getNombre());

                    // 1. Eliminar los objetos originales del inventario o de la habitación
                    consumirObjeto(objeto1);
                    consumirObjeto(objeto2);

                    // 2. Añadir el nuevo objeto al inventario
                    try {
                        jugador.coger(resultado);
                        System.out.println("El nuevo objeto está en tu inventario.");
                    } catch (InventarioLlenoException e) {
                        // El inventario está lleno, dejamos el objeto en la habitación
                        System.out.println("¡Cuidado! Tu inventario estaba lleno y el objeto cayó al suelo.");
                        getHabitacionActual().addObjeto(resultado);
                        System.out.println("El nuevo objeto está en la habitación actual.");
                    }
                } else {
                    System.out.println("La combinación no produjo ningún objeto.");
                }

            } catch (AventuraException | ObjetoNoCombinableException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(objeto1.getNombre() + " no se puede combinar con otros objetos.");
        }
    }

    /**
     * Muestra la ayuda con los comandos disponibles.
     */
    private void mostrarAyuda() {
        System.out.println("Estos son los comandos que puedes ejecutar:");
        System.out.println("Ir derecha: intenta ir hacia la derecha");
        System.out.println("Ir izquierda: intenta ir hacia la izquierda");
        System.out.println("Mirar: muestra la descripción de la habitación actual y los objetos que hay en ella");
        System.out.println("Examinar: muestra la descripción de un objeto específico");
        System.out.println("Inventario: muestra los objetos que llevas contigo");
        System.out.println("Coger: intenta coger un objeto de la habitación actual");
        System.out.println("Abrir: intenta abrir un contenedor (cajón, cofre, taquilla, etc.)");
        System.out.println("Combinar: intenta combinar dos objetos para crear uno nuevo");
        System.out.println("Salir: termina el juego");
        System.out.println("Escribe sólo el comando, sin parámetros adicionales.");
    }

    /**
     * Muestra la información de la habitación actual.
     */
    private void mostrarInfoHabitacion() {
        System.out.println(getHabitacionActual().mirar());

    }

    /**
     * Procesa el comando de coger un objeto de la habitación actual.
     * @param objetoACoger El objeto que el jugador desea coger.
     */
    private void procesarComandoCoger(Objeto objetoACoger) {
        assert objetoACoger != null : "El objeto a coger no puede ser null";

        boolean objetoEncontrado = false;
        for (int i = 0; i < getHabitacionActual().getObjetosHabitacion().length && !objetoEncontrado; i++) {
            if (objetoACoger.equals(getHabitacionActual().getObjetosHabitacion()[i])) {
                try {
                    objetoEncontrado = true;
                    jugador.coger(objetoACoger);
                    getHabitacionActual().getObjetosHabitacion()[i] = null; // Eliminar el objeto de la habitación
                    System.out.println("Has cogido " + objetoACoger.getNombre() + " y lo has añadido a tu inventario.");
                } catch (AventuraException | InventarioLlenoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if (!objetoEncontrado) {
            System.out.println("No hay ningún objeto llamado " + objetoACoger.getNombre() + " en esta habitación.");
        }
    }

    /**
     * Procesa el comando de abrir un contenedor.
     * @param objeto El objeto que el jugador desea abrir.
     */
    private void procesarComandoAbrir(Objeto objeto) {
        if (!(objeto instanceof Abrible abrible)) {
            System.out.printf("%s no se puede abrir.%n", objeto.getNombre());
        } else {
            if (abrible.estaAbierto()) {
                System.out.println("Eso ya está abierto, no pierdas el tiempo.");
                return;
            }

            Llave llaveParaUsar = null;

            // Buscar una llave en el inventario que pueda abrir el contenedor
            for (Objeto objInventario : jugador.getInventario()) {
                if (objInventario instanceof Llave llave) {
                    if (abrible.getCodigoNecesario() != null && llave.getCodigoDeSeguridad().equals(abrible.getCodigoNecesario())) {
                        llaveParaUsar = llave;
                        break;
                    }
                }
            }

            RespuestaAccion respuesta = abrible.abrir(llaveParaUsar);
            System.out.println(respuesta.mensaje());

            if (respuesta.exito()) {
                if (abrible.getContenido() == null) {
                    System.out.println("El contenedor está vacío.");
                }
                else {
                    System.out.println("Has encontrado: " + abrible.getContenido().getNombre());
                    try {
                        //TODO: Si hubiera más de un objeto dentro, habría que implementar un bucle aquí o hacer que el contenido sea una lista de objetos.
                        jugador.coger(abrible.getContenido());
                        System.out.println("Has cogido " + abrible.getContenido().getNombre() + " y lo has añadido a tu inventario.");
                        abrible.setContenido(null); // Vaciar el contenido del contenedor
                    } catch (InventarioLlenoException e) {
                        System.out.println(e.getMessage());
                                    /*
                                    No ha podido coger el objeto. Para que no haya problemas de pérdida de objetos, lo
                                    que haremos será cerrar el contenedor de nuevo y dejar el objeto dentro.
                                     */
                        abrible.cerrar();
                    } catch (AventuraException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Muestra los objetos presentes en la habitación actual.
     */
    private void mostrarObjetosHabitacion() {
        System.out.print("Objetos en la habitación: ");
        boolean hayObjetos = false;
        boolean hayMasDeUnObjeto = false;
        for (Objeto objeto : getHabitacionActual().getObjetosHabitacion()) {
            if (objeto != null && objeto.isVisible()) {
                hayObjetos = true;
                System.out.print(hayMasDeUnObjeto ? ", " + objeto : objeto);
                hayMasDeUnObjeto = true;
            }
        }
        if (!hayObjetos) {
            System.out.print("No hay objetos.");
        }
        System.out.println();
    }

    /**
     * Verifica si hay objetos en la habitación actual.
     *
     * @return true si hay al menos un objeto, false si no hay ninguno.
     */
    private boolean hayObjetosEnHabitacion() {
        for (Objeto objeto : getHabitacionActual().getObjetosHabitacion()) {
            if (objeto != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra los objetos presentes en el inventario del jugador.
     */
    private void mostrarObjetosInventario() {
        System.out.print("Objetos en el inventario: ");
        boolean hayObjetos = false;
        boolean hayMasDeUnObjeto = false;
        for (Objeto objeto : jugador.getInventario()) {
            if (objeto != null) {
                hayObjetos = true;
                System.out.print(hayMasDeUnObjeto ? ", " + objeto : objeto);
                hayMasDeUnObjeto = true;
            }
        }
        if (!hayObjetos) {
            System.out.print("No hay objetos.");
        }
        System.out.println();
    }

    /**
     * Muestra todos los objetos disponibles, tanto en la habitación actual como en el inventario del jugador.
     */
    private void mostrarTodosLosObjetos() {
        mostrarObjetosHabitacion();
        mostrarObjetosInventario();
    }

    /**
     * Muestra los objetos abribles disponibles, tanto en la habitación actual como en el inventario del jugador.
     */
    private void mostrarObjetosAbribles() {
        System.out.print("Contenedores disponibles: ");
        boolean hayObjetos = false;
        boolean hayMasDeUnObjeto = false;
        for (Objeto objeto : getHabitacionActual().getObjetosHabitacion()) {
            if (objeto instanceof Abrible) {
                hayObjetos = true;
                System.out.print(hayMasDeUnObjeto ? ", " + objeto : objeto);
                hayMasDeUnObjeto = true;
            }
        }
        for (Objeto objeto : jugador.getInventario()) {
            if (objeto instanceof Abrible) {
                hayObjetos = true;
                System.out.print(hayMasDeUnObjeto ? ", " + objeto : objeto);
                hayMasDeUnObjeto = true;
            }
        }
        if (!hayObjetos) {
            System.out.print("No hay nada para abrir ahora mismo.");
        }
        System.out.println();
    }

    /**
     * Busca un objeto por su nombre, primero en la habitación actual y luego en el inventario del jugador.
     *
     * @param nombre El nombre del objeto a buscar.
     * @return El objeto si se encuentra, o null si no se encuentra en ninguno de los dos lugares.
     */
    private Objeto buscarObjeto(String nombre) {
        // 1. Buscamos en la habitación (Prioridad 1: Lo que veo)
        Objeto encontrado = getHabitacionActual().buscar(nombre);

        if (encontrado != null) {
            return encontrado;
        }

        // 2. Si no está en la sala, buscamos en el bolsillo (Prioridad 2: Lo que tengo)
        return jugador.buscarEnInventario(nombre);
    }

    /**
     * Obtiene la habitación actual del jugador.
     * @return La habitación en la que se encuentra el jugador.
     */
    private Habitacion getHabitacionActual() {
        return habitaciones[jugador.getHabitacionActual()];
    }

    /**
     * Elimina un objeto del juego, ya sea que esté en la habitación o en el inventario.
     * Usado tras combinar objetos.
     */
    private void consumirObjeto(Objeto obj) {
        // Intentamos borrar del inventario
        jugador.eliminarDeInventario(obj);
        // Intentamos borrar de la habitación
        getHabitacionActual().eliminarObjeto(obj);
    }

}