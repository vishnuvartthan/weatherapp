package com.example.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.WeatherViewModel
import com.example.weatherapp.adapter.CastAdapter
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.db.WeatherDataBase
import com.example.weatherapp.service.ForecastData
import kotlinx.coroutines.launch

class ForecastFragment : Fragment() {

    lateinit var fBinding: FragmentForecastBinding
    private val viewModel: WeatherViewModel by activityViewModels {
        WeatherViewModel.WeatherViewModelFactory(
            requireActivity(),
            WeatherDataBase.getDatabase(requireContext()).noteDao()

        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast, container, false)
        return fBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fBinding.recycle1.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        Log.i("uara", "done3")
        lifecycleScope.launch {

            fBinding.recycle1.adapter = CastAdapter(viewModel.DAO.getForeCast(), resources)

        }
    }
}