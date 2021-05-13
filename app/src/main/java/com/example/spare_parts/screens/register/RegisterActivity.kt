package com.example.spare_parts.screens.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spare_parts.R
import com.example.spare_parts.databinding.ActivityRegisterBinding
import com.example.spare_parts.databinding.FragmentRegisterEmailBinding
import com.example.spare_parts.databinding.FragmentRegisterNamepassBinding
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.common.coordinateBtnAndInputs
import com.example.spare_parts.screens.common.showToast
import com.example.spare_parts.screens.editprofile.EditProfileActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity(),
    EmailFragment.Listener,
    NamePassFragment.Listener {

    private val TAG = "RegisterActivity"

    private var mEmail: String? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatebase: DatabaseReference
    private lateinit var rootElement: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityRegisterBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        mAuth = FirebaseAuth.getInstance()
        mDatebase = FirebaseDatabase.getInstance().reference

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.frame_layout,
                EmailFragment()
            )
                .commit()
        }
    }

    override fun onNext(email: String) {
        if (email.isNotEmpty()) {
            mEmail = email
            mAuth.fetchSignInMethodsForEmail(email) { signInMethods ->
                if (signInMethods.isEmpty()) {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,
                        NamePassFragment()
                    )
                        .addToBackStack(null)
                        .commit()
                } else {
                    showToast(getString(R.string.this_email_already_exists))
                }
            }
        } else {
            showToast(getString(R.string.please_enter_your_email))
        }
    }

    override fun onRegister(name: String, password: String) {
        if (name.isNotEmpty() && password.isNotEmpty()) {        //количество символов дб больше 6-ти
            val cpv = findViewById<View>(R.id.cpv_register)
            val email = mEmail
            if (email != null) {
                if (password.length >= 6) {
                    cpv.visibility = View.VISIBLE
                    mAuth.createUserWithEmailAndPassword(email, password) {
                        mDatebase.createUser(it.user!!.uid, mkUser(name, email)) {
                            startEditProfileActivity()
                        }
                    }
                } else {
                    showToast(getString(R.string.please_your_password_more))
                }
            } else {
                showToast(getString(R.string.please_enter_your_email))
                supportFragmentManager.popBackStack()
            }
        } else {
            showToast(getString(R.string.please_enter_email_and_password))
        }
    }

    private fun startEditProfileActivity() {
        startActivity(Intent(this, EditProfileActivity::class.java))
        finish()
    }

    private fun mkUser(name: String, email: String): User {
        return User(username = name, email = email)
    }

    private fun FirebaseAuth.fetchSignInMethodsForEmail(
        email: String,
        onSuccess: (List<String>) -> Unit
    ) {
        fetchSignInMethodsForEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                onSuccess(it.result!!.signInMethods ?: emptyList<String>())
            } else {
                showToast(getString(R.string.error_enter_email))
            }
        }
    }

    private fun DatabaseReference.createUser(uid: String, user: User, onSuccess: () -> Unit) {
        val reference = mDatebase.child("users").child(uid)
        reference.setValue(user).addOnCompleteListener {
            if (it.isSuccessful) {
                onSuccess()
            } else {
                unknownRegisterError(it)
            }
        }
    }

    private fun FirebaseAuth.createUserWithEmailAndPassword(
        email: String, password: String,
        onSuccess: (AuthResult) -> Unit
    ) {
        createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    sendEmailVerification(it.result?.user!!)
                    onSuccess(it.result!!)
                } else {
                    unknownRegisterError(it)
                }
            }
    }

    private fun unknownRegisterError(it: Task<*>) {
        Log.e(TAG, "Ошибка создания профиля пользователя", it.exception)
        showToast(getString(R.string.error_please_try_again_later))
    }

    private fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener {
            if (it.isSuccessful) {
                showToast(getString(R.string.sent_to_message_your_email))
            } else {
                showToast(getString(R.string.error_sent_to_message_your_email))
            }
        }
    }
}

class EmailFragment : Fragment() {
    private lateinit var mListener: Listener
    private lateinit var rootElement: FragmentRegisterEmailBinding

    interface Listener {
        fun onNext(email: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootElement = FragmentRegisterEmailBinding.inflate(inflater)
        return rootElement.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(rootElement) {
            coordinateBtnAndInputs(nextBtn, emailInput)

            nextBtn.setOnClickListener {
                val email = emailInput.text.toString()
                mListener.onNext(email)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as Listener
    }
}

class NamePassFragment : Fragment() {
    private lateinit var mListener: Listener
    private lateinit var rootElement: FragmentRegisterNamepassBinding

    interface Listener {
        fun onRegister(name: String, password: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootElement = FragmentRegisterNamepassBinding.inflate(inflater)
        return rootElement.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(rootElement) {
            coordinateBtnAndInputs(registerBtn, usernameInput, passwordInput)

            registerBtn.setOnClickListener {
                val name = usernameInput.text.toString()
                val password = passwordInput.text.toString()
                mListener.onRegister(name, password)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as Listener
    }
}
