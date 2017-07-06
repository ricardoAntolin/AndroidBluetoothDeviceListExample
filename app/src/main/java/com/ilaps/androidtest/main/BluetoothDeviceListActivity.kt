package com.ilaps.androidtest.main

import android.Manifest
import android.app.Activity
import android.app.Fragment
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.ilaps.androidtest.R
import com.ilaps.androidtest.common.BaseActivity
import com.ilaps.androidtest.main.models.BluetoothDeviceModel
import com.ilaps.androidtest.navigation.navigateToFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.view_progress.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.onClick

class BluetoothDeviceListActivity : BaseActivity() {
    val btAdapter:BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    lateinit var presenter:BluetoothDeviceListPresenter
    val REQUEST_BLUETOOTH = 1234
    val REQUEST_BLUETOOTH_PERMISSIONS = 43221
    val SAVED_STATE_KEY = "saved state"
    val SAVED_FRAGMENT = "saved fragment"
    val SAVED_STATE_VALUE = 12345
    var savedFragment:BluetoothDeviceListFragment? = null
    lateinit var fragment: BluetoothDeviceListFragment

    private val bReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (BluetoothDevice.ACTION_FOUND == intent?.action) {
                val deviceReceived = intent
                        .getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                val device = BluetoothDeviceModel(deviceReceived.name ?: "NO NAMED", deviceReceived.address)
                presenter.sendDevice(device)
                progressLayout.visibility = View.GONE
            }
            if(BluetoothAdapter.ACTION_DISCOVERY_STARTED == intent?.action){
                progressLayout.visibility = View.VISIBLE
            }
            if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED == intent?.action){
                progressLayout.visibility = View.GONE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if(savedInstanceState != null){
            savedFragment = fragmentManager
                    .getFragment(savedInstanceState,SAVED_FRAGMENT) as BluetoothDeviceListFragment?
        }
        if(SAVED_STATE_VALUE != savedInstanceState?.getInt(SAVED_STATE_KEY)){
            checkIfBluetoothPermissionIsNeeded()
            checkBluetoothAdapterAvailability()
        }
        initFragment()
        initListener()
    }


    private fun initListener() {
        toolbar.btnScanDevices.onClick {
            reloadScan()
            startBluetoothScan()
        }
    }

    fun reloadScan(){
        presenter.view.clearAllDevices()
    }

    private fun getFragmentInstance():BluetoothDeviceListFragment{
        return savedFragment ?: BluetoothDeviceListFragment.newInstance()
    }

    private fun initFragment() {
        fragment = getFragmentInstance()
        presenter = BluetoothDeviceListPresenter(fragment)
        navigateToFragment(fragment, R.id.mainContainer, false)
    }

    private fun checkBluetoothAdapterAvailability() {
        if (btAdapter == null) alert {
            title(R.string.not_compatible_error)
            message(R.string.not_support_bluetooth_error_message)
        }else {
            enableBluetooth(btAdapter)
        }
    }

    private fun enableBluetooth(btAdapter:BluetoothAdapter){
        if(!btAdapter.isEnabled) {
            startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_BLUETOOTH)
        }else startBluetoothScan()
    }

    private fun initBluetoothBroadcastReceiver() {
        registerReceiver(bReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
        registerReceiver(bReceiver, IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED))
        registerReceiver(bReceiver, IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED))

    }

    fun startBluetoothScan(){
        initBluetoothBroadcastReceiver()
        val started = btAdapter?.startDiscovery()
        Log.d("BLUETOOTH", "DISCOVERY is $started")
    }

    private fun checkIfBluetoothPermissionIsNeeded() {
        var rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
        rc += ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
        rc += ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (rc != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.BLUETOOTH,
                            Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_BLUETOOTH_PERMISSIONS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(REQUEST_BLUETOOTH == requestCode && Activity.RESULT_OK == resultCode)
            startBluetoothScan()
        if(REQUEST_BLUETOOTH_PERMISSIONS == requestCode && Activity.RESULT_OK == resultCode)
            startBluetoothScan()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SAVED_STATE_KEY,SAVED_STATE_VALUE)
        fragmentManager.putFragment(outState,SAVED_FRAGMENT,fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(bReceiver)
        }catch (e: IllegalArgumentException) {

        }
    }
}
