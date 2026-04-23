package escalante.diana.peliculasapp.vistas

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import escalante.diana.peliculasapp.modelos.Pelicula
import escalante.diana.peliculasapp.viewmodels.PeliculaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeliculaScreen(viewModel: PeliculaViewModel) {
    val peliculas = viewModel.pelicula.value
    var mostrarDialogo by remember { mutableStateOf(false) }

    var peliculaEditar by remember { mutableStateOf<Pelicula?>(null) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Catálogo de Películas")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    mostrarDialogo = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(peliculas) { pelicula ->
                PeliculaCard(
                    pelicula = pelicula,
                    onDelete = { viewModel.eliminaPelicula(pelicula) },
                    onLongClick = {
                        peliculaEditar = pelicula
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        if(mostrarDialogo){
            AgregarPeliculaDialog(
                onDismiss = { mostrarDialogo = false },
                onConfirm = { titulo, categoria, duracion, sinopsis, fotoUri ->
                    viewModel.agregaPelicula(titulo, categoria, duracion, sinopsis, fotoUri)
                    mostrarDialogo = false
                }
            )
        }

        peliculaEditar?.let { pelicula ->
            EditarPeliculaDialog(
                pelicula = pelicula,
                onDismiss = { peliculaEditar = null },
                onConfirm = { id, titulo, categoria, duracion, sinopsis, fotoUri ->
                    viewModel.editarPelicula(id, titulo, categoria, duracion, sinopsis, fotoUri)
                    peliculaEditar = null
                }
            )
        }
    }
}

@Composable
fun PeliculaCard(pelicula: Pelicula, onDelete: () -> Unit, onLongClick: () -> Unit) {
    val context = LocalContext.current

    Card (
        modifier = Modifier.fillMaxSize()
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (pelicula.fotoUri != null) {
                AsyncImage(
                    model= pelicula.fotoUri,
                    contentDescription = "Poster Película",
                    modifier = Modifier.size(60.dp)
                )
            } else {
                Image(
                    painter = painterResource(pelicula.foto),
                    contentDescription = "Poster Película",
                    modifier = Modifier.size(60.dp)
                )
            }

            Text(text = pelicula.titulo)
            Text(text = pelicula.categoria)
            Text(text = pelicula.duracion)
            Text(text = pelicula.sinopsis)
        }
        IconButton(
            onClick = {
                onDelete()
                Toast.makeText(context, "Se eliminó ${pelicula.titulo}", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar"
            )
        }

    }
}

@Composable
fun AgregarPeliculaDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, String, String?) -> Unit
){
    var titulo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf<Uri?>(null) }

    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        uri: Uri? ->
        foto = uri
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {Text("Nueva Película")},
        text = {
            Column {

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.LightGray)
                        .clickable {
                            launcher.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (foto != null) {
                        AsyncImage(
                            model = foto,
                            contentDescription = "Poster Película",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Avatar",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it},
                    label = { Text("Título") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = categoria,
                    onValueChange = { categoria = it },
                    label = { Text("Categoría") },
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = duracion,
                    onValueChange = { duracion = it },
                    label = { Text("Duración") },
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = sinopsis,
                    onValueChange = { sinopsis = it },
                    label = { Text("Sinopsis") },
                    singleLine = true,
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (titulo.isNotBlank() && categoria.isNotBlank() && duracion.isNotBlank() && sinopsis.isNotBlank()) {
                        onConfirm(titulo, categoria, duracion, sinopsis, foto?.toString())
                    }
                }
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }


    )
}

@Composable
fun EditarPeliculaDialog(
    pelicula: Pelicula,
    onDismiss: () -> Unit,
    onConfirm: (Int, String, String, String, String, String?) -> Unit
){
    var titulo by remember { mutableStateOf(pelicula.titulo) }
    var categoria by remember { mutableStateOf(pelicula.categoria) }
    var duracion by remember { mutableStateOf(pelicula.duracion) }
    var sinopsis by remember { mutableStateOf(pelicula.sinopsis) }
    var foto by remember { mutableStateOf<Uri?>(pelicula.fotoUri?.let {Uri.parse(it)}) }

    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
            uri: Uri? ->
        foto = uri
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {Text("Editar Película")},
        text = {
            Column {

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.LightGray)
                        .clickable {
                            launcher.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (foto != null) {
                        AsyncImage(
                            model = foto,
                            contentDescription = "Poster Película",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Avatar",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it},
                    label = { Text("Título") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = categoria,
                    onValueChange = { categoria = it },
                    label = { Text("Categoría") },
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = duracion,
                    onValueChange = { duracion = it },
                    label = { Text("Duración") },
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = sinopsis,
                    onValueChange = { sinopsis = it },
                    label = { Text("Sinopsis") },
                    singleLine = true,
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (titulo.isNotBlank() && categoria.isNotBlank() && duracion.isNotBlank() && sinopsis.isNotBlank()) {
                        onConfirm(pelicula.id, titulo, categoria, duracion, sinopsis, foto?.toString())
                    }
                }
            ) {
                Text("Editar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }


    )
}