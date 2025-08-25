## 1.1.2
### Intensive Integration!

Added 3 new Modifiers
- Wroughtslam: Mowzie's Mobs integration. Allows the Tool to do the Axe of a Thousand Metals' Shockwave slam Attack.
- Despoil: Biomancy integration. Equivalent to Biomancy's Despoil enchantment.
- Vampire Slayer: Vampirism integration. Equivalent to Vampirism's Vampire Slayer enchantment, +2 Damage per level, modified by Tool's Damage multiplier. Additional creates can be defined as Vampires with the Entity tag "tinkertantrum:vampire_slayable".


## 1.1.1
### Overly Patched (with magnets?)

Added 1 new Modifier
- Deflecting: After landing a hit, the Weapon can be used Block for a brief time. After use, this ability goes on Cooldown. Ending the blocking early will also put it on cooldown.
  - Unobtainable by default, designed to be added to Katanas in MCE2.
  - It's not called Parrying because Tinkers' already has something with that name, and it works differently.
  - both the Window where you can Block and the Cooldown after Blocking ends are configurable.


Fixed Overmending not always working 
- Overmending is also now an Ability as opposed to an Upgrade.
  - For something of its power level, it feels more appropriate.
  - base XP buffer is also increased to compensate.
  - and fun fact, Overworked affects it, have fun with that information.

Fixed Ferromagnetic not working.
- It was mostly me being dumb about Mixins, whoops. But it works fine now!

Disabled some logging from the Quarky trait

Enchanted Alloy material recipe should no longer spout errors when Amethyst Additions is not installed.


## 1.1.0
First Big Update

Added 4 new Modifiers
- Overmending: when equipped, the item will collect Experience and into a small internal buffer, converting it to Overslime over time. Ineffective without Overslime. Inspired By TCon2's Mending Moss.
- Alex's Caves compat
  - Ferromagnetic: Makes the item be considered Ferromagnetic to Alex's Caves content. (pulled/repelled by Neodymium, etc.) Usage with a Galena Gauntlet requires a second level, which requires an Ability slot.
- Vein Mining compat
  - Vein Mining: Allows the tool to do Vein Mining, by briefly applying the Enchantment through TConstruct's systems. If the level is higher than the maximum level for the Vein Mining enchantment, it will be treated as that maximum level instead.

Added 1 new Material
- Amethyst Additions (by adam9899)
  - Enchanted Alloy: a quite Durable, but somewhat slow material that can only be used for Heads, Tool Rods, and Bindings. Comes with Vein Mining as a trait.


Added entries to the Encyclopedia of Tinkering for all Modifiers, aswell as flavor text for Materials


## 1.0.0
Initial Release

Contains the Quarky and Overcharging modifiers.
    - Quarky: Increases damage a little based on how many items from mods by Vazkii are on your Hotbar.
    - Overcharging: Attacking builds charge on the Weapon, which adds some Overslime to your equipped armor when it fills.

