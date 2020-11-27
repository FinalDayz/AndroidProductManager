package cc.join.productmanager.ui.product

import android.content.Context
import androidx.fragment.app.FragmentActivity

class ProductDetailsPresenter(private val view: View) {

    interface View {
        fun requireActivity(): FragmentActivity
        fun requireContext(): Context
    }

}