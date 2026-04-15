package escalante.diana.peliculasapp.vistas

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import escalante.diana.peliculasapp.modelos.Usuario
import escalante.diana.peliculasapp.viewmodels.UsuarioViewModel

@Composable
fun UsuarioScreen(viewModel: UsuarioViewModel) {
    val usuarios = viewModel.usuarios.value

    Scaffold (

    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(usuarios) { usuario ->
                UsuarioCard(usuario)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}

@Composable
fun UsuarioCard(usuario: Usuario) {

    Card (
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = usuario.nombre)
            Text(text = usuario.correo)
            Text(text = usuario.edad.toString())
        }

    }
}