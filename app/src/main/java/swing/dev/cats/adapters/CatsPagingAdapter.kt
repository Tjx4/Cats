package swing.dev.cats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.entersekt.shopfinder.helpers.loadImageFromInternet
import swing.dev.cats.R
import swing.dev.cats.persistance.room.tables.cats.CatImageTable

class CatsPagingAdapter(var context: Context) : PagingDataAdapter<CatImageTable, CatsPagingAdapter.CatsViewHolder>(
    CatComparator
)  {
    private var catClickListener: AddCatClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cat_layout,
            parent,
            false
        )
        return CatsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val cat = getItem(position)
        holder.imgCatImg?.let {
            loadImageFromInternet(context,cat?.url ?: "", it, R.drawable.ic_def)
        }
    }

    inner class CatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var catTv: TextView = itemView.findViewById(R.id.tvCat)
        internal var imgCatImg: ImageView = itemView.findViewById(R.id.imgCat)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            getItem(absoluteAdapterPosition)?.let { catClickListener?.onCatClickListener(it) }
        }
    }

    interface AddCatClickListener {
        fun onCatClickListener(catImageTable: CatImageTable)
    }

    fun addPairClickListener(catClickListener: AddCatClickListener) {
        this.catClickListener = catClickListener
    }
}

object CatComparator : DiffUtil.ItemCallback<CatImageTable>() {
    override fun areItemsTheSame(oldItem: CatImageTable, newItem: CatImageTable) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: CatImageTable, newItem: CatImageTable) = oldItem == newItem
}