package com.prafullkumar.foodtracker

import android.app.Application
import androidx.room.Room
import com.prafullkumar.foodtracker.data.Converter
import com.prafullkumar.foodtracker.data.db.FoodDao
import com.prafullkumar.foodtracker.data.db.FoodDatabase
import com.prafullkumar.foodtracker.ui.theme.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class FoodTracker : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                module {
                    single<FoodDatabase> {
                        Room.databaseBuilder(this@FoodTracker, FoodDatabase::class.java, "food_tracker_db")
                            .build()
                    }
                    single<FoodDao> {
                        get<FoodDatabase>().foodDao()
                    }
                    single {
                        Converter(get())
                    }
                    viewModel {
                        HomeViewModel(get())
                    }
                }
            )
        }
    }
}