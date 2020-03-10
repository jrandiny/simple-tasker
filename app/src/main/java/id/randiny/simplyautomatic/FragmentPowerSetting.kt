import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import id.randiny.simplyautomatic.R

class FragmentPowerSetting : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}