package com.kaloricketabulky.ktlite.data.remote.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "search")
data class SearchResponseDto(
    @field:ElementList(inline = true, name = "potravina")
    var foods: MutableList<FoodDto> = mutableListOf(),

    @field:Attribute(name = "q", required = false)
    var query: String = "",

    @field:Attribute(name = "jedn")
    var unit: String = "",

    @field:Attribute(name = "cas_zpracovani", required = false)
    var processingTime: String = ""
)

@Root(name = "potravina")
data class FoodDto(
    @field:Attribute(name = "guid_potravina")
    var guid: String = "",

    @field:Attribute(name = "id_stav", required = false)
    var stateId: Int? = null,

    @field:Attribute(name = "zamek")
    var isLocked: Boolean = false,

    @field:Attribute(name = "kategorie")
    var category: Int = 0,

    @field:Attribute(name = "napoj")
    var isBeverage: Boolean = false,

    @field:Element(name = "nazev")
    var name: String = "",

    @field:Element(name = "oblibena")
    var isFavorite: Boolean = false,

    @field:Element(name = "autor", required = false)
    var author: String? = null,

    @field:Element(name = "foto", required = false)
    var photo: String? = null,

    @field:Element(name = "foto_thumb", required = false)
    var thumbnailPhoto: String? = null,

    @field:Element(name = "energie")
    var energy: EnergyDto = EnergyDto()
)

@Root(name = "energie")
data class EnergyDto(
    @field:Attribute(name = "jedn")
    var energyUnit: String = "",

    @field:Text
    var energyValue: String = ""
)
