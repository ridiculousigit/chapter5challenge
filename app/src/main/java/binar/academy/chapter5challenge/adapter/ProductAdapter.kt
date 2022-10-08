package binar.academy.chapter5challenge.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.academy.chapter5challenge.activity.DetailActivity
import binar.academy.chapter5challenge.activity.UpdateActivity
import binar.academy.chapter5challenge.databinding.ItemProductBinding
import binar.academy.chapter5challenge.model.ResponseDataProductItem
import com.bumptech.glide.Glide

class ProductAdapter(private var listProduct : List<ResponseDataProductItem>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var onDelete : ((String)->Unit)? = null
    var onEdit : ((ResponseDataProductItem)->Unit)? = null
    var onDetail : ((ResponseDataProductItem)->Unit)? = null

    class ViewHolder(var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.productName.text = listProduct[position].name
        holder.binding.productCategory.text = listProduct[position].category
        holder.binding.productStock.text = listProduct[position].stock.toString()
        Glide.with(holder.itemView.context).load(listProduct[position].image).into(holder.binding.ivProduct)

        // Button Detail
        holder.binding.detailProduct.setOnClickListener {
            val detail = Intent(it.context, DetailActivity :: class.java)
            detail.putExtra("detail", listProduct[position])
            it.context.startActivity(detail)
        }

        // Button Update
        holder.binding.itemUpdate.setOnClickListener {
            val update = Intent(it.context, UpdateActivity :: class.java)
            update.putExtra("update", listProduct[position])
            it.context.startActivity(update)
        }

        // Button Delete
        holder.binding.itemDelete.setOnClickListener {
            onDelete?.invoke(listProduct[position].id)
        }
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}