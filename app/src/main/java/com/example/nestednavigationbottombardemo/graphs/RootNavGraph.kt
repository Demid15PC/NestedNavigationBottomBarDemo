package com.example.nestednavigationbottombardemo.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nestednavigationbottombardemo.screens.home.HomeScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {

       // Создаём узел Хоста
       NavHost(
              navController = navController,
              startDestination = Graph.AUTHENTICATION,
              route = Graph.ROOT // это лишнее но пусть будет
       ) {
              // запуск функции
              authNavGraph(navController = navController)
              composable(route = Graph.HOME) { HomeScreen(/*тут ничего нет*/) }
       }
}

object Graph {
       const val ROOT = "root_graph"
       const val AUTHENTICATION = "auth_graph"
       const val HOME = "home_graph"
       const val DETAILS = "details_graph"
}