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


                    "SALA DE DESCANSO:Una cafetera queda encendida, burbujeando un café quemado con olor rancio. Sillas volcadas y bandejas con comida a medio comer sugieren una interrupción brusca.\n" +
                            " Hay casilleros abiertos: dentro hay pertenencias personales, fotos de familia y tarjetas de acceso.\n" +
                            " En una mesa, un móvil vibra sin parar, mostrando una notificación repetida:\n" +
                            "\n" + "“Protocolo interno de emergencia activado. No abandonar el edificio.”",//Posición 0

                    "SALA DE MANTENIMIENTO:Herramientas desparramadas cubren el suelo. Linternas, cinta aislante y un carrito de herramientas bloquean parcialmente una puerta lateral.\n" +
                            " El aire huele a aceite y metal. Un tablero eléctrico parpadea con advertencias rojas:\n" +
                            "\n" + "“Fallo en el suministro de energía — plantas inferiores comprometidas.”\n" +
                            "Cerca del tablero, hay marcas de arañazos profundas, como si alguien hubiera intentado entrar… o salir", //Posición 1

                    "LABORATORIO DE INVESTIGACIÓN:la puerta está trabada a medias, dejando un espacio estrecho para entrar. Luces rojas pulsantes bañan la sala. Tubos de ensayo rotos y frascos marcados con símbolos biológicos cubren las mesas. En el fondo, una cámara de contención de vidrio está agrietada desde dentro.\n" +
                            "Un monitor reproduce una grabación detenida en una frase:\n" +
                            "\n" + "“¡Aún no está listo para la exposición humana!”", //Posición 2

                    "CENTRO DE CONTROL:pantallas mostrando cámaras del edificio dan la impresión de que alguien aún vigila. En algunas se ven pasillos vacíos, pero en otras se distinguen siluetas moviéndose lento y errático.\n" +
                            "Un panel abierto muestra cables quemados. Sobre el escritorio hay un registro con la última entrada:\n" +
                            "\n" + "“Bloqueen todos los accesos. No dejen salir nada.”", //Posición 3

                    "RECEPCIÓN:estas en la recepción inicial de la corporación miravent.Un gran mostrador de metal domina la entrada, cubierto de polvo y papeles amarillentos. \n" +
                            "El logotipo de la corporación —medio borrado— adorna la pared del fondo, con luces que parpadean débilmente.\n" +
                            "El suelo está lleno de huellas secas y trozos de cristales rotos; una silla caída sugiere que alguien salió con prisa. \n" +
                            "En una esquina, una planta marchita aún permanece en su maceta, junto a una pantalla que muestra el mensaje: “MANTÉNGASE TRANQUILO. LA SITUACIÓN ESTÁ BAJO CONTROL.\n", // Posición 4

                    "SALA DE DOCUMENTACIÓN:estantes metálicos hasta el techo, llenos de carpetas con sellos: Clasificado / Confidencial / Proyecto AURORA. El suelo está tapizado de papeles, como si alguien buscara algo con desesperación.\n" +
                            "Una carpeta abierta en una mesa muestra un gráfico con curvas ascendentes y una nota adherida:\n" +
                            "\n" + "“La tasa de infección es exponencial. No podemos controlarlo.”",//Posición 5

                    "LABORATORIO QUÍMICO:olor penetrante, vidrios rotos y líquidos reaccionando todavía sobre una superficie metálica. La campana extractora sigue funcionando, absorbiendo un humo blanco.\n" +
                            "En la pizarra hay ecuaciones químicas y una frase escrita con trazos frenéticos:\n" +
                            "\n" + "“Deja de buscar cura. Evalúa contención.”",//Posición 6

                    "SALA DE SERVICIO:la temperatura aquí es extrañamente baja. El zumbido constante de las máquinas genera un ambiente sofocante. Los servidores parpadean como estrellas artificiales.\n" +
                            "Una terminal desbloqueada muestra un archivo:\n" +
                            "\n" + "“PROTOCOLO FASE FINAL – Autorización Nivel 7 requerida.”\n" +
                            "La barra para ingresar contraseña está manchada de sangre seca.",//Posición 7

                    "ALMACÉN DE PROVISIONES:cajas apiladas por todas partes, muchas con el logo corporativo y la frase Distribución Global. Algunas contienen trajes de bioseguridad, otras jeringas vacías.\n" +
                            "Una puerta de carga industrial está atrancada con un carro elevador.\n" +
                            "Sobre una caja abierta hay un mapa del edificio, marcado con un fluorescente:\n" +
                            "\n" + "“RUTA ESCAPE — SOLO PERSONAL AUTORIZADO”",//Posición 8

    };

    // Los objetos que hay en cada habitación.
    public static String[][] objetosMapa = {
            {"llave inglesa", null},           // Objetos en Habitación 0
            {null, null},           // Objetos en Habitación 1
            {"taza", null},         // Objetos en Habitación 2
            {null, null},           // Objetos en Habitación 3
            {null, null},           // Objetos en Habitación 4
            {null, null},           // Objetos en habitación 5
            {null, null},           // Objetos en Habitación 6
            {null, null},           // Objetos en Habitación 7
            {null, null},           // Objetos en Habitación 8

    };

    // El inventario del jugador. Tamaño fijo.
    private static String[] inventario = new String[5];

    private static void inventarioActual() {
        int contador = 0;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) {
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("El inventario está vacío");
        } else {
            for (int i = 0; i < inventario.length; i++) {
                if (inventario[i] != null) {
                    System.out.println(i+ 1 + ") " + inventario[i]);
                }
            }
        }
    }

// Variable que guarda la posición actual del jugador
private static int habitacionActual = 4; // Empezamos en la primera habitación

/**
 * Metodo para ir a la derecha
 */
public static void derecha() {
    if (habitacionActual == habitaciones.length - 1) {
        System.out.println("No hay mas habitaciones a la derecha. Solo puedes ir a la izquierda");
    } else {
        System.out.println(habitaciones[habitacionActual + 1]);
        habitacionActual = habitacionActual + 1;
    }

    }

/**
 * Metodo para ir a la izquierda
 */
public static void izquierda() {
    if (habitacionActual == habitaciones.length - 1) {
        System.out.println("No hay mas habitaciones a la izquierda. Solo puedes ir a la derecha");
    } else {
        System.out.println(habitaciones[habitacionActual - 1]);
        habitacionActual = habitacionActual - 1;
    }
}
    /**
     * Metodo para ir a la izquierda
     */
    public static void  izquierda(){
            if(habitacionActual == habitaciones.length - 1) {
                System.out.println("No hay mas habitaciones a la izquierda. Solo puedes ir a la derecha");
            }else {
                System.out.println(habitaciones[habitacionActual - 1]);
                habitacionActual = habitacionActual - 1;
            }
    }

/*+
 * Metodo para coger objetos
 */
public static void cogerObjetos() {
    Scanner sc = new Scanner(System.in);
    //llamar a listar objetos
    int numeroObjetos = listarObjetos();

    if (numeroObjetos == 0) { //Si no hay objetos en la sala decirlo y sacarlo de aqui
        System.out.println("No hay objetos aquí");
        return;
    }
    //almacenar el objeto que el jugador quiere coger
    System.out.println("¿Que objeto quieres coger?");
    String objeto = sc.nextLine();
    for (int i = 0; i < objetosMapa[habitacionActual].length; i++) {
        if (objetosMapa[habitacionActual][i] != null) {
            if (objetosMapa[habitacionActual][i].equalsIgnoreCase(objeto)) {
                if (guardarObjeto(objeto)) {
                    System.out.println("Has cogido " + objeto);
                    objetosMapa[habitacionActual][i] = null; //Eliminamos el objeto del mapa
                }

                return;
            } else System.out.println("No se ha encontrado ese objeto.");
        } else { //Miramos si el objeto no esta en la habitación
            System.out.println("Ese objeto no esta en esta habitación");
            return;
        }
    }

}

private static int listarObjetos() {
    int contador = 0;
    for (int i = 0; i < objetosMapa[habitacionActual].length; i++) {
        if (objetosMapa[habitacionActual][i] != null) {
            System.out.println(objetosMapa[habitacionActual][i]);
            contador++;
        }
    }

    return contador;
}

private static boolean guardarObjeto(String objeto) {
    int ocupado = 0;
    for (int i = 0; i < inventario.length; i++) {
        if (inventario[i] != null) ocupado++;
    }

    for (int i = 0; i < inventario.length; i++) {
        if (inventario[i] == null) {
            inventario[i] = objeto;
            return true;
        } else if (inventario[i] != null) {
            if (ocupado == inventario.length) {
                System.out.println("No tienes espacio en el inventario");
            }
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

        System.out.println(descripcionJuego);

        System.out.println(habitaciones[habitacionActual]);

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
                derecha();
                break;
            case "ir izquierda":
                izquierda();
                break;
            case "mirar":
                System.out.println(habitaciones[habitacionActual]);
                listarObjetos();
                break;
            case "salir":
                jugando = false;
                break;
            case "ayuda":
                System.out.println("Las opciones son ayuda, mirar, inventario,\n" +
                        "ir derecha, ir izquierda, coger [objeto] y salir \n");
                break;
            case "coger":
                cogerObjetos();
                break;
            case "inventario":
                inventarioActual();
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