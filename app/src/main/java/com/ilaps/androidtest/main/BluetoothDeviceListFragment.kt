package com.ilaps.androidtest.main

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.ilaps.androidtest.R
import com.ilaps.androidtest.navigation.Navigation
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_bluetooth_devices_list.*
import org.jetbrains.anko.onClick
import javax.inject.Inject

class BluetoothDeviceListFragment : Fragment() {
    @Inject
    lateinit var presenter: BluetoothDeviceListPresenter
    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_bluetooth_devices_list, container, false)
        AndroidInjection.inject(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        btnSignOut.onClick {
            FirebaseAuth.getInstance().signOut()
            navigation.navigateToSignIn(activity)
        }
    }
}
