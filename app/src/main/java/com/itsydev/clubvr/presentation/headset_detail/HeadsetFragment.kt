package com.itsydev.clubvr.presentation.headset_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.load
import com.itsydev.clubvr.ExperiencesActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentExperiencesBinding
import com.itsydev.clubvr.databinding.FragmentHeadsetDetailBinding
import com.itsydev.clubvr.presentation.experiences.ExperienceListeners
import com.itsydev.clubvr.presentation.experiences.ExperiencesAdapter
import com.itsydev.clubvr.presentation.experiences.ExperiencesViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class HeadsetFragment : Fragment() {

    lateinit var binding: FragmentHeadsetDetailBinding
    val viewmodel: ExperiencesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHeadsetDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        viewmodel.loadHeadsetData(requireContext())
        (requireActivity() as ExperiencesActivity).getActivityBinding().experiencesFloatingButton.visibility = View.GONE
        (requireActivity() as ExperiencesActivity).getActivityBinding().bottomAppBar.visibility = View.GONE
        return binding.root
    }

    private fun setupObservers() {
        viewmodel.getHeadset().observe(viewLifecycleOwner){
            binding.headsetDetailHeaderName.text = it.name
            binding.headsetDetailHeaderIcon.load(it.img[0].url)
            val photoList = mutableListOf<CarouselItem>()
            for (i in 0 until it.img.size) {
                if (i != 0) {
                    photoList.add(
                        CarouselItem(
                            it.img[i].url,
                            ""
                        )
                    )
                }
            }
            binding.headsetDetailImagesCarousel.setData(photoList)
        }
    }

    private fun setupListeners() {
        binding.headsetDetailGoBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_headsetFragment_to_experienceDetail)
        }
    }

}