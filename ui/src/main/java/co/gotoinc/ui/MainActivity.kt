package co.gotoinc.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigation.findNavController(this, R.id.fragmentContainer)
            .addOnDestinationChangedListener { controller, destination, arguments ->
                updateToolbar(destination.id)
            }
    }

    private fun updateToolbar(destinationId: Int) {
        when (destinationId) {
            R.id.bookFragment -> {
                backButtonToolbar.isInvisible = true
                titleTextViewToolbar.setText(R.string.book_your_flight)
            }
        }
    }
}