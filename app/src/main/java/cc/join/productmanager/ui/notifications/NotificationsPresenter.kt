package cc.join.productmanager.ui.notifications

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class NotificationsPresenter(private val view: View)  {

    interface View {
        fun getTextView(): TextView
        fun viewLifecycleOwner(): LifecycleOwner
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    init {
        text.observe(view.viewLifecycleOwner(), Observer {
            view.getTextView().text = it
        })
    }
}