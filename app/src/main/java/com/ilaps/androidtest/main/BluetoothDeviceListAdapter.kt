package com.ilaps.androidtest.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ilaps.androidtest.R
import com.ilaps.androidtest.main.models.BluetoothDeviceModel
import com.ilaps.androidtest.utils.inflate
import kotlinx.android.synthetic.main.item_bluetooth_device.view.*

/**
 * Created by ricar on 6/7/17.
 */
class BluetoothDeviceListAdapter(val devices: ArrayList<BluetoothDeviceModel>,
                                 val itemClick: (BluetoothDeviceModel) -> Unit):
        RecyclerView.Adapter<BluetoothDeviceListAdapter.DeviceViewHolder>() {

    fun add(device: BluetoothDeviceModel){
        devices.add(device)
        this.notifyDataSetChanged()
    }

    fun addAll(devices: ArrayList<BluetoothDeviceModel>){
        this.devices.addAll(devices)
        this.notifyDataSetChanged()
    }

    fun clearAllDevices(){
        devices.clear()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(parent.inflate(R.layout.item_bluetooth_device), itemClick)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder?, position: Int) {
        holder?.bindDevice(devices[position])
    }

    override fun getItemCount(): Int {
        return devices.count()
    }

    class DeviceViewHolder(var view: View, val itemClick: (BluetoothDeviceModel) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindDevice(device:BluetoothDeviceModel) = with(view){
            txtDeviceName.text = device.deviceName
            txtDeviceAddress.text = device.deviceAddress

            setOnClickListener { itemClick(device) }
        }
    }
}