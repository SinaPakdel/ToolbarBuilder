package ir.sina.toolbarbuilder

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class DialogBuilder(private val context: Context) {
    companion object {
        private const val DEFAULT_NUM_COLUMNS = 3
    }

    private val dialog: Dialog = Dialog(context)
    private var icons: List<IconItem> = emptyList()
    private var itemClickListener: ((iconItem: IconItem) -> Unit)? = null

    private var iconSize: Int = 0
    private var gravity: Int = Gravity.CENTER

    fun setIcons(icons: List<IconItem>, iconSize: Int = 0): DialogBuilder {
        this.icons = icons
        this.iconSize = iconSize
        return this
    }

    fun setOnItemClickListener(listener: (iconItem: IconItem) -> Unit): DialogBuilder {
        this.itemClickListener = listener
        return this
    }

    fun setGravity(gravity: Int): DialogBuilder {
        this.gravity = gravity
        return this
    }

    fun build(): Dialog {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_icon_picker)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setGravity(gravity)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        val gridView = dialog.findViewById<GridView>(R.id.iconGridView)
        gridView.numColumns = calculateNumColumns()
        gridView.adapter = IconAdapter(dialog.context, icons)
        gridView.setOnItemClickListener { _, _, position, _ ->
            val iconItem = icons[position]
            itemClickListener?.invoke(iconItem)
            dialog.dismiss()
        }

        return dialog
    }

    private fun calculateNumColumns(): Int {
        return if (iconSize > 0) {
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            screenWidth / iconSize
        } else {
            DEFAULT_NUM_COLUMNS
        }
    }

    private inner class IconAdapter(
        private val context: Context,
        private val icons: List<IconItem>
    ) : BaseAdapter() {

        override fun getCount(): Int {
            return icons.size
        }

        override fun getItem(position: Int): IconItem {
            return icons[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            val holder: ViewHolder

            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.icon_item, parent, false)
                holder = ViewHolder(view)
                view.tag = holder
            } else {
                view = convertView
                holder = view.tag as ViewHolder
            }

            val icon = icons[position]
            holder.nameTextView.text = icon.itemName
            holder.iconImageView.setImageResource(icon.itemId)

            return view
        }

        private inner class ViewHolder(view: View) {
            val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
            val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        }
    }
}