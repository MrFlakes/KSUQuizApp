package com.example.ksuquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.fragment.app.FragmentActivity


class Helper {
    /**
     * Helper class which contains a method to refresh the screen after a question is answered.
     * Taken from https://stackoverflow.com/questions/20702333/refresh-fragment-at-reload
     * Current Activity instance will go through its lifecycle to onDestroy() and a new instance then created after it.
     */
    @SuppressLint("NewApi")
    fun recreateActivityCompat(a: FragmentActivity?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            a?.recreate()
        } else {
            val intent = a?.intent
            intent?.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            a?.finish()
            a?.overridePendingTransition(0, 0)
            a?.startActivity(intent)
            a?.overridePendingTransition(0, 0)
        }
    }
}