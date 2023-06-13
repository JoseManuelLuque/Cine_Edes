import java.util.*

class GestionCine(cine: Cine) {

    private var cine: Cine

    init {
        this.cine = cine
    }

    fun gestionarMenu() {
        var b: Butaca
        var respuesta: Int
        /*
        Mostrar menú principal
        1º Haciendo uso de la técnica de "divide y vencerás", aislamos la "pantalla" del menú principal
        en un método para así poder llamarlo cada vez que queramos. Como el método pertenece a esta clase,
        no hace falta inicializar un objeto para llamar al método
         */
        var opcionEscogida = 0
        cine.leerAsientos("C:\\Users\\josem\\Escritorio\\entradasVendidas.txt")
        do {
            try {
                opcionEscogida = menuPrincipal()
                when (opcionEscogida) {
                    1 -> {
                        println("MOSTRAR ASIENTOS")
                        cine.mostrarAsientos()
                    }

                    2 -> {
                        println("COMPRAR ENTRADA")
                        b = menuGestionButacas()
                        respuesta = cine.comprarEntrada(b)
                        if (respuesta == 0) menuAsientoIncorrecto(b) else if (respuesta == 1) menuCompraEfectuada(b) else menuErrorInesperado()
                    }

                    3 -> {
                        print("DEVOLVER ENTRADA\n")
                        b = menuGestionButacas()
                        respuesta = cine.devolverEntrada(b)
                        if (respuesta == 0) menuAsientoIncorrecto(b) else if (respuesta == 1) menuDevolucionEfectuada(b) else menuErrorInesperado()
                    }

                    4 -> {
                        print("SALIR\n")
                        cine.guardarVentas()
                    }

                    else -> System.err.print("OPCION NO SOPORTADA.\n")
                }
            } catch (e: Exception) {
                menuErrorInesperado()
            }
        } while (opcionEscogida != 4)
    }

    private fun menuErrorInesperado() {
        System.err.println("Error inesperado, se recomienda reiniciar la aplicación")
    }

    private fun menuCompraEfectuada(b: Butaca) {
        print("\t---------\n")
        System.out.format("%s\n", cine.nombreCine)
        System.out.format("Fila: %s\n", b.fila)
        System.out.format("Asiento %s\n", b.asiento)
        print("\t---------\n")
    }

    private fun menuDevolucionEfectuada(b: Butaca) {
        print("\t---------\n")
        System.out.format("%s\n", cine.nombreCine)
        println("Devolución efectuada")
        print("\t---------\n")
    }

    private fun menuAsientoIncorrecto(b: Butaca) {
        System.out.format("Asiento %s:%s no elegible, por favor elija otro", b.fila, b.asiento)
    }

    private fun menuPrincipal(): Int {
        // Mostramos las opciones del menú principal
        print("\t---------\n")
        print("1. Mostrar butacas\n")
        print("2. Comprar entrada\n")
        print("3. Devolver entrada\n")
        print("4. Salir\n")
        print("\t---------\n")

        /*
            1º Leemos lo que el usuario introduce por teclado (para ello utilizamos la clase Scanner)
            2º Instanciamos la clase Scanner para obtener un objeto
            3º Utilizamos el método .nextLine()
         */
        val sc = Scanner(System.`in`)
        print("Introduzca la opción deseada: ")
        val opcionTeclado = sc.nextLine()
        // Podemos pasar lo que el usuario ha escrito por teclado al formato que nosotros deseemos haciendo uso del casting.
        return opcionTeclado.trim { it <= ' ' }.toInt()
    }

    private fun menuGestionButacas(): Butaca {
        print("\t---------\n")
        print("Escoja la fila (0-9)\n")
        val sc = Scanner(System.`in`)
        var opcionTeclado = sc.nextLine()
        // Podemos pasar lo que el usuario ha escrito por teclado al formato que nosotros deseemos haciendo uso del casting.
        val fila = opcionTeclado.trim { it <= ' ' }.toInt()
        print("Escoja el asiento (0-14)\n")
        opcionTeclado = sc.nextLine()
        // Podemos pasar lo que el usuario ha escrito por teclado al formato que nosotros deseemos haciendo uso del casting.
        val asiento = opcionTeclado.trim { it <= ' ' }.toInt()
        return Butaca(fila, asiento)
    }
}