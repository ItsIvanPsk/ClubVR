package com.itsydev.clubvr.data.models.experiences

import com.itsydev.clubvr.R


class ExperienceConstants {
    companion object {

        const val categoryItems = 6
        const val compatibleHeadsetItems = 4
        const val warningItems = 5

        val CATEGORY = listOf(
            R.string.category_simulation,
            R.string.category_action,
            R.string.category_adventure,
            R.string.category_arcade,
            R.string.category_inmersive,
            R.string.terror
        )
        val HEADSETS = listOf(
            R.string.headsets_htc,
            R.string.headsets_oculus,
            R.string.headsets_pico,
            R.string.reverb
        )
        val WARNINGS = listOf(
            R.string.warning_epilepsia,
            R.string.warning_vertigo,
            R.string.warning_visual,
            R.string.warning_delay,
            R.string.warning_phobia
        )
    }
}