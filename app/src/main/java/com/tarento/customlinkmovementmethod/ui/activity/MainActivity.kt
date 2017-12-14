package com.tarento.customlinkmovementmethod.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.tarento.customlinkmovementmethod.R
import com.tarento.customlinkmovementmethod.custom.CustomLinkMovementMethod
import com.tarento.customlinkmovementmethod.ui.fragment.FragmentWebView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customLinkMovementTextView = findViewById<View>(R.id.custom_link_movement_text_view) as TextView
        customLinkMovementTextView.movementMethod = object : CustomLinkMovementMethod() {
            override fun onLinkClicked(textView: TextView, link: String, text: String) {
                showWebView(link)
            }
        }
    }


    /**
     * Showing the url in webview
     *
     * @param aboutUrl
     */
    private fun showWebView(aboutUrl: String) {
        val bundle = Bundle()
        bundle.putString("URL", aboutUrl)

        val webViewFragment = FragmentWebView()
        webViewFragment.arguments = bundle
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, webViewFragment as Fragment)
                .addToBackStack(null)
                .commit()
    }
}
