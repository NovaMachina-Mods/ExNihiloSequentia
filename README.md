<p align="center">
    <a href="https://www.curseforge.com/minecraft/mc-mods/ex-nihilo-sequentia"><img src="https://cf.way2muchnoise.eu/full_400012_downloads.svg" /></a>
    <a href="https://www.curseforge.com/minecraft/mc-mods/ex-nihilo-sequentia"><img src="https://cf.way2muchnoise.eu/versions/400012.svg" /></a>
    <a href="https://github.com/NovaMachina-Mods/ExNihiloSequentia/blob/1.19/LICENSE"><img alt="GitHub license" src="https://img.shields.io/badge/license-CC%20BY--NC--SA%204.0-brightgreen"></a>
    <a href="https://github.com/NovaMachina-Mods/ExNihiloSequentia/issues"><img alt="GitHub issues" src="https://img.shields.io/github/issues/NovaMachina-Mods/ExNihiloSequentia"></a>
    <a href="https://github.com/NovaMachina-Mods/ExNihiloSequentia/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/NovaMachina-Mods/ExNihiloSequentia"></a>
</p>

# Ex Nihilo: Sequentia for Minecraft 1.19

Ex Nihilo: Sequentia is a modern port of Ex Nihilo: Creatio for 1.12, which in turn was a fork of Ex Nihilo: Adscensio for 1.10, which was a continuation of Ex Nihilo for 1.7.10.

# Discord

Ex Nihilo: Sequentia is on the NovaMachina Mods [discord server](https://discord.gg/CJyAkuw) where you can chat with other Ex Nihilo: Sequentia users and the developers.

# Translating

If you would like to help translate Ex Nihilo: Sequentia, you can do so through [Crowdin](https://crowdin.com/project/ex-nihilo-sequentia).

# License

[![](https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png)](http://creativecommons.org/licenses/by-nc-sa/4.0/)

This work is licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-nc-sa/4.0/).

# Maven

Ex Nihilo: Sequentia is avaliable via the [NovaMachina Mods artifact repository](https://repo.jacob-williams.me/#browse) for developers wishing to utilize its API.

Add the following to your `build.gradle`:

```groovy
repositories {
    maven {
        url = "https://repo.jacob-williams.me/repository/internal/"

    }
}

dependencies {
    implementation(fg.deobf("novamachina.exnihilosequentia:ExNihiloSequentia:${exnihilo_version}"))
}
```

Add the following to your `gradle.properties` (see [Maven](https://repo.jacob-williams.me/#artifact/novamachina.exnihilosequentia/ExNihiloSequentia) for the list of available versions):

```properties
exnihilo_version: 1.19.2-4.1.0.55-beta
```
