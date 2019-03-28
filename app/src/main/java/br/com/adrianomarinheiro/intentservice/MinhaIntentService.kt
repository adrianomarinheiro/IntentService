package br.com.adrianomarinheiro.intentservice

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.SystemClock
import android.text.format.DateFormat
import org.greenrobot.eventbus.EventBus




class MinhaIntentService : IntentService("MinhaIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        EventBus.getDefault().post(Status.LOADING)
        val msg = intent?.getStringExtra(PARAM_ENTRADA)
        SystemClock.sleep(3000)
        val resultado = "$msg ${DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis())}"
        val intent = Intent()
        intent.action = ACTION
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.putExtra(PARAM_SAIDA, resultado)
        sendBroadcast(intent)

        EventBus.getDefault().post(Status.SUCCESS)
    }

    companion object {
        val PARAM_ENTRADA = "entrada"
        val PARAM_SAIDA = "saída"
        val ACTION = "br.com.adrianomarinheiro.intent.action.RESPONSE"

    }


}
