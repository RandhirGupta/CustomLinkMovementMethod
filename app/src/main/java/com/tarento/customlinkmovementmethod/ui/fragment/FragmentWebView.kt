package com.tarento.customlinkmovementmethod.ui.fragment

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.tarento.customlinkmovementmethod.R


/**
 * @author randhirgupta
 *  @since 14/12/17.
 */
class FragmentWebView : Fragment() {

    private val TAG = WebViewFragment::class.java.simpleName

    private var mWebView: WebView? = null
    private val BUNDLE_URL_KEY = "URL"
    private var mUrl: String? = null

    fun getBundle(url: String): Bundle {
        val bundle = Bundle()
        bundle.putString(BUNDLE_URL_KEY, url)
        return bundle
    }

    fun getFragment(url: String): WebViewFragment {
        val webViewFragment = WebViewFragment()
        webViewFragment.arguments = getBundle(url)
        return webViewFragment
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val bundle = arguments
        if (bundle != null) {
            mUrl = bundle.getString(BUNDLE_URL_KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.webview_fragment, container, false)
        mWebView = rootView!!.findViewById(R.id.webview) as WebView
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mWebView!!.settings.javaScriptEnabled = true
        mWebView!!.clearCache(true)
        mWebView!!.settings.builtInZoomControls = true
        mWebView!!.settings.setSupportZoom(true)
        mWebView!!.settings.displayZoomControls = false
        mWebView!!.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        mWebView!!.webChromeClient = WebChromeClient()
        mWebView!!.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return false
            }
        }
        
        mWebView!!.loadUrl(mUrl)

    }
}
