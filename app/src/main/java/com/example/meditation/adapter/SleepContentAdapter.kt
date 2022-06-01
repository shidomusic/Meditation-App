package com.example.meditation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meditation.R
import com.example.meditation.model.Content
import kotlinx.coroutines.withContext

class SleepContentAdapter(private var contentList: ArrayList<Content>, var onClick: OnItemClickListerner) : RecyclerView.Adapter<SleepContentAdapter.ContentViewHolder>() {

    interface OnItemClickListerner{
        fun onClickItem(content: Content, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_sleep_content, parent, false)
        return ContentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.initialize(contentList[position], onClick, holder.imgContent.context)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    class ContentViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtName : TextView = itemView.findViewById(R.id.tv_name)
        val txtMode : TextView = itemView.findViewById(R.id.tv_mode)
        val txtDuration : TextView = itemView.findViewById(R.id.tv_duration)
        val imgContent : ImageView = itemView.findViewById(R.id.img_content)

        fun initialize(content: Content, action: OnItemClickListerner, context: Context){
            txtName.text = content.name
            txtMode.text = content.mode!!.uppercase()
            txtDuration.text = content.duration.toString() + " PHÚT"
            Glide.with(context).load(content.img).into(imgContent)

            itemView.setOnClickListener {
                action.onClickItem(content, adapterPosition)
            }
        }
    }

}