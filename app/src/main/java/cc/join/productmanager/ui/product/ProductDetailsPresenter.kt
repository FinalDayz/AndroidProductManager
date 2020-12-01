package cc.join.productmanager.ui.product

import android.content.Context
import android.content.Intent
import android.widget.Toast
import cc.join.productmanager.models.Product

class ProductDetailsPresenter(private val view: View) {

    lateinit var product: Product

    interface View {
        fun setProductName(name: String)
        fun setProductStock(stock: String)
        fun setProductPrice(price: String)
        fun setProductDescription(description: String)
        fun getIntent(): Intent
        fun requireContext(): Context
        fun back()
    }

    fun viewCreated() {

        //TODO: get a better solution for the hardcoded string
        product = view.getIntent().getSerializableExtra("PRODUCT") as Product

        fillFields()
    }

    private fun fillFields() {
        view.setProductName(product.name)

        view.setProductStock(product.stock.toString())

        //TODO: get a better solution for the €
        view.setProductPrice("€"+product.price.toString())

        view.setProductDescription(product.description)
    }

    fun clickedOrder() {
        val text = "Ordered '"+product.name+"'";
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(view.requireContext(), text, duration)
        toast.show()
        view.back()
    }

}