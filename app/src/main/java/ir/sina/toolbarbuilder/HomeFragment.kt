package ir.sina.toolbarbuilder

import DialogBuilder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import ir.sina.toolbarbuilder.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


        val builder = DialogBuilder(requireContext())
            .setIcons(getIconList())
            .setOnItemClickListener { position ->
                when (position) {
                    0 -> {
                        Toast.makeText(requireContext(), "Icon 1 clicked", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Log.e("TAG", "onViewCreated: sort")
                        Toast.makeText(requireContext(), "Icon 2 clicked", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Log.e("TAG", "onViewCreated: search")
                        Toast.makeText(requireContext(), "Icon 3 clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        binding.button.setOnClickListener {
            val dialog = builder.build()
            dialog.show()
        }
    }
    private fun getIconList(): List<Pair<String, Int>> {
        return listOf(
            Pair("Icon 1", R.drawable.ic_arrow_back),
            Pair("Icon 2", R.drawable.ic_sort),
            Pair("Icon 3", R.drawable.ic_search)
        )
    }
}