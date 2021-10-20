# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.4] - 2021-10-18

### Fixed

- Kill all players if all game ends with a draw to prevent plugin crash.
- Fix plugin crash when a player kills other player.

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