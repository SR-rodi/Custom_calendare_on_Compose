package com.example.customcalendar.customCalendar.presentation.child

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch

@Composable
fun ScaleAnimation(key:Any,scaleEffect: Animatable<Float, AnimationVector1D>){
    LaunchedEffect(key1 = key) {
        launch {
            scaleEffect.animateTo(
                targetValue = 0.3f,
                animationSpec = tween(
                    durationMillis = 350
                )
            )
            scaleEffect.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
    }
}