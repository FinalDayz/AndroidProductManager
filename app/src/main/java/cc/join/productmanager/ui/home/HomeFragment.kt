package cc.join.productmanager.ui.home

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cc.join.productmanager.R
import cc.join.productmanager.models.Product
import com.google.gson.Gson


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


        listView.adapter = homeViewModel.getProductListAdapter(requireContext(), requireActivity());

        val addButton: Button = root.findViewById(R.id.addButton)
        addButton.setOnClickListener { clickedAddProduct() }

        return root
    }


    fun clickedAddProduct() {
        val builder: AlertDialog.Builder =
                activity?.let {
                    AlertDialog.Builder(it)
                }
             ?: return

        val inflater = requireActivity().layoutInflater;
        val formView = inflater.inflate(R.layout.add_product_form, null)
        builder.setView(formView)
        // 2. Chain together various setter methods to set the dialog characteristics
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

                    val gson = Gson();

                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    if(sharedPref != null) {
                        with(sharedPref.edit()) {

                            val productSet = HashSet<String>()
                            sharedPref.getStringSet(getString(R.string.save_products_key), productSet);

                            productSet.add(gson.toJson(product))

                            putStringSet(getString(R.string.save_products_key), productSet)
                            apply()
                        }
                    }

                }
            }
            .setNegativeButton("cancel") { dialog, _ ->
                {
                    dialog.cancel()
                }
            }
        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        val dialog: AlertDialog? = builder.create()

        dialog?.show()
    }
}