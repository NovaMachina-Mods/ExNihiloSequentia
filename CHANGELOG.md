# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [NeoForged Semantic Versioning](https://docs.neoforged.net/docs/gettingstarted/versioning).

## [6.0.0](https://github.com/NovaMachina-Mods/ExNihiloSequentia/compare/v5.0.0...v6.0.0) - 2024-01-01

### Added
- `ITooltipProvider` interface for blocks
- Codecs for all recipes
- Advancements for all recipes
### Changed
- Update to NeoForge 20.4.167
- Update to NovaCore 2.0.0
- Replace all references of Forge with NeoForge
- Recipes with varargs parameters replaced with `List` parameter
- `MeshType` and `CrucibleType` now implement `StringRepresentable`
- Updated ore networking with ConfigurationTasks
### Removed
- CraftTweaker, Jade, JEI, KubeJS and TOP compatibility and moved them to their own addon mod
- Useless `EventBusSubscriber` annotations
- Id parameter from recipes

## [5.0.0](https://github.com/NovaMachina-Mods/ExNihiloSequentia/compare/v5.0.0) - 2023-10-27

### Added
- Dependency on [NovaCore](https://github.com/NovaMachina-Mods/NovaCore)
- Cherry barrel, crook, crucible, hammer, and sieve
- Bamboo barrel, crook, crucible, hammer, and sieve
- Christmas and Halloween Crook
### Changed
- Replace Forge with [NeoForge](https://neoforged.net/)
  - Requires NeoForge 47.1.55+
- Use NovaCore definitions, registries, and common data generation classes
- Replaced CraftTweaker builder functions with single `addRecipe` method
- Updated textures
### Removed
- Common Data Generation classes for:
  - Recipes
  - Loot Tables
  - Tags
  - Language
