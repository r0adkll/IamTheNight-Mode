package com.r0adkll.batapp.ui.day.home

import android.graphics.DashPathEffect
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.r0adkll.batapp.R
import com.robinhood.spark.SparkAdapter
import com.robinhood.spark.SparkView
import kotlin.random.Random


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val graph = root.findViewById<SparkView>(R.id.graph)

        // int m = a + (rand() % (b - a));
        val slope = 25

        graph.baseLinePaint.apply {
            style = Paint.Style.STROKE
            pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
        }
        graph.adapter = object : SparkAdapter() {

            val data = (0 .. 20).map {
                val noise = (Random.nextFloat() * 100) * (if (Random.nextBoolean()) -1 else 1)
                slope * it + noise
            }

            override fun getY(index: Int): Float {
                return data[index]
            }

            override fun getItem(index: Int): Any {
                return data[index]
            }

            override fun getCount(): Int {
                return data.size
            }

            override fun hasBaseLine(): Boolean {
                return true
            }

            override fun getBaseLine(): Float {
                return 75f
            }
        }

        return root
    }
}
