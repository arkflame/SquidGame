# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.7] - 2021-10-23

### Added

- Added third game

### Fixed

- Fix title too longer error in GUI.
- Kill arena player only if bukkit player is alive.
- Kill arena player when bukkit player die.

## [0.1.6] - 2021-10-21

### Added

- Declaring the only player alive in play as the winner is now configurable.

### Fixed

- Fixed class not found "particles".
- Fixed 1.8 glass break sound (again)
- Fixed arena starting time config path.
- Reduced code redundancy.

## [0.1.5] - 2021-10-21

### Fixed

- Fixed 1.8 glass break sound.
- Fixed bedrock generation instead glass.

## [0.1.4] - 2021-10-18

### Added

- Improved sixth (6th) game.
- Added warning error if translation key doesn't exist.

### Fixed

- Kill all players if all game ends with a draw to prevent plugin crash.
- Fix plugin crash when a player kills other player.
- Only prevent fall damage if arena isn't in game state.
- Kill arena player when bukkit player died.

## [0.1.3] - 2021-10-17

### Added

- Now tutorial title is configurable per tutorial item, like subtitle.
- Added configurable sounds for red-light and green-light game.
- Added reward system with commands executed by console.
- Prevent food level change in game.
- Added /squid start command for force start.

### Fixed

- Fixed /squid leave command throw exception when arena is starting

### Removed

- Removed fall damage in arena.

## [0.1.2] - 2021-10-16

### Added

- Added 1.8 Compatibility.
- Added option to configure arena times (Wait, Starting and In-game time)
- Game sounds are now configurable.
- Kill block in last game is now configurable.

### Fixed

- Fixed arena config not update in real time.