package cc.join.productmanager.ui.home

import cc.join.productmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.marginBottom
import cc.join.productmanager.models.Product


public class ProductListAdapter(
    private var context: Context
) : BaseAdapter() {

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