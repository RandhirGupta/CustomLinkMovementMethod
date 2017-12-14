package com.tarento.customlinkmovementmethod.custom

import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.MotionEvent
import android.widget.TextView

/**
 * @author randhirgupta
 *  @since 14/12/17.
 */
abstract class CustomLinkMovementMethod : LinkMovementMethod() {


    /**
     * This gives click event for links
     *
     * @param textView
     * @param link
     * @param text
     */
    abstract fun onLinkClicked(textView: TextView, link: String, text: String)


    override fun onTouchEvent(widget: TextView?, buffer: Spannable?, event: MotionEvent?): Boolean {

        val action = event?.action
        if (action == MotionEvent.ACTION_UP) {

            var x = event.x.toInt()
            var y = event.y.toInt()

            x -= widget!!.totalPaddingLeft
            y -= widget.totalPaddingTop

            x += widget.scrollX
            y += widget.scrollY

            val layout = widget.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())

            val link = buffer!!.getSpans(off, off, URLSpan::class.java)
            if (link.size > 0) {
                val span = link[0]
                val url = span.url
                val s = widget.text as Spanned
                val start = s.getSpanStart(span)
                val end = s.getSpanEnd(span)
                var title: String? = null
                if (start != -1 && end != -1) {
                    val wordThatWasClicked = s.subSequence(start, end)
                    title = wordThatWasClicked.toString()
                }
                onLinkClicked(widget, url, title!!)
                return true
            }
        }
        return super.onTouchEvent(widget, buffer, event)
    }
}