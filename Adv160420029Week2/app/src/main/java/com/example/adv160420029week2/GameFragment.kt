package com.example.adv160420029week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var score = 0
    var result = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }
        val txtRand1 = view.findViewById<TextView>(R.id.txtRand1)
        val txtRand2 = view.findViewById<TextView>(R.id.txtRand2)

        val random1 = (0..100).random()
        val random2 = (0..100).random()

        txtRand1.text = random1.toString()
        txtRand2.text = random2.toString()

        result = random1 + random2

        var tebakan = view.findViewById<TextView>(R.id.txtAngka)

        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        btnSubmit.setOnClickListener {
            if(tebakan.text.toString() == result.toString()){
                score = score + 1

                val random1 = (0..100).random()
                val random2 = (0..100).random()

                txtRand1.text = random1.toString()
                txtRand2.text = random2.toString()

                result = random1 + random2
                tebakan.setText("")
            } else {
                //lempar ke fragment result
                var totalScore = score
                val action = GameFragmentDirections.actionResultFragment(totalScore)
                Navigation.findNavController(it).navigate(action)
            }
        }

        btnBack.setOnClickListener {
            val action = GameFragmentDirections.actionMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}