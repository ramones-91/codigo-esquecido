package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.codigoesquecido.R

class CodePairActivity : AppCompatActivity() {
    private lateinit var qrCodeRevealManager: QRCodeRevealManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_code_reveal)

        qrCodeRevealManager = QRCodeRevealManager(findViewById(android.R.id.content))
    }

    // Chame este método quando um QR code for lido
    fun onQRCodeScanned(parteNumero: Int) {
        qrCodeRevealManager.revealParte(parteNumero)
    }

    // Chame este método para esconder todas as partes
    fun resetQRCode() {
        qrCodeRevealManager.hideAllParts()
    }
} 