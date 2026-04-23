package com.movtery.zalithlauncher.feature.accounts

import android.annotation.SuppressLint
import android.app.Activity
import com.movtery.zalithlauncher.R
import com.movtery.zalithlauncher.setting.AllSettings
import com.movtery.zalithlauncher.ui.dialog.TipDialog
import com.movtery.zalithlauncher.utils.ZHTools
import com.movtery.zalithlauncher.utils.path.UrlManager

class LocalAccountUtils {
    companion object {
        /**
         * Метод изменен: проверка на Microsoft аккаунт удалена.
         * Теперь использование оффлайн/локальных аккаунтов разрешено всегда.
         */
        @JvmStatic
        fun checkUsageAllowed(listener: CheckResultListener) {
            // Мы сразу вызываем метод "разрешено", игнорируя наличие лицензии
            listener.onUsageAllowed()
        }

        @JvmStatic
        fun saveReminders(checked: Boolean) {
            AllSettings.localAccountReminders.put(!checked).save()
        }

        @JvmStatic
        @SuppressLint("InflateParams")
        fun openDialog(
            activity: Activity,
            confirmClickListener: TipDialog.OnConfirmClickListener?,
            message: String?,
            confirm: Int
        ) {
            // Используем apply для более чистого кода настройки диалога
            TipDialog.Builder(activity).apply {
                setTitle(R.string.generic_warning)
                setMessage(message)
                setWarning()
                setShowCheckBox(true)
                setCheckBox(R.string.generic_no_more_reminders)
                setConfirmClickListener(confirmClickListener)
                setConfirm(confirm)
                setCancelClickListener { ZHTools.openLink(activity, UrlManager.URL_MINECRAFT) }
                setCancel(R.string.account_purchase_minecraft_account)
            }.showDialog()
        }
    }

    interface CheckResultListener {
        fun onUsageAllowed()
        fun onUsageDenied()
    }
}
