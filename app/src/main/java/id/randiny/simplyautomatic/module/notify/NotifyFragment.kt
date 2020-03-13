package id.randiny.simplyautomatic.module.notify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.ConfiguratorFragment

class NotifyFragment : ConfiguratorFragment() {

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notify, container, false)

        titleEditText = root.findViewById(R.id.title_edit_text)
        contentEditText = root.findViewById(R.id.content_edit_text)

        titleEditText.addTextChangedListener {
            update()
        }
        contentEditText.addTextChangedListener {
            update()
        }

        return root
    }

    private fun update() {
        validConfig.postValue(
            titleEditText.text.toString().isNotBlank() &&
                    contentEditText.text.toString().isNotBlank()
        )
    }


    override fun getParam(): HashMap<String, String> {
        return hashMapOf(
            NotifyModule.PARAM_NOTIFICATION_TITLE to titleEditText.text.toString(),
            NotifyModule.PARAM_NOTIFICATION_CONTENT to contentEditText.text.toString()
        )
    }
}
