import java.io.*

class Cine {

    private val FILAS = 10
    private val ASIENTOS = 15
    private var rutaArchivo: String? = null
    var nombreCine = "Cine Club Lumiere"
    private var cine = Array(FILAS) {arrayOfNulls<String>(ASIENTOS)}

    init {
        // Inicializamos el array cine vacío, es decir, con todos los asientos vacíos
        for (i in cine.indices) {
            for (j in cine[i].indices) {
                cine[i][j] = "__"
            }
        }
    }

    fun mostrarAsientos() {

        // Se puede hacer una atención a la diversidad en este método. A la hora de mostrar el menú,
        // un estudiante lo puede hacer con System.out.println normal... o con la función .format. La
        // cual es más compleja de entender y está relacionada con otros lenguajes de programación como C
        System.out.format("%5s", " ")
        for (i in 0 until ASIENTOS) {
            System.out.format("%4s", "A$i")
        }
        println()
        for (i in cine.indices) {
            System.out.format("F.%2s.", i)
            for (j in cine[i].indices) {
                System.out.format("%4s", cine[i][j])
            }
            println()
        }
    }

    fun actuaizarCine(rutaArchivo: String) {
        /*  A la hora de leer un archivo .txt. Podemos hacerlo de diversas formas.
            Lo podemos hacer usando la clase FileWriter, la cual tiene métodos para leer carácter por carácter.
            En este caso, como se nos indica que la información contenida en el .txt va a estar en líneas
            independientes, podemos usar la clase BufferedReader, la cual tiene métodos para leer línea por línea
        */

        // Declaramos los objetos y variables que vamos a usar.
        var archivo: File? = null
        var fr: FileReader? = null
        var br: BufferedReader? = null
        this.rutaArchivo = rutaArchivo

        // A la hora de leer archivos, se pueden producir errores, así que englobamos la lectura dentro
        // de un bloque try-catch
        try {
            // Abrimos el archivo estableciendo un flujo de lectura.
            archivo = File(this.rutaArchivo)
            fr = FileReader(archivo)
            br = BufferedReader(fr)

            // Leemos línea por línea
            var linea: String? = null
            while (br.readLine().also { linea = it } != null) {

                // En este punto debemos usar algún método de la clase String para separar la fila del asiento
                val b = Butaca(linea!!)
                cine[b.fila][b.asiento] = "XX"
            }
        } catch (e: IOException) {
            System.err.println("Error leyendo el archivo. Compruebe la validez de la uri")
            e.printStackTrace()
        } catch (e: Exception) {
            System.err.println("Error genérico. Se recomienda reiniciar la aplicación")
            e.printStackTrace()
        } finally {
            // Es buena práctica cerrar los flujos de lectura/escritura
            try {
                br?.close()
                fr?.close()
            } catch (e: Exception) {
                System.err.println("Error al cerrar los flujos de lectura")
                e.printStackTrace()
            }
        }
    }

    fun guardarVentas() {
        var archivo: File? = null
        var fw: FileWriter? = null
        var bw: BufferedWriter? = null
        try {
            archivo = File(rutaArchivo)
            fw = FileWriter(archivo)
            bw = BufferedWriter(fw)
            for (i in cine.indices) {
                for (j in cine[i].indices) {
                    if (cine[i][j].equals("XX", ignoreCase = true)) {
                        val b = Butaca(i, j)
                        bw.write(b.toString())
                        bw.newLine()
                    }
                }
            }
        } catch (e: IOException) {
            System.err.println("Error leyendo el archivo. Compruebe la validez de la uri")
            e.printStackTrace()
        } catch (e: Exception) {
            System.err.println("Error genérico. Se recomienda reiniciar la aplicación")
            e.printStackTrace()
        } finally {
            // Es buena práctica cerrar los flujos de lectura/escritura
            try {
                bw?.close()
                fw?.close()
            } catch (e: Exception) {
                System.err.println("Error al cerrar los flujos de escritura")
                e.printStackTrace()
            }
        }
    }

    fun comprarEntrada(butaca: Butaca?): Int {
        if (butaca == null) {
            // -1 indica error a la hora de realizar la compra/venta
            return -1
        }
        return if (cine[butaca.fila][butaca.asiento].equals("__", ignoreCase = true)) {
            cine[butaca.fila][butaca.asiento] = "XX"
            // 1 indica que el asiento está libre y que la compra ha sido efectuada
            1
        } else {
            // 0 indica que el asiento está ocupado
            0
        }
    }

    fun devolverEntrada(butaca: Butaca?): Int {
        if (butaca == null) {
            // -1 indica error a la hora de realizar la compra/venta
            return -1
        }
        return if (cine[butaca.fila][butaca.asiento].equals("XX", ignoreCase = true)) {
            cine[butaca.fila][butaca.asiento] = "__"
            // 1 indica que el asiento pasa de estar ocupado a estar libre y que la devolucion ha sido efectuada
            1
        } else {
            // 0 indica que el asiento está ya libre, y no se puede devolver algo que no está ocupado
            0
        }
    }

}