package cc.join.productmanager.ui.product

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cc.join.productmanager.R
import android.widget.TextView

class ProductDetailsActivity: AppCompatActivity(), ProductDetailsPresenter.View {

    private lateinit var presenter: ProductDetailsPresenter

    private lateinit var productNameField: TextView
    private lateinit var productStockField: TextView
    private lateinit var productPriceField: TextView
    private lateinit var productDescriptionField: TextView

    lateinit var orderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        presenter = ProductDetailsPresenter(this)
        setContentView(R.layout.product_details)

        productNameField = findViewById(R.id.productDetailsName)
        productStockField = findViewById(R.id.productDetailsStock)
        productPriceField = findViewById(R.id.productDetailsPrice)
        productDescriptionField = findViewById(R.id.productDetailsDescription)
        orderButton = findViewById(R.id.productDescriptionOrderButton)

        orderButton.setOnClickListener {
            presenter.clickedOrder()
        }

        presenter.viewCreated();
    }

    override fun setProductName(name: String) {

        productNameField.text = name
    }

    override fun setProductStock(stock: String) {

        productStockField.text = stock
    }

    override fun setProductPrice(price: String) {

        productPriceField.text = price
    }

    override fun setProductDescription(description: String) {

        productDescriptionField.text = description
    }

    override fun requireContext(): Context {
        return baseContext
    }

    override fun back() {
        onBackPressed()
    }
}