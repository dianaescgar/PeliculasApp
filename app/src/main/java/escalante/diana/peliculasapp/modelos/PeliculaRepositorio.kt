package escalante.diana.peliculasapp.modelos

import escalante.diana.peliculasapp.R

class PeliculaRepositorio {
    private val peliculas = mutableListOf(
        Pelicula(1, "Kpop Demon Hunters", "Acción", "1h 36min", "Un grupo de idols del K-pop lleva una doble vida como cazadoras de demonios, protegiendo al mundo de fuerzas oscuras mientras equilibran su carrera musical y sus identidades secretas.", R.drawable.kpopdemonhunters),
        Pelicula(2, "The Hunger Games: The Ballad of Songbirds and Snakes", "Ciencia ficción", "2h 37min", "Años antes de convertirse en el presidente de Panem, Coriolanus Snow es un joven ambicioso asignado como mentor en los décimos Juegos del Hambre, donde su relación con una tributo del Distrito 12 cambiará su destino para siempre.", R.drawable.photoshoot),
        Pelicula(3, "Minions", "Comedia", "1h 31min", "Los Minions, criaturas amarillas dedicadas a servir al villano más malvado, viajan por el mundo en busca de un nuevo líder, encontrando finalmente a Scarlet Overkill, quien planea dominar el mundo.", R.drawable.photoshoot),
        Pelicula(4, "Iron Man", "Acción", "2h 6min", "El multimillonario e ingeniero Tony Stark construye una armadura avanzada para escapar del cautiverio y luego decide usar su tecnología para convertirse en el héroe conocido como Iron Man.", R.drawable.photoshoot),
        Pelicula(5, "Challengers", "Drama", "2h 11min", "Una extenista convertida en entrenadora se ve atrapada en un triángulo amoroso entre su esposo y su antiguo novio, mientras compiten en un torneo que sacará a la luz rivalidades, tensiones y emociones del pasado.", R.drawable.photoshoot)
    )
    fun getPeliculas(): List<Pelicula> {
        return peliculas.toList()
    }

    fun agregarPelicula(pelicula: Pelicula) {
        peliculas.add(pelicula)
    }

    fun eliminarPelicula(pelicula: Pelicula) {
        peliculas.remove(pelicula)
    }

    fun editarPelicula(pelicula: Pelicula) {
        val indice = peliculas.indexOfFirst { it.id == pelicula.id }

        if (indice != -1) {
            peliculas[indice] = pelicula
        }
    }
}