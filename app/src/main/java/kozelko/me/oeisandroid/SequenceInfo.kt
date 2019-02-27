package kozelko.me.oeisandroid

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

class SequenceInfo : LinearLayout {

    constructor(context : Context) : super(context)

    constructor(context : Context, attributeSet : AttributeSet) : super(context, attributeSet)

    private fun addText(text : String?) {
        if (text != null) {
            val tv = TextView(context)
            tv.text = text
            addView(tv)
        }
    }

    fun setInfo(json: SequenceJson) {
        addText("A%06d".format(json.id))
    }
}