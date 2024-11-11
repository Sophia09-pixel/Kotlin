package org.example.br.com.fiap.alugames.principal
import br.com.fiap.alugames.modelo.Gamer
import br.com.fiap.alugames.services.ConsumoApi
import org.example.br.com.fiap.alugames.modelo.Jogo
import transformarEmIdade
import java.util.*

fun main() {

    val entrada = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(entrada)

    println("Cadastro concluído com sucesso, Dados do gamer: ")
    println(gamer)
    println("\n Idade: " + gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite o codigo do jogo: ")
        val codigo = entrada.nextLine()

        val buscaApi = ConsumoApi()

        val meuInfoJogo = buscaApi.buscaJogo(codigo)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
        }

        resultado.onFailure {
            println("Erro ao procurar jogo, Tente outro ID")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val opcao = entrada.nextLine()
            if(opcao.equals("S", true)){
                println("Insira a descrição personalizada para o jogo")
                val descricao = entrada.nextLine()
                meuJogo?.descricao = descricao
            }else{
                meuJogo?.descricao = meuJogo?.titulo
            }
           gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar um novo jogo? S/N")
        val resp = entrada.nextLine()

    }while (resp.equals("S", true))

    println("Jogos buscados: ")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por titulo")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach {
        println("Titulo: "+ it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman",true) ?: false
    }
    println("\n Jogos filtrados: ")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = entrada.nextLine()

    if(opcao.equals("S", true)){
        println(gamer.jogosBuscados)
         println("\n Informe a posição do jogo que deseja excluir ")
        val posicao = entrada.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n Lista atualizada: ")
    println(gamer.jogosBuscados)
    println("\n Busca realizada com sucesso!")
}