class Butaca {
    // ATRIBUTOS DE CLASE
    var fila: Int
        private set
    var asiento: Int
        private set

    // CONSTRUCTORES DE CLASE
    constructor(linea: String) {
        val filaXAsiento: Array<String> = linea.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        fila = filaXAsiento[0].toInt()
        asiento = filaXAsiento[1].toInt()
    }

    constructor(fila: Int, asiento: Int) {
        this.fila = fila
        this.asiento = asiento
    }

    override fun toString(): String {
        return "$fila:$asiento"
    }
}