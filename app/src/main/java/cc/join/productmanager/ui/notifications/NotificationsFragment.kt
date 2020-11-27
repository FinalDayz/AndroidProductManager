package cc.join.productmanager.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cc.join.productmanager.R

class NotificationsFragment : Fragment(), NotificationsPresenter.View {

    private lateinit var notificationsPresenter: NotificationsPresenter
    private lateinit var textView: TextView

    override fun getTextView(): TextView {
        return textView
    }

    override fun viewLifecycleOwner(): LifecycleOwner {
        return viewLifecycleOwner
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        textView = root.findViewById(R.id.text_notifications)

        notificationsPresenter = NotificationsPresenter(this)
        return root
    }
}