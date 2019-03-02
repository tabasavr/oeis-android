package kozelko.me.oeisandroid

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class SequenceInfo : LinearLayout {

    constructor(context : Context) : super(context) {
        orientation = VERTICAL
    }

    constructor(context : Context, attributeSet : AttributeSet) : super(context, attributeSet) {
        orientation = VERTICAL
    }

    private fun addText(title: String, text : String?) {
        if (text != null) {
            val titleView = TextView(context)
            titleView.text = title
            addView(titleView)

            val tv = TextView(context)
            tv.text = text
            addView(tv)
        }
    }

    private fun addText(title: String, list: List<String>?) {
        if (list != null) {
            val titleView = TextView(context)
            titleView.text = title
            addView(titleView)

            list.forEach{
                val tv = TextView(context)
                tv.text = it
                addView(tv)
            }
        }
    }

    private fun addTitle(number: Int, id: String?, name: String?) {
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.HORIZONTAL

        val number_tv = TextView(context)
        number_tv.minEms = 7
        number_tv.maxEms = 7
        number_tv.layoutParams = ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        number_tv.text = "A%06d".format(number) + if (id != null) { "\n" + id.replace(" ", "\n")} else {""}
        layout.addView(number_tv)

        val name_tv = TextView(context)
        name_tv.text = name ?: "No name"
        layout.addView(name_tv)

        addView(layout)
    }

    fun setInfo(json: SequenceJson) {
        removeAllViews()

        addTitle(json.number, json.id, json.name)
        addText("Sequence:", json.data)
        addText("Comments:", json.comment)
        addText("References:", json.reference)
        addText("Links:", json.link)
        addText("Formula:", json.formula)
        addText("Examples:", json.example)
        addText("Maple:", json.maple)
        addText("Mathematica:", json.mathematica)
        addText("Programs:", json.program)
        addText("Crossrefs:", json.xref)
        addText("Keywords:", json.keyword)
        addText("Offset:", json.offset)
        addText("Author:", json.author)
        addText("Extensions:", json.ext)
    }

    fun setSmallInfo(json: SequenceJson) {
        removeAllViews()

        addTitle(json.number, json.id, json.name)
        addText("Sequence:", json.data)
    }
}