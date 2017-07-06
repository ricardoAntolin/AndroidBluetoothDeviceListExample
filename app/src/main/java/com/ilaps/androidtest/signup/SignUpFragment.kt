package com.ilaps.androidtest.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.ilaps.androidtest.R
import com.ilaps.androidtest.common.BaseFragment
import com.ilaps.androidtest.navigation.Navigation
import com.ilaps.androidtest.utils.validateIsBlank
import com.ilaps.androidtest.utils.validateIsEmail
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.sign_up_form.*
import org.jetbrains.anko.onClick
import javax.inject.Inject


class SignUpFragment: BaseFragment(), SignUpContract.View {

    @Inject
    lateinit var presenter: SignUpPresenter
    @Inject
    lateinit var navigation: Navigation

    companion object {
        fun newInstance(bundle: Bundle): SignUpFragment {
            val fragment = SignUpFragment()
            fragment.arguments = bundle
            return fragment
        }
        fun newInstance(): SignUpFragment {
            val fragment = SignUpFragment()
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        AndroidInjection.inject(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setListeners() {
        btnSignUp.onClick { attemptSignUp() }
    }

    private fun attemptSignUp() {
        if(isFormValid()){
            presenter.signUp(edtUsername.text.toString(), edtPassword.text.toString())
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

        if (edtPassword.text.toString().length < 6) {
            txtErrors.text = getString(R.string.password_input_too_short_error)
            return false
        }

        if(!edtUsername.validateIsEmail()) {
            txtErrors.text = getText(R.string.username_input_not_email_error)
            return false
        }

        if(edtPassword.text.toString() != edtConfirmPassword.text.toString()) {
            txtErrors.text = getText(R.string.passwords_inputs_not_match_error)
            return false
        }

        txtErrors.text = ""
        return true
    }

    override fun showError(exception: Exception) {
        if (exception is FirebaseAuthUserCollisionException) {
            txtErrors.text = getText(R.string.user_exist)
        }else {
            txtErrors.text = getText(R.string.server_communication_error)
        }
    }

    override fun userLogged(user: FirebaseUser) {
        navigation.navigateToBluetoothDeviceListActivity(activity)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

}