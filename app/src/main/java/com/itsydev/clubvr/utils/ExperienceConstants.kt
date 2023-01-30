package com.itsydev.clubvr.utils

import com.itsydev.clubvr.R


class ExperienceConstants {
    companion object {

        const val categoryItems = 5
        const val compatibleHeadsetItems = 3
        const val warningItems = 5

        val CATEGORY = listOf(
            R.string.category_simulation,
            R.string.category_action,
            R.string.category_adventure,
            R.string.category_arcade,
            R.string.category_inmersive
        )
        val HEADSETS = listOf(
            R.string.headsets_htc,
            R.string.headsets_oculus,
            R.string.headsets_pico
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