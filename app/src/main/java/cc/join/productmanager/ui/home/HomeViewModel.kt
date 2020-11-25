package cc.join.productmanager.ui.home

import android.content.Context
import android.widget.ListAdapter
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cc.join.productmanager.R
import cc.join.productmanager.models.Product
import com.google.gson.Gson

class HomeViewModel : ViewModel() {

    fun getProductListAdapter(
        context: Context,
        activity: FragmentActivity
    ): ListAdapter? {

        val products = ArrayList<Product>()
//        list.add(Product(name="Lenovo laptop", stock = 10, price = 899f))
//        list.add(Product(name="Race Bike", stock = 2, price = 295f))
//        list.add(Product(name="JOIN premium", stock = 99, price = 6.99f))

        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val productSet = HashSet<String>()
        sharedPref.getStringSet(activity.getString(R.string.save_products_key), productSet);

        val gson = Gson();
        for(productJson in productSet) {
            products.add(
                gson.fromJson<Product>(productJson, Product::class.java)
            )
        }

        return ProductListAdapter(context, products)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}