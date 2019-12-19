package co.gotoinc.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import co.gotoinc.domain.AirplaneProgressBarInteractor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : AppCompatActivity(), LifecycleOwner, AirplaneProgressBarInteractor {
    override fun showProgress(show: Boolean) {
        isLoading = show
        progressBar.isVisible = show
        if (show)
            airplaneProgressbar.startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.infinity_rotation)
                    .apply { interpolator = LinearInterpolator() }
            )
        else airplaneProgressbar.animation?.cancel()
    }

    override var isLoading: Boolean = false

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragmentContainer)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateToolbar(destination.id)
        }
        backButtonToolbar.setOnClickListener {
            takeUnless { isLoading }?.apply { navController.navigateUp() }
        }
    }

    private fun updateToolbar(destinationId: Int) {
        when (destinationId) {
            R.id.bookFragment -> {
                backButtonToolbar.isInvisible = true
                titleTextViewToolbar.setText(R.string.book_your_flight)
            }
            R.id.airportsFragment -> {
                backButtonToolbar.isInvisible = false
                titleTextViewToolbar.setText(R.string.leaving_from)
            }
        }
    }
}