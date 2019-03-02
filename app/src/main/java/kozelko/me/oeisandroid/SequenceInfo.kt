package kozelko.me.oeisandroid

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

class SequenceInfo : LinearLayout {

    constructor(context : Context) : super(context)

    constructor(context : Context, attributeSet : AttributeSet) : super(context, attributeSet)

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

    fun setInfo(json: SequenceJson) {
        removeAllViews()
        orientation = LinearLayout.VERTICAL

        addText("Id:", "A%06d (formerly %s)".format(json.number, json.id))
        addText("Name:", json.name)
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
        orientation = LinearLayout.VERTICAL

        addText("Id:", "A%06d (formerly %s)".format(json.number, json.id))
        addText("Name:", json.name)
        addText("Sequence:", json.data)
    }
}