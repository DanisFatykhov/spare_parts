package com.example.spare_parts.screens.main

import com.example.spare_parts.common.AuthManager
import com.example.spare_parts.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class MainViewModel(private val authManager: AuthManager, onFailureListener: OnFailureListener): BaseViewModel(onFailureListener), AuthManager by authManager


