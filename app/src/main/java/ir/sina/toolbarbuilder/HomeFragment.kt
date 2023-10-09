package ir.sina.toolbarbuilder

import android.os.Bundle
import android.util.Log
import android.view.Gravity
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

        binding.button.setOnClickListener {
             DialogBuilder(requireContext())
                .setIcons(
                    listOf(
                        Pair("آیکن ۱", R.drawable.ic_arrow_back),
                        Pair("آیکن ۲", R.drawable.ic_search),
                        Pair("آیکن ۳", R.drawable.ic_sort),
                        Pair("آیکن ۳", R.drawable.ic_sort),
                        Pair("آیکن ۳", R.drawable.ic_sort),
                        // ...
                    )
                )
                .setOnItemClickListener { position ->
                    when (position) {
                        0 -> {
                            Toast.makeText(
                                requireContext(),
                                "Icon 1 clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        1 -> {
                            Log.e("TAG", "onViewCreated: sort")
                            Toast.makeText(
                                requireContext(),
                                "Icon 2 clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        2 -> {
                            Log.e("TAG", "onViewCreated: search")
                            Toast.makeText(
                                requireContext(),
                                "Icon 3 clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                .setGravity(Gravity.BOTTOM)
                .build()
                .show()
        }
    }
}

enum class IconItem(val itemName: String, val itemId: Int) {
    ICON_1("آیکن ۱", R.drawable.ic_sort),
    ICON_2("آیکن ۲", R.drawable.ic_search),
    ICON_3("آیکن ۳", R.drawable.ic_arrow_back),
    // ...
}