package com.ilaps.androidtest.main.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ricar on 6/7/17.
 */
data class BluetoothDeviceModel(val deviceName:String, val deviceAddress:String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<BluetoothDeviceModel> = object : Parcelable.Creator<BluetoothDeviceModel> {
            override fun createFromParcel(source: Parcel): BluetoothDeviceModel = BluetoothDeviceModel(source)
            override fun newArray(size: Int): Array<BluetoothDeviceModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(deviceName)
        dest.writeString(deviceAddress)
    }
}