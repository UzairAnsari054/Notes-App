package com.example.notesapp.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.example.notesapp.ui.theme.CadmiumGreenDarkPrimary
import com.example.notesapp.ui.theme.CadmiumGreenDarkSecondary
import com.example.notesapp.ui.theme.CadmiumGreenLightPrimary
import com.example.notesapp.ui.theme.CadmiumGreenLightSecondary
import com.example.notesapp.ui.theme.CobaltBlueDarkPrimary
import com.example.notesapp.ui.theme.CobaltBlueDarkSecondary
import com.example.notesapp.ui.theme.CobaltBlueLightPrimary
import com.example.notesapp.ui.theme.CobaltBlueLightSecondary
import com.example.notesapp.ui.theme.CrimsonDarkPrimary
import com.example.notesapp.ui.theme.CrimsonDarkSecondary
import com.example.notesapp.ui.theme.CrimsonLightPrimary
import com.example.notesapp.ui.theme.CrimsonLightSecondary
import com.example.notesapp.ui.theme.DarkBackground
import com.example.notesapp.ui.theme.DarkOnSurface
import com.example.notesapp.ui.theme.DarkTertiary
import com.example.notesapp.ui.theme.LightBackground
import com.example.notesapp.ui.theme.LightOnSurface
import com.example.notesapp.ui.theme.LightTertiary
import com.example.notesapp.ui.theme.PurpleDarkPrimary
import com.example.notesapp.ui.theme.PurpleDarkSecondary
import com.example.notesapp.ui.theme.PurpleLightPrimary
import com.example.notesapp.ui.theme.PurpleLightSecondary
import com.example.notesapp.utils.ColorPalletName
import com.example.notesapp.utils.ColorSchemeName

object ColorSchemeChoice {
    fun getColorScheme(
        colorScheme: String,
        colorPallet: String
    ): ColorScheme {
        return when (colorScheme) {
            ColorSchemeName.DARK_MODE -> {
                when (colorPallet) {
                    ColorPalletName.CRIMSON -> DarkCrimsonScheme
                    ColorPalletName.CADMIUM_GREEN -> DarkCadmiumGreenScheme
                    ColorPalletName.COBALT_BLUE -> DarkCobaltBlueScheme
                    else -> DarkPurpleScheme
                }
            }

            else -> {
                when (colorPallet) {
                    ColorPalletName.CRIMSON -> LightCrimsonScheme
                    ColorPalletName.CADMIUM_GREEN -> LightCadmiumGreenScheme
                    ColorPalletName.COBALT_BLUE -> LightCobaltBlueScheme
                    else -> LightPurpleScheme
                }
            }
        }
    }

    private val DarkPurpleScheme = darkColorScheme(
        primary = PurpleDarkPrimary,
        secondary = PurpleDarkSecondary,
        tertiary = DarkTertiary,
        background = DarkBackground,
        surface = DarkTertiary,
        onPrimary = DarkTertiary,
        onSurface = DarkOnSurface
    )

    private val LightPurpleScheme = lightColorScheme(
        primary = PurpleLightPrimary,
        secondary = PurpleLightSecondary,
        tertiary = LightTertiary,
        background = LightBackground,
        surface = LightTertiary,
        onPrimary = LightTertiary,
        onSurface = LightOnSurface
    )

    private val DarkCrimsonScheme = darkColorScheme(
        primary = CrimsonDarkPrimary,
        secondary = CrimsonDarkSecondary,
        tertiary = DarkTertiary,
        background = DarkBackground,
        surface = DarkTertiary,
        onPrimary = DarkTertiary,
        onSurface = DarkOnSurface
    )

    private val LightCrimsonScheme = lightColorScheme(
        primary = CrimsonLightPrimary,
        secondary = CrimsonLightSecondary,
        tertiary = LightTertiary,
        background = LightBackground,
        surface = LightTertiary,
        onPrimary = LightTertiary,
        onSurface = LightOnSurface
    )

    private val DarkCadmiumGreenScheme = darkColorScheme(
        primary = CadmiumGreenDarkPrimary,
        secondary = CadmiumGreenDarkSecondary,
        tertiary = DarkTertiary,
        background = DarkBackground,
        surface = DarkTertiary,
        onPrimary = DarkTertiary,
        onSurface = DarkOnSurface
    )

    private val LightCadmiumGreenScheme = lightColorScheme(
        primary = CadmiumGreenLightPrimary,
        secondary = CadmiumGreenLightSecondary,
        tertiary = LightTertiary,
        background = LightBackground,
        surface = LightTertiary,
        onPrimary = LightTertiary,
        onSurface = LightOnSurface
    )

    private val DarkCobaltBlueScheme = darkColorScheme(
        primary = CobaltBlueDarkPrimary,
        secondary = CobaltBlueDarkSecondary,
        tertiary = DarkTertiary,
        background = DarkBackground,
        surface = DarkTertiary,
        onPrimary = DarkTertiary,
        onSurface = DarkOnSurface
    )

    private val LightCobaltBlueScheme = lightColorScheme(
        primary = CobaltBlueLightPrimary,
        secondary = CobaltBlueLightSecondary,
        tertiary = LightTertiary,
        background = LightBackground,
        surface = LightTertiary,
        onPrimary = LightTertiary,
        onSurface = LightOnSurface
    )
}