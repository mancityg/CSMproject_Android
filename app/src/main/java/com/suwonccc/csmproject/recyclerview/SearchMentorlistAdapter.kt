package com.suwonccc.csmproject.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.suwonccc.csmproject.R
import de.hdodenhof.circleimageview.CircleImageView

class SearchMentorlistAdapter(val context: Context, val mentorlist: ArrayList<Mentor>): RecyclerView.Adapter<SearchMentorlistAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.mentorlist_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return mentorlist.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(mentorlist[position], context)

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        val mentorImg = itemView?.findViewById<CircleImageView>(R.id.profile_photo)
        val mentorname = itemView?.findViewById<AppCompatTextView>(R.id.item_name)
        val mentortag1 = itemView?.findViewById<AppCompatTextView>(R.id.item_tag1)

        fun bind (mentor: Mentor, context: Context){
            val resourceId = context.resources.getIdentifier(mentor.thumb, "drawable", context.packageName)
            var basic : String = ""
            basic += mentor.campus
            basic += "  /  "
            basic += mentor.dept
            mentorname?.text = mentor.name
            mentorImg?.setImageResource(resourceId)
            mentortag1?.text = basic
        }
    }


}
