package com.ilaps.androidtest.main

import com.ilaps.androidtest.common.BasePresenter
import com.ilaps.androidtest.common.BaseView
import com.ilaps.androidtest.main.models.BluetoothDeviceModel

/**
 * Created by ricar on 4/7/17.
 */
interface BluetoothDeviceListContract {
    interface View: BaseView{
        fun setPresenter(presenter:Presenter)
        fun deviceReceived(device:BluetoothDeviceModel)
        fun clearAllDevices()
    }

    interface Presenter: BasePresenter{
        fun sendDevice(device:BluetoothDeviceModel)
    }
}