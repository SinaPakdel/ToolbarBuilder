import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import ir.sina.toolbarbuilder.R

class DialogBuilder(context: Context) {
    private val dialog: Dialog = Dialog(context)
    private var icons: List<Pair<String, Int>> = listOf()
    private var itemClickListener: ((Int) -> Unit)? = null

    fun setIcons(icons: List<Pair<String, Int>>): DialogBuilder {
        this.icons = icons
        return this
    }

    fun setOnItemClickListener(listener: (Int) -> Unit): DialogBuilder {
        itemClickListener = listener
        return this
    }

    fun build(): Dialog {
        val layout = LinearLayout(dialog.context)
        layout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layout.orientation = LinearLayout.VERTICAL

        val gridView = GridView(dialog.context)
        gridView.numColumns = 4
        gridView.adapter = IconAdapter(dialog.context, icons)
        gridView.setOnItemClickListener { _, _, position, _ ->
            itemClickListener?.invoke(position)
            dialog.dismiss()
        }

        layout.addView(gridView)

        dialog.setContentView(layout)
        return dialog
    }

    private inner class IconAdapter(
        private val context: Context,
        private val icons: List<Pair<String, Int>>
    ) : BaseAdapter() {
        override fun getCount(): Int {
            return icons.size
        }

        override fun getItem(position: Int): Any {
            return icons[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.icon_item, parent, false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            val (name, iconResId) = icons[position]
            viewHolder.iconImageView.setImageResource(iconResId)
            viewHolder.nameTextView.text = name

            return view
        }

        private inner class ViewHolder(view: View) {
            val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
            val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        }
    }
}