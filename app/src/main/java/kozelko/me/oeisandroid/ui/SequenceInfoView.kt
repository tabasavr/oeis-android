package kozelko.me.oeisandroid.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.sequence_info_header.view.*
import kozelko.me.oeisandroid.R
import kozelko.me.oeisandroid.api.SequenceJson

class SequenceInfoView : LinearLayout {

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

    private fun addTitle(number: Int, id: String?, name: String?, data: String?) {
        val layout = LayoutInflater.from(context).inflate(R.layout.sequence_info_header, this, true)

        layout.info_header_number.text = "A%06d".format(number) + if (id != null) { "\n" + id.replace(" ", "\n")} else {""}
        layout.info_header_name.text = name ?: "No name"
        layout.info_header_sequence.text = data?.replace(",", ", ") ?: "No data"
    }

    fun setInfo(json: SequenceJson) {
        removeAllViews()

        addTitle(json.number, json.id, json.name, json.data)
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

        addTitle(json.number, json.id, json.name, json.data)
    }
}