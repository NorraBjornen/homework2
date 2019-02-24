package com.example.homework2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class MyFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(number : Int) : MyFragment{
            val args = Bundle()
            args.putInt(ARG_DOC_NUMBER, number)
            val fragment = MyFragment()
            fragment.arguments = args
            return fragment
        }
        private const val ARG_DOC_NUMBER = "number"
    }

    private var number : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        number = arguments?.getInt(ARG_DOC_NUMBER) as Int
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.document, container, false)
        val txt = view.findViewById<TextView>(R.id.txt)
        txt.text = resources.getString(R.string.default_document_label, number)
        return view
    }
}