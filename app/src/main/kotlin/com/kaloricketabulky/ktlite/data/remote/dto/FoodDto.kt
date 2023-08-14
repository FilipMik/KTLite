package com.kaloricketabulky.ktlite.data.remote.dto

import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement


@XmlRootElement(name = "potravina")
data class FoodDto(
    @XmlAttribute(name = "guid_potravina")
    val guid: String,

    @XmlAttribute
    val zamek: Boolean,

    @XmlAttribute
    val kategorie: Int,

    @XmlAttribute
    val napoj: Boolean,

    @XmlElement
    val nazev: String,

    @XmlElement
    val oblibena: Boolean,

    @XmlElement
    val autor: String?,

    @XmlElement
    val foto: String?,

    @XmlElement(name = "foto_thumb")
    val fotoThumb: String?,

    @XmlElement
    val energie: EnergyDto
)

data class EnergyDto(
    @XmlAttribute(name = "jedn")
    val jednotka: String,

    @XmlElement
    val value: Double
)
