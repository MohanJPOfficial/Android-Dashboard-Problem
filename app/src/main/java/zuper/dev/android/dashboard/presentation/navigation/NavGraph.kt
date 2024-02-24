package zuper.dev.android.dashboard.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import zuper.dev.android.dashboard.presentation.dashboard.DashBoardScreen
import zuper.dev.android.dashboard.presentation.jobs.JobsScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.DASHBOARD.routeName
    ) {
        composable(
            route = Screen.DASHBOARD.routeName
        ) {
            DashBoardScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.JOBS.routeName
        ) {
            JobsScreen(navHostController = navHostController)
        }
    }
}