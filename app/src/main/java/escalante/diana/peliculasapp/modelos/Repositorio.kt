package escalante.diana.peliculasapp.modelos

import escalante.diana.peliculasapp.R

class Repositorio {
    private val usuarios = mutableListOf(
        Usuario(1, "Diana", "diana@gmail.com", 22, R.drawable.avatarwoman),
        Usuario(2, "Libia", "libia@gmail.com", 21, R.drawable.bootstrap_person_circle),
        Usuario(3, "Carlos", "carlos@gmail.com", 70, R.drawable.avatarman),
        Usuario(4, "Kim", "kim@gmail.com", 20, R.drawable.avatargirl),
    )

    fun getUsuarios(): List<Usuario> {
        return usuarios.toList()
    }

    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun editarUsuario(usuario: Usuario) {
        val indice = usuarios.indexOfFirst { it.id == usuario.id }

        if (indice != -1) {
            usuarios[indice] = usuario
        }
    }
}