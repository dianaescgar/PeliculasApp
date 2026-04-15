package escalante.diana.peliculasapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
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
}