import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import id.randiny.simplyautomatic.R

class FragmentOption : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}