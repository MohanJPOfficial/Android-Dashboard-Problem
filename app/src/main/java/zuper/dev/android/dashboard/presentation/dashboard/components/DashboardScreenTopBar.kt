package zuper.dev.android.dashboard.presentation.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R

@Composable
fun DashboardScreenTopBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(15.dp)
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.dashboard),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}