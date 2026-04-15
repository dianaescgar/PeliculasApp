package escalante.diana.peliculasapp.modelos

class Repositorio {
    fun getUsuarios(): List<Usuario> {
        return listOf (
            Usuario(1, "Diana", "diana@gmail.com", 22),
            Usuario(2, "Libia", "libia@gmail.com", 21),
            Usuario(3, "Carlos", "carlos@gmail.com", 70),
            Usuario(4, "Kim", "kim@gmail.com", 20),
        )
    }
}