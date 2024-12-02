package com.example.submissioncompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.submissioncompose.R
import com.example.submissioncompose.model.Article


@Composable
fun ItemMenuColumn(
    modifier: Modifier = Modifier,
    article: Article,
    navigateToDetail: (articleDetail: Article) -> Unit
){
    Row(
        modifier = modifier
            .clickable {
                navigateToDetail(article)
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageFromUrlWithGlide(url = article.urlToImage)
        Spacer(modifier = modifier.width(10.dp))
        Column(
            modifier = Modifier
        ) {
            Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = article.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageFromUrlWithGlide(
    modifier: Modifier = Modifier,
    url: String,
){
    GlideImage(
        model = url,
        contentDescription = "image",
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(100)),
        contentScale = ContentScale.None,
        loading = placeholder(R.drawable.placeholder_image_2),
        failure = placeholder(R.drawable.placeholder_image_2)
    )
}

//@Preview(showBackground = true)
//@Composable
//fun ItemColumnPreview() {
//    SubmissionComposeTheme {
//        ItemMenuColumn(data = mapOf(
//            "source" to mapOf("id" to null, "name" to "Mundodeportivo.com"),
//            "author" to "Anna M. Cordovilla Perez",
//            "title" to "Paula Coba, nuevo refuerzo para el Femenino",
//            "description" to "El Femenino del Espanyol se está reforzando con garantías y piezas importantes para afrontar la nueva campaña en la Liga F, la máxima categoría del fútbol femenino español.",
//            "url" to "https://www.mundodeportivo.com/futbol/rcd-espanyol/20240708/1002278148/paula-coba.html",
//            "urlToImage" to "https://www.mundodeportivo.com/files/og_thumbnail/uploads/2024/07/08/668ba805edd09.jpeg",
//            "publishedAt" to "2024-07-08T08:50:29Z",
//            "content" to "El Femenino del Espanyol se está reforzando con garantías y piezas importantes para afrontar la nueva campaña en la Liga F, la máxima categoría del fútbol femenino español. ..."
//        ))
//    }
//}