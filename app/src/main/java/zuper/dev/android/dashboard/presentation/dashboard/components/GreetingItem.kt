package zuper.dev.android.dashboard.presentation.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R

@Composable
fun GreetingItem(
    modifier: Modifier = Modifier,
    todayDate: String
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(4.dp))
            .border(
                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(4.dp),
            )
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Column {
            Text(
                text = "Hello, Groot!",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = todayDate,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.outline
            )
        }

        Image(
            painter = painterResource(id = R.drawable.groot),
            contentDescription = stringResource(R.string.profile_picture),
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(3.dp))
                .align(Alignment.CenterVertically)

        )
    }
}