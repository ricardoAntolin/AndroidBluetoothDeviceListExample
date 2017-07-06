package com.ilaps.androidtest.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseUser
import com.ilaps.androidtest.R
import com.ilaps.androidtest.common.BaseFragment
import com.ilaps.androidtest.navigation.Navigation
import com.ilaps.androidtest.utils.validateIsBlank
import com.ilaps.androidtest.utils.validateIsEmail
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.sign_in_form.*
import org.jetbrains.anko.onClick
import javax.inject.Inject

/**
 * Created by ricar on 4/7/17.
 */
class SignInFragment: BaseFragment(), SignInContract.View {

    @Inject
    lateinit var presenter: SignInPresenter
    @Inject
    lateinit var navigation: Navigation

    companion object {
        fun newInstance(bundle: Bundle): SignInFragment {
            val fragment = SignInFragment()
            fragment.arguments = bundle
            return fragment
        }
        fun newInstance(): SignInFragment {
            val fragment = SignInFragment()
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        AndroidInjection.inject(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setListeners() {
        btnSignIn.onClick { attemptSignIn() }
        btnSignUp.onClick { navigation.navigateToSignUp(activity) }
    }

    private fun attemptSignIn() {
        if(isFormValid()){
            presenter.signIn(edtUsername.text.toString(), edtPassword.text.toString())
        }
    }

    private fun isFormValid(): Boolean {
        if (edtUsername.validateIsBlank()) {
            txtErrors.text = getText(R.string.username_input_not_blank_error)
            return false
        }

        if (edtPassword.validateIsBlank()) {
            txtErrors.text = getText(R.string.password_input_not_blank_error)
            return false
        }

        if(!edtUsername.validateIsEmail()) {
            txtErrors.text = getText(R.string.username_input_not_email_error)
            return false
        }

        txtErrors.text = ""
        return true
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun userLogged(user: FirebaseUser) {
        navigation.navigateToBluetoothDeviceListActivity(activity)
    }

    override fun userLoginIncorrect() {
        txtErrors.text = getText(R.string.username_password_not_match)
    }

    override fun showError(exception: Exception) {
        txtErrors.text = getText(R.string.server_communication_error)
    }
}