package com.pawel.presentation.helpers

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.pawel.common.networkErrorHandling.*
import com.pawel.worldline_android_technical_test.presentation.R

object ExtensionsErrors {

    fun Context.showAlertDialog(
        exception: MovieException,
        positiveButtonAction: AlertDialogButtonConfiguration? = null,
        negativeButtonAction: AlertDialogButtonConfiguration? = null
    ) {
        this.showAlertDialog(
            dialogTitle = exception.titleRes(),
            dialogMessage = exception.messageRes(),
            positiveButtonAction = positiveButtonAction,
            negativeButtonAction = negativeButtonAction
        )
    }

    private fun Context.showAlertDialog(
        @StringRes dialogTitle: Int? = null,
        @StringRes dialogMessage: Int? = null,
        positiveButtonAction: AlertDialogButtonConfiguration? = null,
        negativeButtonAction: AlertDialogButtonConfiguration? = null
    ) {
        // TODO apply custom style to AlertDialog, other than ThemeOverlay_AppCompat
        val builder = AlertDialog.Builder(this, R.style.ThemeOverlay_AppCompat)
        if (positiveButtonAction != null) {
            builder.setPositiveButton(
                positiveButtonAction.buttonLabel,
                positiveButtonAction.buttonAction
            )
        }
        if (negativeButtonAction != null) {
            builder.setNegativeButton(
                negativeButtonAction.buttonLabel,
                negativeButtonAction.buttonAction
            )
        }
        if (positiveButtonAction == null && negativeButtonAction == null) {
            builder.setPositiveButton(
                getString(R.string.ok),
                null
            )
        }
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun MovieException.titleRes(): Int {
        return when (this.movieErrorCode.code) {
            UNAUTHENTICATED_ERROR_CODE -> R.string.unauthenticated_error_code_title
            UNAUTHORIZED_ERROR_CODE -> R.string.unauthorized_error_code_title
            else -> R.string.generic_code_error_title
        }
    }

    private fun MovieException.messageRes(): Int {
        return when (this.movieErrorCode.code) {
            FUNCTIONNAL_ERROR_CODE -> R.string.functionnal_error_code_message
            UNAUTHENTICATED_ERROR_CODE -> R.string.unauthenticated_error_code_message
            UNAUTHORIZED_ERROR_CODE -> R.string.unauthorized_error_code_message
            else -> R.string.generic_code_error_message
        }
    }

    data class AlertDialogButtonConfiguration(
        @StringRes val buttonLabel: Int,
        val buttonAction: DialogInterface.OnClickListener
    )
}