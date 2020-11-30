package cc.join.productmanager.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cc.join.productmanager.R
import cc.join.productmanager.models.Product


class ProductListAdapter(
    private var context: Context,
    private val clickListener: OnClickProductInterface
) : BaseAdapter() {

    interface OnClickProductInterface {
        fun onClick(product: Product, view: View?)
    }

    var products: ArrayList<Product> = ArrayList<Product>();

    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView : View? = _convertView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.product_list_view_row_item, parent, false)

        }

        val currentItem = getItem(position) as Product

        val textViewProductName =
            convertView!!.findViewById(R.id.productName) as TextView
        val textViewProductStock =
            convertView.findViewById(R.id.productStock) as TextView
        val textViewProductPrice =
            convertView.findViewById(R.id.productCost) as TextView

        textViewProductName.setText(currentItem.name)
        textViewProductStock.setText(currentItem.stock.toString())
        textViewProductPrice.setText("€"+currentItem.price.toString())

        convertView.setOnClickListener {
            clickListener.onClick(currentItem, it)
        }

        return convertView
    }

    override fun getItem(position: Int): Any {
        return products[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return products.size
    }

}