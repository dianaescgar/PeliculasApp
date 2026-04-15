package escalante.diana.peliculasapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import escalante.diana.peliculasapp.modelos.Repositorio

class UsuarioViewModelFactory(private val repo: Repositorio): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//      return super.create(modelClass)
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            return UsuarioViewModel(repo) as T
        }
        throw IllegalArgumentException("Desconocido")
    }
}