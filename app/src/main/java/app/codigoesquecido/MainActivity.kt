package app.codigoesquecido

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import app.codigoesquecido.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var codeAdapter: CodeAdapter
    private val correctAnswer = "Filipenses 4:6-7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        codeAdapter = CodeAdapter()
        binding.codeGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 4)
            adapter = codeAdapter
        }
        // Desbloqueie os códigos iniciais
        codeAdapter.unlockPiece("parte1")
        codeAdapter.unlockPiece("parte3")
    }

    private fun setupClickListeners() {
        binding.scanButton.setOnClickListener {
            startActivity(Intent(this, QRScannerActivity::class.java))
        }

        binding.verifyButton.setOnClickListener {
            checkAnswer()
        }

        binding.helpButton.setOnClickListener {
            Toast.makeText(this, R.string.help_message, Toast.LENGTH_LONG).show()
        }
    }

    private fun checkAnswer() {
        val answer = binding.answerInput.text.toString()
        if (answer.equals(correctAnswer, ignoreCase = true)) {
            Toast.makeText(this, R.string.code_revealed, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val scannedCode = data.getStringExtra("qr_code")
            if (scannedCode != null) {
                codeAdapter.unlockPiece(scannedCode)
            }
        }
    }
} 