package com.sicredi.teste.centraldeeventos.presenter.ui

import android.app.Activity
import android.widget.TextView
import android.widget.Toast
import android.view.Gravity
import android.view.View
import com.sicredi.teste.centraldeeventos.R

object Alert {

    private const val SUCCESS = 1
    private const val WARNING = 2
    private const val FAILED = 3

    @JvmStatic
    fun success(context: Activity, msg: String) {
        mountView(context, msg, SUCCESS)
    }

    @JvmStatic
    fun warning(context: Activity, msg: String) {
        mountView(context, msg, WARNING)
    }

    @JvmStatic
    fun failed(context: Activity, msg: String) {
        mountView(context, msg, FAILED)
    }

    private fun mountView(activity: Activity, msg: String, viewType: Int) {
        val inflater = activity.layoutInflater
        var layout: View? = null
        var text: TextView? = null

        when (viewType) {
            SUCCESS -> {
                layout = inflater.inflate(
                    R.layout.toast_positive,
                    activity.findViewById(R.id.toast_positive_layout)
                )
                text = layout.findViewById(R.id.infoToastPositive)
            }

            WARNING -> {
                layout = inflater.inflate(
                    R.layout.toast_warning,
                    activity.findViewById(R.id.toast_warning_layout)
                )
                text = layout.findViewById(R.id.infoToastWarning)
            }

            FAILED -> {
                layout = inflater.inflate(
                    R.layout.toast_negative,
                    activity.findViewById(R.id.toast_negative_layout)
                )
                text = layout.findViewById(R.id.infoToastNegative)
            }
            else -> Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }

        layout?.let {
            text?.text = msg
            showMessage(activity, layout)
        }
    }

    private fun showMessage(context: Activity, layout: View) {
        val toast = Toast(context)
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }
}