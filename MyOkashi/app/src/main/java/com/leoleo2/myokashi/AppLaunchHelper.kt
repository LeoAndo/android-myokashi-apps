package com.leoleo2.myokashi

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.widget.Toast

class AppLaunchHelper constructor(private val context: Context) {
    fun launchBrowserApp(url: String) {
        try {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            i.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}