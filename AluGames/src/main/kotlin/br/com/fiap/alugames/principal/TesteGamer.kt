import br.com.fiap.alugames.modelo.Gamer

fun main() {
    val gamer1 = Gamer("Sophia","sophia.dev09@gmail.com")

    println(gamer1)

    val gamer2 = Gamer("Luis","luis.dev@gmail.com", "10/10/2004", "minguel")

    println(gamer2)

    gamer1.let {
        it.dataNascimento = "09/03/2005"
        it.usuario = "CDNcooper"
    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "Sophi"
    println(gamer1)
}