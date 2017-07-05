package com.ilaps.androidtest.dagger.modules

import android.content.Context
import com.ilaps.androidtest.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ricar on 4/7/17.
 */
@Module
class ApplicationModule (private val app: App) {

    @Provides
    @Singleton
    fun application(): App {
        return app
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return app
    }
}