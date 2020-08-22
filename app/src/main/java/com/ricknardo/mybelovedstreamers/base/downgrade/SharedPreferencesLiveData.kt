package com.ricknardo.mybelovedstreamers.base.downgrade

import android.content.SharedPreferences
import android.text.TextUtils

import androidx.lifecycle.LiveData

abstract class SharedPreferencesLiveData<T> internal constructor(protected val sharedPreferences: SharedPreferences, private val key: String,
                                                                 private val defaultValue: T) : LiveData<T>() {

    private val onSharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (TextUtils.equals(key, this@SharedPreferencesLiveData.key)) {
            // Se establece el nuevo valor en el LiveData
            value = getValueFromPreferences(key, defaultValue)
        }
    }

    // Método abstracto para obtener el valor de una determinada preferencia.
    // Su implementación concreta usará el método getTipo() adecuado del
    // objeto SharedPreferencia dependiendo de qué tipo concreto sea T.
    protected abstract fun getValueFromPreferences(key: String, defaultValue: T): T?

    // Cuando se pasa a tener algún observador se establece el valor. De esta manera
    // se entrega el valor directamente, sin que se tenga que producir un cambio.
    // Además se registra el listener de observación de cambios.
    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences(key, defaultValue)
        sharedPreferences.registerOnSharedPreferenceChangeListener(
                onSharedPreferenceChangeListener)
    }

    // Cuando se dejan de tener observadores, se quita el listener del registro
    override fun onInactive() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(
                onSharedPreferenceChangeListener)
        super.onInactive()
    }

}