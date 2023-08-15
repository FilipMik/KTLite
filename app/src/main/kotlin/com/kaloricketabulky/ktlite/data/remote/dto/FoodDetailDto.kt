package com.kaloricketabulky.ktlite.data.remote.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "potravina", strict = false)
data class FoodDetailDto(
    @field:Attribute(name = "guid_potravina")
    var guidPotravina: String = "",

    @field:Element(name = "nazev")
    var nazev: String = "",

    /*@field:Element(name = "stav", required = false)
    var stav: FoodStatus = FoodStatus(),

    @field:Element(name = "kategorie", required = false)
    var kategorie: FoodCategory = FoodCategory(),

    @field:Element(name = "znacka", required = false)
    var znacka: FoodBrand = FoodBrand(),

    @field:Element(name = "oblibena", required = false)
    var oblibena: String = "",

    @field:Element(name = "zakladniJednotka", required = false)
    var zakladniJednotka: String = "",

    @field:Element(name = "zamek", required = false)
    var zamek: String = "",*/

    @field:Element(name = "hodnoty", required = false)
    var hodnoty: NutritionalValues = NutritionalValues(),

    /*@field:ElementList(name = "jednotky", required = false)
    var jednotky: List<UnitType> = mutableListOf(),

    @field:Element(name = "popisObsah", required = false)
    var popisObsah: String = "",

    @field:Element(name = "popisZdravi", required = false)
    var popisZdravi: String = "",

    @field:Element(name = "popisPrakticke", required = false)
    var popisPrakticke: String = "",

    @field:ElementList(name = "stitkyVitaminy", required = false)
    var stitkyVitaminy: List<VitaminTag> = mutableListOf(),

    @field:ElementList(name = "stitkyMineraly", required = false)
    var stitkyMineraly: List<MineralTag> = mutableListOf()*/
)

data class FoodStatus(
    @field:Attribute(name = "id_stav", required = false)
    var idStav: String = "",

    @field:Text(required = false)
    var stav: String = ""
)

data class FoodCategory(
    @field:Attribute(name = "id_typ", required = false)
    var idTyp: String = "",

    @field:Text(required = false)
    var kategorie: String = ""
)

data class FoodBrand(
    @field:Attribute(name = "id_znacka", required = false)
    var idZnacka: String = "",

    @field:Text(required = false)
    var znacka: String = ""
)

data class NutritionalValues(
    @field:Element(name = "energie", required = false)
    var energie: Nutrient = Nutrient(),

    @field:Element(name = "bilkoviny", required = false)
    var bilkoviny: Nutrient = Nutrient(),

    @field:Element(name = "sacharidy", required = false)
    var sacharidy: Nutrient = Nutrient(),

    @field:Element(name = "cukry", required = false)
    var cukry: Nutrient = Nutrient(),

    @field:Element(name = "tuky", required = false)
    var tuky: Nutrient = Nutrient(),

    @field:Element(name = "nasyceneMastneKyseliny", required = false)
    var nasyceneMastneKyseliny: Nutrient = Nutrient(),

    @field:Element(name = "transmastneKyseliny", required = false)
    var transmastneKyseliny: Nutrient = Nutrient(),

    @field:Element(name = "cholesterol", required = false)
    var cholesterol: Nutrient = Nutrient(),

    @field:Element(name = "vlaknina", required = false)
    var vlaknina: Nutrient = Nutrient(),

    @field:Element(name = "sodik", required = false)
    var sodik: Nutrient = Nutrient(),

    @field:Element(name = "vapnik", required = false)
    var vapnik: Nutrient = Nutrient(),

    @field:Element(name = "sul", required = false)
    var sul: Nutrient = Nutrient(),

    @field:Element(name = "voda", required = false)
    var voda: Nutrient = Nutrient(),

    @field:Element(name = "mononenasycene", required = false)
    var mononenasycene: Nutrient = Nutrient(),

    @field:Element(name = "polynenasycene", required = false)
    var polynenasycene: Nutrient = Nutrient(),

    @field:Element(name = "gi", required = false)
    var gi: String = "",

    @field:Element(name = "phe", required = false)
    var phe: Nutrient = Nutrient(),

    @field:Element(name = "alcohol", required = false)
    var alcohol: Nutrient = Nutrient(),

    @field:Element(name = "tekutiny", required = false)
    var tekutiny: String = ""
)

data class Nutrient(
    @field:Attribute(name = "jedn", required = false)
    var jednotka: String = "",

    @field:Text(required = false)
    var hodnota: String = ""
)

/*data class UnitType(
    @field:Attribute(name = "nasobek", required = false)
    var nasobek: String = "",

    @field:Attribute(name = "guid_jednotka", required = false)
    var guidJednotka: String = "",

    @field:Text(required = false)
    var nazev: String = ""
)

data class VitaminTag(
    @field:Attribute(name = "nazev", required = false)
    var nazev: String = "",

    @field:Attribute(name = "guid_stitek", required = false)
    var guidStitek: String = "",

    @field:Attribute(name = "typ", required = false)
    var typ: String = "",

    @field:Text(required = false)
    var obsah: String = ""
)

data class MineralTag(
    @field:Attribute(name = "nazev", required = false)
    var nazev: String = "",

    @field:Attribute(name = "guid_stitek", required = false)
    var guidStitek: String = "",

    @field:Attribute(name = "typ", required = false)
    var typ: String = "",

    @field:Text(required = false)
    var obsah: String = ""
)*/
