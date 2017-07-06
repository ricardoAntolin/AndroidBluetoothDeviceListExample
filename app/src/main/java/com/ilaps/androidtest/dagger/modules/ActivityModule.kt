package com.ilaps.androidtest.dagger.modules

import com.ilaps.androidtest.dagger.scope.PerActivity
import com.ilaps.androidtest.signin.SignInActivity
import com.ilaps.androidtest.main.BluetoothDeviceListActivity
import com.ilaps.androidtest.signup.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ricar on 4/7/17.
 */
@Module
abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeBluetoothDeviceListActivityInjector(): BluetoothDeviceListActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeSignInActivityInjector(): SignInActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeSignUpActivityInjector(): SignUpActivity
}