package com.example.data.models

data class Step(
    val distance: DistanceX,
    val duration: DurationX,
    val end_location: EndLocationX,
    val html_instructions: String,
    val polyline: Polyline,
    val start_location: StartLocationX,
    val travel_mode: String
)