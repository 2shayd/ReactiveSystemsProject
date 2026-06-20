package com.example.noimo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.noimo.data.local.NoiMoDatabase
import com.example.noimo.data.remote.SupabaseClientProvider
import com.example.noimo.data.remote.auth.SupabaseAuthDataSource
import com.example.noimo.data.remote.auth.SupabaseCurrentUserProvider
import com.example.noimo.data.remote.datasource.SupabaseCrashEventRemoteDataSource
import com.example.noimo.data.repository.RoomCrashEventRepository
import com.example.noimo.ui.navigation.NoiMoNavGraph
import com.example.noimo.ui.navigation.NoiMoBottomBar
import com.example.noimo.ui.theme.NoiMoTheme
import com.example.noimo.viewmodel.CrashEventViewModel
import com.example.noimo.viewmodel.CrashEventViewModelFactory
import com.example.noimo.viewmodel.SensorViewModel
import com.example.noimo.viewmodel.SensorViewModelFactory

//shayla worked on this
class MainActivity : ComponentActivity() {

    private lateinit var database: NoiMoDatabase
    private lateinit var sensorViewModel: SensorViewModel
    private lateinit var crashEventViewModel: CrashEventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            NoiMoDatabase::class.java,
            "noimo_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()

        val supabaseClient = SupabaseClientProvider.client

        val crashEventDao = database.crashEventDao()

        val crashEventRemoteDataSource = SupabaseCrashEventRemoteDataSource(
            client = supabaseClient
        )

        val currentUserProvider = SupabaseCurrentUserProvider(
            client = supabaseClient
        )

        val authDataSource = SupabaseAuthDataSource(
            client = supabaseClient
        )

        val crashEventRepository = RoomCrashEventRepository(
            dao = crashEventDao
        )

        sensorViewModel = ViewModelProvider(
            this,
            SensorViewModelFactory(
                crashEventDao = crashEventDao,
                crashEventRemoteDataSource = crashEventRemoteDataSource,
                currentUserProvider = currentUserProvider,
                authDataSource = authDataSource
            )
        )[SensorViewModel::class.java]

        crashEventViewModel = ViewModelProvider(
            this,
            CrashEventViewModelFactory(
                repository = crashEventRepository
            )
        )[CrashEventViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            NoiMoTheme {

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NoiMoBottomBar(navController)
                    }
                ) { innerPadding ->

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NoiMoNavGraph(navController = navController,
                            sensorViewModel = sensorViewModel,
                            crashEventViewModel = crashEventViewModel)
                    }
                }
            }
        }
    }
}