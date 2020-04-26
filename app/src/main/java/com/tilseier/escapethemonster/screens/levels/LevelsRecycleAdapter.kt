package com.tilseier.escapethemonster.screens.levels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.models.Level
import kotlinx.android.synthetic.main.level_item.view.*

class LevelsRecycleAdapter constructor(var levels: List<Level>) :
    RecyclerView.Adapter<LevelsRecycleAdapter.LevelItemViewHolder>() {

    interface LevelClickListener {
        fun onLevelClick(level: Level)
    }

    private var levelsListener: LevelClickListener? = null

    fun setOnLevelClickListener(levelsListener: LevelClickListener) {
        this.levelsListener = levelsListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LevelItemViewHolder {
        val layoutView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.level_item, parent, false)
        return LevelItemViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    override fun onBindViewHolder(holder: LevelItemViewHolder, position: Int) {
        val viewHolder: LevelItemViewHolder = holder
        viewHolder.bind(levels[viewHolder.adapterPosition])
        viewHolder.levelBox.setOnClickListener {
            levelsListener?.onLevelClick(levels[viewHolder.adapterPosition])
        }
    }

    class LevelItemViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val levelBox = itemView.level_box

        fun bind(images: Level) {
            itemView.tv_level.text = images.level.toString()
            itemView.tv_count_of_places.text = images.safePlaces.size.toString() + " Pl"
            itemView.tv_stars.text = images.stars.toString() + " Stars"
            itemView.tv_locked.text = if (images.locked) "LOCKED" else "OPENED"
        }

    }

}