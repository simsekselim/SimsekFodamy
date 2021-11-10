package com.mobillium.simsekfodamy.presentation.loginflow.intro

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.icu.text.CaseMap
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.mobillium.simsekfodamy.R

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import java.util.*


    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()



class FragmentIntro : Fragment() {

    var swipe : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_fragment,container,false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun addToList(title: String,description: String, image: Int){
            titlesList.add(title)
            descList.add(description)
            imagesList.add(image)

        }

        val cancel : Button = view.findViewById(R.id.cancel)
        cancel.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fragmentLogin)

        }


        fun postToList(){


                addToList("Welcome to Fodamy Network! ",
                    "Fodamy is the best place to find your favourite recipes in all around the world. "
                    ,R.drawable.intro1)
                addToList("Finding recipes were not that easy. ",
                    "Fodamy is the best place to find your favourite recipes in all around the world.  ",
                    R.drawable.intro2)
                addToList("Add new recipe. ",
                    "Fodamy is the best place to find your favourite recipes in all around the world.  ",
                    R.drawable.intro3)
                addToList("Share recipe with others. ",
                    "Fodamy is the best place to find your favourite recipes in all around the world.  ",
                    R.drawable.intro4)





        }
        postToList()

        val view_pager2 = view.findViewById<ViewPager2>(R.id.view_pager2)
        view_pager2.adapter = ViewPagerAdapter(titlesList, descList, imagesList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //Indicator

        val indicator : SpringDotsIndicator = view.findViewById<SpringDotsIndicator>(R.id.indicator)
        indicator.setViewPager2(view_pager2)




       view_pager2.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
           override
           fun onPageSelected(position: Int) {
               super.onPageSelected(position)
               if(position == imagesList.size - 1){

                   swipe = view.findViewById(R.id.swipe)
                   swipe?.text = getString(R.string.Start)
                   swipe?.setOnClickListener {
                       Navigation.findNavController(it).navigate(R.id.fragmentLogin)
                   }


               }

               else{

                   swipe?.text = getString(R.string.GoAhead)



               }
           }
       })









        swipe = view.findViewById(R.id.swipe)
        swipe?.setOnClickListener {

            view_pager2.apply {
                beginFakeDrag()
                fakeDragBy(-1000f)
                endFakeDrag()
            }



        }







        }

    }



