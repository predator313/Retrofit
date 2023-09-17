package com.aamirashraf.retrofit_complete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aamirashraf.retrofit_complete.databinding.ItemTodoBinding
import java.util.Objects

class TodoAdapter:Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(val binding: ItemTodoBinding):ViewHolder(binding.root)
    private val diffCallback=object :DiffUtil.ItemCallback<TodosItem>(){
        override fun areItemsTheSame(oldItem: TodosItem, newItem: TodosItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: TodosItem, newItem: TodosItem): Boolean {
            return oldItem==newItem
        }

    }
    private val differ=AsyncListDiffer(this,diffCallback)
    var todos:List<TodosItem>
        get()=differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo=todos[position]
            tvTitle.text=todo.title
            cbDone.isChecked=todo.completed
        }
    }
}