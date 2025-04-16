package com.example.app

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import app.codigoesquecido.R

class QRCodeRevealManager(private val rootView: View) {
    private val partes = mapOf(
        "parte1" to R.drawable.symbols_parte1,
        "parte2" to R.drawable.symbols_parte2,
        "parte3" to R.drawable.symbols_parte3,
        "parte4" to R.drawable.symbols_parte4,
        "parte5" to R.drawable.symbols_parte5,
        "parte6" to R.drawable.symbols_parte6,
        "parte7" to R.drawable.symbols_parte7,
        "parte8" to R.drawable.symbols_parte8
    )

    init {
        // Inicializa todas as ImageViews com seus respectivos drawables
        partes.forEach { (parteId, drawableId) ->
            val imageView = rootView.findViewById<ImageView>(
                rootView.resources.getIdentifier(parteId, "id", rootView.context.packageName)
            )
            imageView.setImageResource(drawableId)
        }
    }

    fun revealParte(parteNumero: Int) {
        val parteId = "parte$parteNumero"
        val imageView = rootView.findViewById<ImageView>(
            rootView.resources.getIdentifier(parteId, "id", rootView.context.packageName)
        )
        
        // Revela a parte com uma animação de fade
        imageView.apply {
            alpha = 0f
            isVisible = true
            animate()
                .alpha(1f)
                .setDuration(500)
                .start()
        }
    }

    fun hideAllParts() {
        partes.keys.forEach { parteId ->
            val imageView = rootView.findViewById<ImageView>(
                rootView.resources.getIdentifier(parteId, "id", rootView.context.packageName)
            )
            imageView.isVisible = false
        }
    }
} 