package escalante.diana.peliculasapp.modelos

data class Pelicula(val id: Int, var titulo: String, var categoria: String, var duracion: String, var sinopsis: String, var foto: Int, var fotoUri: String? = null)
