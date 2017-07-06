package com.ilaps.androidtest.dagger.modules

import com.ilaps.androidtest.dagger.scope.PerFragment
import com.ilaps.androidtest.signin.SignInFragment
import com.ilaps.androidtest.signup.SignUpFragment
import com.ilaps.androidtest.main.BluetoothDeviceListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ricar on 4/7/17.
 */
@Module
abstract class FragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeBluetoothDeviceListFragmentInjector(): BluetoothDeviceListFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeSignInFragmentInjector(): SignInFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeSignUpFragmentInjector(): SignUpFragment

}