package com.hacknyu.reclaimlife.model

data class Activities(val activity: String, val level: PhysicalActivityLevel, val group: GroupType)

enum class PhysicalActivityLevel {
    LIGHT,
    MODERATE,
    INTENSE
}

enum class GroupType {
    INDIVIDUAL,
    GROUP
}

val activitiesList = listOf(
    Activities("Yoga", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Meditation", PhysicalActivityLevel.LIGHT, GroupType.GROUP),
    Activities("Hiking", PhysicalActivityLevel.MODERATE, GroupType.INDIVIDUAL),
    Activities("Swimming", PhysicalActivityLevel.MODERATE, GroupType.GROUP),
    Activities("Art Therapy", PhysicalActivityLevel.LIGHT, GroupType.GROUP),
    Activities("Cycling", PhysicalActivityLevel.MODERATE, GroupType.INDIVIDUAL),
    Activities("Music Therapy", PhysicalActivityLevel.LIGHT, GroupType.GROUP),
    Activities("Reading", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Gardening", PhysicalActivityLevel.MODERATE, GroupType.GROUP),
    Activities("Dance Therapy", PhysicalActivityLevel.INTENSE, GroupType.GROUP),
    Activities("Cooking", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Journaling", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Photography", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Rock Climbing", PhysicalActivityLevel.INTENSE, GroupType.INDIVIDUAL),
    Activities("Volunteering", PhysicalActivityLevel.MODERATE, GroupType.GROUP),
    Activities("Fishing", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Gym", PhysicalActivityLevel.MODERATE, GroupType.GROUP),
    Activities("Writing", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Painting", PhysicalActivityLevel.LIGHT, GroupType.INDIVIDUAL),
    Activities("Yard Work", PhysicalActivityLevel.MODERATE, GroupType.INDIVIDUAL)
)
