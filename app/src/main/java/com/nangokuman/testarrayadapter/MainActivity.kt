package com.nangokuman.testarrayadapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.nangokuman.testarrayadapter.databinding.ActivityMainBinding
import com.nangokuman.testarrayadapter.databinding.SampleItemRowBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityMainBinding
    private var nextInt: Int = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setInitialListView()
    }

    private fun setInitialListView() {
        val list = mutableListOf(
            SampleRow(1, "Apple Pie"),
            SampleRow(2, "Banana Bread"),
            SampleRow(3, "Cupcake"),
            SampleRow(4, "Donut"),
            SampleRow(5, "Eclair"),
            SampleRow(6, "Froyo"),
            SampleRow(7, "Honeycomb"),
            SampleRow(8, "Icecream Sandwich"),
            SampleRow(9, "Jelly Bean"),
            SampleRow(10, "KitKat"))
        val adapter = SampleListView(this, R.layout.sample_item_row , list)
        activityBinding.listView.adapter = adapter

        activityBinding.addBtn.setOnClickListener{
            if(!activityBinding.inputText.text.isNullOrEmpty()) {
                adapter.add(SampleRow(nextInt++, activityBinding.inputText.text.toString()))
            }
        }
    }

    private inner class SampleListView(context: Context, resourceId: Int, val dataList: MutableList<SampleRow>) :
        ArrayAdapter<SampleRow>(context, resourceId, dataList) {
        private val inflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return dataList.count()
        }

        override fun getItem(position: Int): SampleRow {
            return dataList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val binding: SampleItemRowBinding
            if(convertView == null) {
                binding = DataBindingUtil.inflate(inflater, R.layout.sample_item_row, null, false)
                binding.root.tag = binding
            } else {
                binding = convertView.tag as SampleItemRowBinding
            }
            binding.sampleRow = getItem(position)
            return binding.root
        }
    }
}
