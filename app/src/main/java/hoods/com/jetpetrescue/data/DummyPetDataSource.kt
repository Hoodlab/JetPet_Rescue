package hoods.com.jetpetrescue.data


import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.data.model.Owner
import hoods.com.jetpetrescue.data.model.Pet

object DummyPetDataSource {
    private val owner = listOf(
        Owner(name = "Said", "Developer", R.drawable.blue_dog),
        Owner(name = "Said", "Engineer", R.drawable.blue_dog),
        Owner(name = "Said", "Economist", R.drawable.blue_dog),
        Owner(name = "Said", "Teacher", R.drawable.blue_dog),
        Owner(name = "Said", "Scientist", R.drawable.blue_dog),
    )
    val dogList = listOf(
        Pet(
            id = 0,
            name = "Hachiko",
            age = "Adult",
            gender = "Male",
            color = "Brown",
            breed = "Chihuahua",
            location = "Toronto CA",
            image = R.drawable.orange_dog,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
            owner = owner[0]
        ),
        Pet(
            id = 1,
            name = "Skooby Doo",
            age = "Adult",
            gender = "Male",
            color = "Gold",
            breed = "Chihuahua",
            location = "Toronto CA",
            image = R.drawable.blue_dog,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
            owner = owner[1]
        ),
        Pet(
            id = 2,
            name = "Miss Agnes",
            age = "Adult",
            gender = "Female",
            color = "White",
            breed = "Chihuahua",
            location = "Toronto CA",
            image = R.drawable.red_dog,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
            owner = owner[2]
        ),
        Pet(
            id = 3,
            name = "Cyrus",
            age = "Baby",
            gender = "Male",
            color = "Black",
            breed = "Chihuahua",
            location = "Toronto CA",
            image = R.drawable.yellow_dog,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
            owner = owner[3]
        ),
        Pet(
            id = 4,
            name = "Shelby",
            age = "Baby",
            gender = "Female",
            color = "Choco",
            breed = "Chihuahua",
            location = "Toronto CA",
            image = R.drawable.white_dog,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
            owner = owner[4]
        )
    )
}