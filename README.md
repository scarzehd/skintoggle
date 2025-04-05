# SkinToggle

Do you want a mod that almost certainly does nothing useful? Then you're in the right place!

This mod dynamically toggles skin layers using the built-in vanilla minecraft skin customization system, making it only necessary to install on the client. In fact, installing on the server will have no effect at all.

Each part can be configured separately. Several configuration options are available.

## Mode
### Disabled
Nothing changes. You can configure your skin layers manually as usual.
### Toggle
The part is toggled based on the Activation Type.
### Flash On
The part is hidden by default but shown based on Activation Type. Identical to Toggle when Activation Type is Health High or Health Low.
### Flash Off
The part is shown by default but hidden based on Activation Type.

## Activation Type
### Hit Entity
The part is changed when you hit an entity.
### Health Low
The part is changed when your health is below Health Threshold (inclusive).
### Health High
The part is changed when your health is above Health Threshold (inclusive).
### Take Damage
The part is changed when you take damage.

## Other Configuration
### Flash Duration
How long to show or hide the part for in ticks. Only has an effect when Mode is Flash On or Flash Off.
### Health Threshold
How high or low your health needs to be to trigger the change, inclusive. Only has an effect when Activation Type is Health High or Health Low.

## Dependencies

This mod uses [oωo-lib](https://modrinth.com/mod/owo-lib) for configuration. Additionally, [Mod Menu](https://modrinth.com/mod/modmenu) is recommended for the in-game configuration UI to work. [Fabric API](https://modrinth.com/mod/fabric-api) is required as well.