package escalante.diana.peliculasapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import escalante.diana.peliculasapp.R
import escalante.diana.peliculasapp.modelos.Pelicula
import escalante.diana.peliculasapp.modelos.PeliculaRepositorio
import escalante.diana.peliculasapp.modelos.Usuario

class PeliculaViewModel(val repo: PeliculaRepositorio): ViewModel() {
    private val _peliculas = mutableStateOf<List<Pelicula>>(emptyList())
    val pelicula: State<List<Pelicula>> = _peliculas

    init {
        getPeliculas()
    }

    private fun getPeliculas() {
        _peliculas.value = repo.getPeliculas()
    }

    fun agregaPelicula(titulo: String, categoria: String, duracion: String, sinopsis: String, fotoUri: String?) {
        val nuevoId = _peliculas.value.size + 1
        val peli = Pelicula(nuevoId, titulo, categoria, duracion, sinopsis, R.drawable.photoshoot, fotoUri)
        repo.agregarPelicula(peli)

        _peliculas.value = repo.getPeliculas()
    }

    fun eliminaPelicula(pelicula: Pelicula) {
        repo.eliminarPelicula(pelicula)
        _peliculas.value = repo.getPeliculas()
    }
}