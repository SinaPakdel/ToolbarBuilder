package ir.sina.toolbarbuilder

import android.os.Bundle
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
                        IconItem.ICON_1,
                        IconItem.ICON_2,
                        IconItem.ICON_3,
                    )
                )
                .setOnItemClickListener {
                    when (it) {
                        IconItem.ICON_1 -> {
                            Toast.makeText(
                                requireContext(),
                                "Icon 1 clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        IconItem.ICON_2 -> {
                            Toast.makeText(
                                requireContext(),
                                "Icon 2 clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        IconItem.ICON_3 -> {
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