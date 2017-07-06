package com.ilaps.androidtest.main

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ilaps.androidtest.main.models.BluetoothDeviceModel

/**
 * Created by ricar on 4/7/17.
 */

class BluetoothDeviceListPresenter(val view: BluetoothDeviceListContract.View)
    : BluetoothDeviceListContract.Presenter {
    init {
        view.setPresenter(this)
    }

    override fun sendDevice(device: BluetoothDeviceModel) {
        view.deviceReceived(device)
    }

}