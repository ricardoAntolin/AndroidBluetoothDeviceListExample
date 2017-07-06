package com.ilaps.androidtest.main

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.ilaps.androidtest.R
import com.ilaps.androidtest.common.BaseFragment
import com.ilaps.androidtest.main.models.BluetoothDeviceModel
import com.ilaps.androidtest.navigation.Navigation
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_bluetooth_devices_list.*
import org.jetbrains.anko.onClick
import javax.inject.Inject

class BluetoothDeviceListFragment : BaseFragment(), BluetoothDeviceListContract.View {

    val devices: ArrayList<BluetoothDeviceModel> = ArrayList()
    val adapter = BluetoothDeviceListAdapter(devices, { onListItemClick(it) })
    private lateinit var presenter: BluetoothDeviceListContract.Presenter

    @Inject
    lateinit var navigation: Navigation

    companion object {
        fun newInstance(bundle: Bundle): BluetoothDeviceListFragment {
            val fragment = BluetoothDeviceListFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(): BluetoothDeviceListFragment {
            val fragment = BluetoothDeviceListFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_bluetooth_devices_list, container, false)
        AndroidInjection.inject(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcyDevicesList.setHasFixedSize(true)
        rcyDevicesList.layoutManager = LinearLayoutManager(activity)
        rcyDevicesList.adapter = adapter
        setListeners()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun setPresenter(presenter: BluetoothDeviceListContract.Presenter) {
        this.presenter = presenter
    }

    override fun deviceReceived(device: BluetoothDeviceModel) {
        adapter.add(device)
    }

    private fun setListeners() {
        btnSignOut.onClick {
            FirebaseAuth.getInstance().signOut()
            navigation.navigateToSignIn(activity)
        }
    }

    fun onListItemClick(device: BluetoothDeviceModel) {

    }

    override fun showError(exception: Exception) {

    }

    override fun clearAllDevices() {
        adapter.clearAllDevices()
    }

}
