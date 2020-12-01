package cc.join.productmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import cc.join.productmanager.R


class HomeFragment: Fragment(), HomePresenter.View {

    lateinit var presenter: HomePresenter
    lateinit var addProductButton: Button
    lateinit var productListView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = HomePresenter(this)


        val root = inflater.inflate(R.layout.fragment_home, container, false)
        productListView = root.findViewById(R.id.ProductListView)
        addProductButton = root.findViewById(R.id.addButton)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.viewCreated();
        addProductButton.setOnClickListener { clickedAddProduct() }
        productListView.adapter = presenter.productListAdapter
    }


    private fun clickedAddProduct() {
        presenter.clickedAddProduct();
    }
}