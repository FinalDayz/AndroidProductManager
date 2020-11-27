package cc.join.productmanager.ui.home

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import cc.join.productmanager.MainActivity
import cc.join.productmanager.R
import cc.join.productmanager.models.Product
import com.google.gson.Gson

class HomePresenter(private val view: View) {

    interface View {
        fun requireActivity(): FragmentActivity
        fun requireContext(): Context
    }

    private var products: ArrayList<Product> = ArrayList()
    val productListAdapter: ProductListAdapter = ProductListAdapter(
        MainActivity.appContext
    )

    private fun fetchProducts() {
        products.clear()

        val sharedPref = view.requireActivity().getSharedPreferences(
            MainActivity.appContext.getString(cc.join.productmanager.R.string.preferences_location),
            Context.MODE_PRIVATE
        )

        var productSet = HashSet<String>()
        productSet =
            sharedPref.getStringSet(view.requireActivity().getString(R.string.save_products_key), productSet) as HashSet<String>;

        val gson = Gson();
        for(productJson in productSet) {
            products.add(
                gson.fromJson<Product>(productJson, Product::class.java)
            )
        }

        productListAdapter.products = this.products
        productListAdapter.notifyDataSetChanged()
    }

    fun clickedAddProduct() {
        val builder: AlertDialog.Builder =
            view.requireContext().let {
                AlertDialog.Builder(it)
            }

        val inflater = view.requireActivity().layoutInflater;
        val formView = inflater.inflate(R.layout.add_product_form, null)
        builder.setView(formView)

        builder
            .setTitle("Add product")
            .setPositiveButton("add") { dialog, listener ->
                run {
                    dialog.dismiss()
                    val name : String = formView.findViewById<TextView>(R.id.newProductName).text.toString()
                    val stock: Int = formView.findViewById<TextView>(R.id.newProductStock).text.toString().toInt()
                    val price: Float = formView.findViewById<TextView>(R.id.newProductStock).text.toString().toFloat()

                    val product = Product(
                        name = name,
                        stock = stock,
                        price = price
                    )

                    products.add(product)
                    saveProducts()
                    fetchProducts()
                    productListAdapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("cancel") { dialog, _ ->
                run {
                    dialog.cancel()
                }
            }

        val dialog: AlertDialog? = builder.create()

        dialog?.show()
    }

    private fun saveProducts() {
        val gson = Gson();

        val sharedPref = view.requireActivity().getSharedPreferences(
            MainActivity.appContext.getString(cc.join.productmanager.R.string.preferences_location),
            Context.MODE_PRIVATE
        )

        val newProductSet = HashSet<String>()

        for(product in products) {
            newProductSet.add(gson.toJson(product))
        }

        if(sharedPref != null) {
            with(sharedPref.edit()) {
                putStringSet(
                    MainActivity.appContext.getString(cc.join.productmanager.R.string.save_products_key),
                    newProductSet
                )
                commit()
            }
        }
    }

    fun viewCreated() {
        fetchProducts()
    }
}