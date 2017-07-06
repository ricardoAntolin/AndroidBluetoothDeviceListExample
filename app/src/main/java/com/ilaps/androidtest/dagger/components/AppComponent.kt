package com.ilaps.androidtest.dagger.components

import android.content.Context
import com.ilaps.androidtest.App
import com.ilaps.androidtest.dagger.modules.ActivityModule
import com.ilaps.androidtest.dagger.modules.ApplicationModule
import com.ilaps.androidtest.dagger.modules.FragmentModule
import com.ilaps.androidtest.navigation.Navigation
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by ricar on 4/7/17.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, ApplicationModule::class, ActivityModule::class, FragmentModule::class))
interface AppComponent {

    fun inject(app: App)

    val app: App

    fun context(): Context

    fun navigation(): Navigation
}