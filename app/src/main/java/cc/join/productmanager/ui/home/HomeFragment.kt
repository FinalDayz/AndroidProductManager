package cc.join.productmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cc.join.productmanager.R
import cc.join.productmanager.models.Product


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val listView: ListView = root.findViewById(R.id.ProductListView)

        val list = ArrayList<Product>()
        val p = Product(name="lenovo laptop", stock = 10)

        list.add(p)

        listView.adapter = ProductListAdapter(requireContext(), list)

        return root
    }
}