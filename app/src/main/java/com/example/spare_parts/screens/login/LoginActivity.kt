package com.example.spare_parts.screens.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.spare_parts.R
import com.example.spare_parts.databinding.ActivityLoginBinding
import com.example.spare_parts.databinding.DialogRecoveryBinding
import com.example.spare_parts.screens.register.RegisterActivity
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.coordinateBtnAndInputs
import com.example.spare_parts.screens.main.MainActivity
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class LoginActivity : BaseActivity(), View.OnClickListener, KeyboardVisibilityEventListener {
    private lateinit var mViewModel: LoginViewModel
    private lateinit var rootElement: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityLoginBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        KeyboardVisibilityEvent.setEventListener(this, this)

        with(rootElement) {
            coordinateBtnAndInputs(loginBtn, emailInput, passwordInput)
            loginBtn.setOnClickListener(this@LoginActivity)
            registerBtn.setOnClickListener(this@LoginActivity)
        }

        mViewModel = initViewModel()
        mViewModel.goToMainScreen.observe(this, Observer { it?.let {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } })
        mViewModel.goToRegisterScreen.observe(this, Observer {
            startActivity(Intent(this, RegisterActivity::class.java))
        })

        rootElement.passwordRecovery.setOnClickListener {
            createRecoveryDialog()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_btn -> {
                with(rootElement) {
                    mViewModel.onLoginClick(
                        email = emailInput.text.toString(),
                        password = passwordInput.text.toString())
                    cpvLogin.visibility = View.VISIBLE
                }
            }

            R.id.register_btn -> {
                mViewModel.onRegisterClick()
            }

            R.id.login_const -> {

            }
        }
    }

    override fun onVisibilityChanged(isKeyboardOpen: Boolean) {
        rootElement.passwordRecovery.visibility = if (isKeyboardOpen) View.GONE else View.VISIBLE
    }

    private fun createRecoveryDialog() {
        val builder = AlertDialog.Builder(this)
        val rootDialogElement = DialogRecoveryBinding.inflate(layoutInflater)
        val view = rootDialogElement.root
        val dialog = builder.create()

        rootDialogElement.sendBtn.setOnClickListener {
            mViewModel.onSendBtnClick(email = rootDialogElement.emailInput.text.toString())
            dialog.dismiss()
        }

        builder.setView(view)
        builder.show()
    }
}