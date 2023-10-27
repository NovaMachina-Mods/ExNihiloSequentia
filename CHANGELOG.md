# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [NeoForged Semantic Versioning](https://docs.neoforged.net/docs/gettingstarted/versioning).

## [5.0.0](https://github.com/NovaMachina-Mods/ExNihiloSequentia/compare/v5.0.0) - 2023-10-27

### Added
- Dependency on [NovaCore](https://github.com/NovaMachina-Mods/NovaCore)
- Cherry barrel, crook, crucible, hammer, and sieve
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
