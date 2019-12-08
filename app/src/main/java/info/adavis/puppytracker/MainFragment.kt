package info.adavis.puppytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import coil.api.load

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.imageView)
        textView = view.findViewById(R.id.text_log)

        view.findViewById<Button>(R.id.button_track).setOnClickListener {
            viewModel.trackPup()
        }

        viewModel.url.observe(this) { imageUrl ->
            displayPuppyImage(imageUrl)
        }

        viewModel.log.observe(this) { log ->
            updateLog(log)
        }
    }

    private fun displayPuppyImage(imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(true)
        }
    }

    private fun updateLog(log: String) {
        textView.text = log
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}