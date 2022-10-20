package com.tanishq.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

import com.tanishq.assignment.R
import com.tanishq.assignment.databinding.PageItemBinding


class CartAdapterTwo(val requireContext: Context, val arrayList: List<String>) : PagerAdapter() {


    override fun getCount(): Int {
        return arrayList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = LayoutInflater.from(container.context).inflate(R.layout.page_item, container, false)

        val img = view.findViewById<ImageView>(R.id.images)

//        Log.d("posi", "instantiateItem: ${arrayList[position]}")
        Glide.with(container.context)
            .load(arrayList[position])
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img)

        // Glide.with(view.context).load(string[position]).into(img)

        container.addView(view)
        return view
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
