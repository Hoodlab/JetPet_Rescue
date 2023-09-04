package hoods.com.jetpetrescue.presentation.model

import androidx.annotation.DrawableRes

data class Pet(
    val name:String,
    val gender:String,
    val age:Double,
    val weight:Double,
    val color:String,
    val location:String,
    @DrawableRes val image:Int,
    val description:String,
    val owner: Owner,
    val id:Int
)