![CurseForge Downloads](https://img.shields.io/curseforge/dt/400012?style=for-the-badge&logo=curseforge)
![CurseForge Downloads](https://img.shields.io/curseforge/game-versions/400012?style=for-the-badge&logo=curseforge)
![Modrinth Downloads](https://img.shields.io/modrinth/dt/WhVa4xyw?style=for-the-badge&logo=modrinth)
![Modrinth Downloads](https://img.shields.io/modrinth/game-versions/WhVa4xyw?style=for-the-badge&logo=modrinth)
![Static Badge](https://img.shields.io/badge/license-CC_BY--NC--SA_4.0-green?style=for-the-badge)
![GitHub Issues or Pull Requests](https://img.shields.io/github/issues/NovaMachina-Mods/ExNihiloSequentia?style=for-the-badge&logo=github)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/NovaMachina-Mods/ExNihiloSequentia/build_1_20.yaml?style=for-the-badge&logo=github&label=1.20%20Build)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/NovaMachina-Mods/ExNihiloSequentia/build_1_20_dev.yaml?style=for-the-badge&logo=github&label=1.20-dev%20Build)

# Ex Nihilo: Sequentia

Ex Nihilo: Sequentia is a modern port of Ex Nihilo: Creatio for 1.12, which in turn was a fork of Ex Nihilo: Adscensio for 1.10, which was a continuation of Ex Nihilo for 1.7.10.

# Discord

Ex Nihilo: Sequentia is on the NovaMachina Mods [discord server](https://discord.gg/CJyAkuw) where you can chat with other Ex Nihilo: Sequentia users and the developers.

[![Discord](https://img.shields.io/discord/749057733709267005?style=for-the-badge&logo=discord)](https://discord.gg/CJyAkuw)

# Translating

If you would like to help translate Ex Nihilo: Sequentia, please create a pull request with your translation.

# License

[![](https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png)](http://creativecommons.org/licenses/by-nc-sa/4.0/)

The source code of this work is licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-nc-sa/4.0/).

Artwork of this mod is licensed under an All rights reserved license: Copyright &copy;2024 NovaMachina-Mods All rights reserved.

# Maven

Ex Nihilo: Sequentia is avaliable via the [NovaMachina Mods artifact repository](https://cloudsmith.io/~novamachina-mods/repos/release/packages/) for developers wishing to utilize its API.

Add the following to your `build.gradle`:

```groovy
repositories {
    maven {
        url = "https://dl.cloudsmith.io/public/novamachina-mods/release/maven/"

    }
}

dependencies {
    implementation(fg.deobf("novamachina.exnihilosequentia:ExNihiloSequentia:${exnihilo_version}"))
}
```

Add the following to your `gradle.properties` (see [Maven](https://cloudsmith.io/~novamachina-mods/repos/release/packages/) for the list of available versions):

```properties
exnihilo_version: 1.19.2-4.1.0.55-beta
```

[![Hosted By: Cloudsmith](https://img.shields.io/badge/OSS%20hosting%20by-cloudsmith-blue?logo=cloudsmith&style=for-the-badge)](https://cloudsmith.com)

Package repository hosting is graciously provided by  [Cloudsmith](https://cloudsmith.com).
Cloudsmith is the only fully hosted, cloud-native, universal package management solution, that
enables your organization to create, store and share packages in any format, to any place, with total
confidence.
