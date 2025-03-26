enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String, val email: String) {
    override fun toString(): String {
        return "Aluno: $nome (${email})"
    }
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel = Nivel.BASICO) {
    override fun toString(): String {
        return "$nome (${duracao}min - Nível: ${nivel})"
    }
}

data class Formacao(val nome: String, val nivel: Nivel, val conteudos: List<ConteudoEducacional>) {
    private val inscritos = mutableListOf<Usuario>()
    
    val cargaHorariaTotal: Int
        get() = conteudos.sumOf { it.duracao }
    
    fun matricular(usuario: Usuario) {
        if (usuario !in inscritos) {
            inscritos.add(usuario)
            println("${usuario.nome} matriculado com sucesso na formação $nome!")
        } else {
            println("${usuario.nome} já está matriculado nesta formação.")
        }
    }
    
    fun listarInscritos() {
        println("\nInscritos na formação $nome (${inscritos.size} alunos):")
        inscritos.forEachIndexed { index, usuario ->
            println("${index + 1}. ${usuario.nome}")
        }
    }
    
    fun exibirDetalhes() {
        println("\nDetalhes da Formação:")
        println("Nome: $nome")
        println("Nível: $nivel")
        println("Carga Horária Total: ${cargaHorariaTotal} minutos")
        println("Conteúdos Educacionais:")
        conteudos.forEachIndexed { index, conteudo ->
            println("  ${index + 1}. $conteudo")
        }
    }
}

fun main() {
    // Criando conteúdos educacionais
    val conteudoKotlin = ConteudoEducacional("Introdução ao Kotlin", 120, Nivel.BASICO)
    val conteudoPOO = ConteudoEducacional("Programação Orientada a Objetos", 180, Nivel.INTERMEDIARIO)
    val conteudoDS = ConteudoEducacional("Estruturas de Dados em Kotlin", 240, Nivel.DIFICIL)
    
    // Criando uma formação
    val formacaoKotlin = Formacao(
        "Formação Kotlin Developer",
        Nivel.INTERMEDIARIO,
        listOf(conteudoKotlin, conteudoPOO, conteudoDS)
    )
    
    // Criando usuários
    val aluno1 = Usuario("João Rodrigues", "joaorodrigues@email.com")
    val aluno2 = Usuario("Venilton FalvoJr", "falvojr@email.com")
    val aluno3 = Usuario("Fulano de Tal", "fulanodt@email.com")
    
    // Matriculando alunos
    formacaoKotlin.matricular(aluno1)
    formacaoKotlin.matricular(aluno2)
    formacaoKotlin.matricular(aluno3)
    formacaoKotlin.matricular(aluno1) // Tentativa de matrícula duplicada
    
    // Exibindo detalhes da formação
    formacaoKotlin.exibirDetalhes()
    
    // Listando inscritos
    formacaoKotlin.listarInscritos()
    
    // Criando outra formação
    val conteudoWeb1 = ConteudoEducacional("HTML e CSS", 90)
    val conteudoWeb2 = ConteudoEducacional("JavaScript Moderno", 150, Nivel.INTERMEDIARIO)
    
    val formacaoWeb = Formacao(
        "Formação Web Developer",
        Nivel.BASICO,
        listOf(conteudoWeb1, conteudoWeb2)
    )
    
    // Matriculando alunos na nova formação
    formacaoWeb.matricular(aluno2)
    formacaoWeb.matricular(aluno3)
    
    // Exibindo detalhes da segunda formação
    formacaoWeb.exibirDetalhes()
    formacaoWeb.listarInscritos()
}
