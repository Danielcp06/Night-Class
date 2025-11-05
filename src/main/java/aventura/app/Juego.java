package aventura.app;

import java.util.Scanner;

/**
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    private static String descripcionJuego = "Seis meses después del primer día del apocalipsis, eres J.A Bermudo, un exdetective \n" +
            "obsesionado con encontrar el \"Protocolo P.R.C\", la clave del brote, de la Dra. Sonia Joao. Tu pista te \n" +
            "lleva al sellado edificio de la Corporación Miravent, un laberinto silencioso lleno de peligros.\n" +
            "En la recepción, tu objetivo es encontrar información en las distintas salas para avanzar. Te enfrentas \n" +
            "a tu primera elección crucial: sala del servicio de mantenimiento (Izquierda) o las escaleras de servicio (Derecha) \n" +
            "hacia niveles inferiores. Tu supervivencia depende de la información que encuentres en cada sala. \n";

    // El mapa de habitaciones.
    // TODO: (Skin) ¡Rellenad esto con vuestras descripciones!
    private static String[] habitaciones = {
            "Una sala pequeña, iluminada por luces parpadeantes. En las paredes hay herramientas viejas y planos manchados. \n" +
                    "Todo parece abandonado con prisa. Huele a metal, a polvo… y a algo más.", // Posición 0

            "Estas en la recepción inicial de la corporación miravent.Un gran mostrador de metal domina la entrada, cubierto de polvo y papeles amarillentos. \n" +
                    "El logotipo de la corporación —medio borrado— adorna la pared del fondo, con luces que parpadean débilmente.\n" +
                    "El suelo está lleno de huellas secas y trozos de cristales rotos; una silla caída sugiere que alguien salió con prisa. \n" +
                    "En una esquina, una planta marchita aún permanece en su maceta, junto a una pantalla que muestra el mensaje: “MANTÉNGASE TRANQUILO. LA SITUACIÓN ESTÁ BAJO CONTROL.", // Posición 1

            "Estás en el aula 105. Hay una puerta a la IZQUIERDA y has visto una 'llave' en una mesa.", // Posición 2

    };

    // Los objetos que hay en cada habitación.
    // TODO: (Skin) Rellenad esto con vuestros objetos
    private static String[][] objetosMapa = {
            {null, null},           // Objetos en Habitación 0
            {null, null},           // Objetos en Habitación 1
            {"llave", "nota"},      // Objetos en Habitación 2
    };

    // El inventario del jugador. Tamaño fijo.
    private static String[] inventario = new String[5];

    // Variable que guarda la posición actual del jugador
    private static int habitacionActual = 0; // Empezamos en la primera habitación

    // --- FIN DE LA DEFINICIÓN DE DATOS ---


    public static void main(String[] args) {
        // Puedes utilizar la clase MiEntradaSalida, que viviría en el paquete io
        Scanner sc = new Scanner(System.in);
        boolean jugando = true;
        int posicionActual = 1;
        System.out.println("'LA CURA'");
        System.out.println("------------------------------------------");

        System.out.println(descripcionJuego);

        System.out.println(habitaciones[posicionActual]);

        System.out.println("Las opciones son ayuda, mirar, inventario, \n" +
                "ir derecha, ir izquierda, coger [objeto] y salir");

        // TODO 2: Iniciar el bucle principal del juego (game loop)
        while (jugando) {

            // TODO 3: Leer el comando del usuario por teclado
            String comando = sc.nextLine() ;

            /*
            TODO 4: Crear un 'switch' o una estructura 'if-else if'
             para procesar el 'comando' del usuario.
             Debe gestionar como mínimo: "ayuda", "mirar", "inventario",
             "ir derecha", "ir izquierda", "coger [objeto]" y "salir".
             */
            switch (comando.toLowerCase()){
                case "ir derecha":
                    System.out.println(habitaciones[posicionActual + 1]);
                    posicionActual = posicionActual + 1;
                    break;
                case "ir izquierda":
                    System.out.println(habitaciones[posicionActual - 1]);
                    posicionActual = posicionActual - 1;
                    break;
                case "mirar":
                    System.out.println(habitaciones[habitacionActual]);
                    break;
                case "salir":
                    jugando = false;
                    break;
                case "ayuda":
                    System.out.println("Las opciones son ayuda, mirar, inventario,\n" +
                            "ir derecha, ir izquierda, coger [objeto] y salir \n" );
                    break;
                case "coger":
                    System.out.println();
                case "inventario":
                    ;
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