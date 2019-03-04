package com.example.pritam.kotlinretrofit.adapter

import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pritam.kotlinretrofit.R
import com.example.pritam.kotlinretrofit.adapter.PersonListAdapter.ViewHolder
import com.example.pritam.kotlinretrofit.model.Person

class PersonListAdapter(context: Context, modelList:List<Person>): RecyclerView.Adapter<ViewHolder>() {

    private val TAG = PersonListAdapter::class.java!!.simpleName
    private var context: Context? = null
    private var modelList:List<Person> = emptyList()

    init{
        this.context=context
        this.modelList = modelList
    }
    override fun getItemCount():Int {
        return try {
            modelList.size
        } catch (e:Exception) {
            Log.d(TAG, "Exception " + e.message)
            0
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.layout_item_list, viewGroup,
            false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(@NonNull holder:ViewHolder, position:Int) {
        holder.name.text = modelList[position].name
        holder.email.text = modelList[position].email
        holder.contact.text = modelList[position].contact
        holder.age.text = modelList[position].age
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.findViewById(R.id.name)
        internal var email: TextView = itemView.findViewById(R.id.email)
        internal var contact: TextView = itemView.findViewById(R.id.contact)
        internal var age: TextView = itemView.findViewById(R.id.age)
    }
}