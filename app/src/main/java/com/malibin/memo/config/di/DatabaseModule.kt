package com.malibin.memo.config.di

import androidx.room.Room
import com.malibin.memo.db.AppDatabase
import com.malibin.memo.db.CategoryRepository
import com.malibin.memo.db.MemoRepository
import com.malibin.memo.db.source.local.CategoryLocalDataSource
import com.malibin.memo.db.source.local.MemoLocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appDataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database").build()
    }
}

val localDataSourceModule = module {
    single {
        CategoryLocalDataSource.getInstance(get(), get<AppDatabase>().categoryDao())
    }
    single {
        MemoLocalDataSource.getInstance(get(), get<AppDatabase>().memoDao())
    }
}

val repositoryModule = module {
    single { CategoryRepository(get()) }
    single { MemoRepository(get()) }
}