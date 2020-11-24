package cc.join.productmanager.ui.home

import cc.join.productmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cc.join.productmanager.models.Product


public class ProductListAdapter//public constructor
    (//context
    private var context: Context,//data source of the list adapter
    private var products: ArrayList<Product>
) : BaseAdapter() {

    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView = _convertView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.product_list_view_row_item, parent, false)
        }

        // get current item to be displayed

        // get current item to be displayed
        val currentItem = getItem(position) as Product

        // get the TextView for item name and item description

        // get the TextView for item name and item description
        val textViewItemName =
            convertView!!.findViewById(R.id.productName) as TextView
        val textViewItemDescription =
            convertView.findViewById(R.id.productStock) as TextView

        //sets the text for item name and item description from the current item object

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.name)
        textViewItemDescription.setText(currentItem.stock.toString())

        // returns the view for the current row

        // returns the view for the current row
        return convertView!!
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