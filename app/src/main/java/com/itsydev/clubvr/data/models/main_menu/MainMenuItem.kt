package com.itsydev.clubvr.data.models.main_menu

data class MainMenuItem(
    val id: Int
)

/*
    MainMenuItemDto and MainMenuItemBo Relation
    1 MainMenuAdadpter has 0 or N MainMenuItem
    1 MainMenuItem has 1 or N Title, Subtitle
    1 MainMenuItem has 1 or N Sections
 */

data class MainMenuItemDto(
    val id: Int,
    val main_title: List<String>,
    val main_subtitle: List<String>,
    val sections: List<Section> // List of sections
)

data class MainMenuItemBo(
    val id: Int,
    val main_title: List<String>,
    val main_subtitle: List<String>,
    val sections: List<Section> // List of sections
)

/*
    @Section
    The Sections can have types, this types declares which type of layout will inflate on de Item
    detail Fragment, this is used to extract and refactorize the code.
 */

data class Section(
    val id: Int,
    val type: Int,
    val title: List<String>,
    val subtitle: List<String>,
    val description: List<String>,
    val img: List<String>
)
