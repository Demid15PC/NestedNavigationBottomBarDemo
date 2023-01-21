package com.example.nestednavigationbottombardemo.screens.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nestednavigationbottombardemo.BottomBarScreen
import com.example.nestednavigationbottombardemo.graphs.HomeNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
       Scaffold(
              bottomBar = { BottomBar(navController = navController) }
       ) {
              HomeNavGraph(navController = navController)
       }
}

@Composable
fun BottomBar(navController: NavHostController) {
       val screens = listOf(
              BottomBarScreen.Home,
              BottomBarScreen.Profile,
              BottomBarScreen.Settings,
       )
       val navBackStackEntry by navController.currentBackStackEntryAsState()
       val currentDestination = navBackStackEntry?.destination

       if (screens.any { it.route == currentDestination?.route }) {
              BottomNavigation {
                     screens.forEach { screen ->
                            AddItem(
                                   screen = screen,
                                   currentDestination = currentDestination,
                                   navController = navController
                            )
                     }
              }
       }
}

@Composable
fun RowScope.AddItem(
       screen: BottomBarScreen,
       currentDestination: NavDestination?,
       navController: NavHostController
) {
       BottomNavigationItem(
              label = {
                     Text(text = screen.title)
              },
              icon = {
                     Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title
                     )
              },
              selected = currentDestination?.route == screen.route,
              unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
              onClick = {
                     if (currentDestination?.route != screen.route) {
                            navController.navigate(screen.route) {
                                   popUpTo(navController.graph.findStartDestination().id)
                                   launchSingleTop = true
                            }
                     }
              }
       )
}