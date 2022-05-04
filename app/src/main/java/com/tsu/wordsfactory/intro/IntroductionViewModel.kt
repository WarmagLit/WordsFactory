package com.tsu.wordsfactory.intro

import androidx.lifecycle.ViewModel
import com.tsu.wordsfactory.utils.Preferences

class IntroductionViewModel(private val prefs: Preferences): ViewModel() {

    fun isFirstAppStart(): Boolean {
        return prefs.isFirstRun()
    }
}