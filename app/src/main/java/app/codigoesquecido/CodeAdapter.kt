package app.codigoesquecido

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import app.codigoesquecido.databinding.ItemCodeBinding

class CodeAdapter : RecyclerView.Adapter<CodeAdapter.CodeViewHolder>() {
    private val codePieces: List<Pair<String, Int>> = listOf(
        Pair("parte1", R.drawable.symbols_parte1),
        Pair("parte2", R.drawable.symbols_parte2),
        Pair("parte3", R.drawable.symbols_parte3),
        Pair("parte4", R.drawable.symbols_parte4),
        Pair("parte5", R.drawable.symbols_parte5),
        Pair("parte6", R.drawable.symbols_parte6),
        Pair("parte7", R.drawable.symbols_parte7),
        Pair("parte8", R.drawable.symbols_parte8)
    )
    private val unlockedPieces = mutableSetOf<String>()

    fun unlockPiece(code: String) {
        if (codePieces.any { it.first == code }) {
            unlockedPieces.add(code)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeViewHolder {
        val binding = ItemCodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CodeViewHolder, position: Int) {
        val (code, vectorResId) = codePieces[position]
        val isUnlocked = unlockedPieces.contains(code)
        holder.bind(isUnlocked, vectorResId)
    }

    override fun getItemCount() = codePieces.size

    class CodeViewHolder(private val binding: ItemCodeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(isUnlocked: Boolean, vectorResId: Int) {
            if (isUnlocked) {
                binding.symbolImage.apply {
                    val vectorDrawable = AppCompatResources.getDrawable(context, vectorResId)
                    setImageDrawable(vectorDrawable)
                    visibility = View.VISIBLE
                }
                binding.lockIcon.visibility = View.GONE
            } else {
                binding.symbolImage.visibility = View.GONE
                binding.lockIcon.visibility = View.VISIBLE
            }
        }
    }
} 