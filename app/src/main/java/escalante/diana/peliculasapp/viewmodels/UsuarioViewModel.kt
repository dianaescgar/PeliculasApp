package escalante.diana.peliculasapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import escalante.diana.peliculasapp.R
import escalante.diana.peliculasapp.modelos.Repositorio
import escalante.diana.peliculasapp.modelos.Usuario

class UsuarioViewModel(val repo: Repositorio): ViewModel() {

    private val _usuarios = mutableStateOf<List<Usuario>>(emptyList())
    val usuarios: State<List<Usuario>> = _usuarios

    init {
        getUsuarios()
    }

    private fun getUsuarios() {
        _usuarios.value = repo.getUsuarios()
    }

    fun agregaUsuario(nombre: String, correo: String, edad: Int, fotoUri: String?) {
        val nuevoId = _usuarios.value.size + 1
        val usu = Usuario(nuevoId, nombre, correo, edad, R.drawable.bootstrap_person_circle, fotoUri)
        repo.agregarUsuario(usu)

        _usuarios.value = repo.getUsuarios()
    }
}