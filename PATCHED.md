# List of Patched Bugs
## Unpatched in vanilla
### Client Side
| Type     | Bug ID                                                | Name                                                                                                                                |
|----------|-------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| Basic    | [MC-577](https://bugs.mojang.com/browse/MC-577)       | Mouse buttons block all inventory controls that are not default                                                                     |
| Basic    | [MC-4490](https://bugs.mojang.com/browse/MC-4490)     | Fishing line not attached to fishing rod in third person while crouching                                                      |
| Gameplay | [MC-12829](https://bugs.mojang.com/browse/MC-12829)   | Flying through ladders/vines/scaffolding in creative mode slows you down                                                                  |
| Basic    | [MC-22882](https://bugs.mojang.com/browse/MC-22882)   | Ctrl + Q won't work on Mac (disabled by default)                                                                                    |
| Basic    | [MC-46766](https://bugs.mojang.com/browse/MC-46766)   | Mining a block in Survival, then changing to Spectator creates a breaking animation and sound                                       |
| Basic    | [MC-55347](https://bugs.mojang.com/browse/MC-55347)   | Title with long duration shows in other world                                                                                       |
| Basic    | [MC-59810](https://bugs.mojang.com/browse/MC-59810)   | Cannot break blocks while sprinting (Ctrl+Click = right click on macOS) *(macOS only)*                                              |
| Basic    | [MC-79545](https://bugs.mojang.com/browse/MC-79545)   | The experience bar disappears when too many levels are given to the player                                                          |
| Basic    | [MC-80859](https://bugs.mojang.com/browse/MC-80859)   | Starting to drag item stacks over other compatible stacks makes the latter invisible until appearance change (stack size increases) |
| Basic    | [MC-90683](https://bugs.mojang.com/browse/MC-90683)   | "Received unknown passenger" - Entities with differing render distances as passengers outputs error                                 |
| Basic    | [MC-93384](https://bugs.mojang.com/browse/MC-93384)   | Bubbles appear at the feet of drowning mobs                                                                                         |
| Basic    | [MC-105068](https://bugs.mojang.com/browse/MC-105068) | Hitting another player blocking with a shield plays normal hurt sound                                                                                      |
| Basic    | [MC-108948](https://bugs.mojang.com/browse/MC-108948) | Boat on top of slime blocks hover over block                                                                                        |
| Basic    | [MC-111516](https://bugs.mojang.com/browse/MC-111516) | Player flickers/turns invisible when flying at high speeds                                                                          |
| Basic    | [MC-112730](https://bugs.mojang.com/browse/MC-112730) | Beacon beam and structure block render twice per frame                                                                              |
| Basic    | [MC-116379](https://bugs.mojang.com/browse/MC-116379) | Punching with a cast fishing rod in the off-hand detaches fishing line from rod                                                     |
| Basic    | [MC-122627](https://bugs.mojang.com/browse/MC-122627) | Tab suggestion box has missing padding on right side                                                                                |
| Basic    | [MC-122477](https://bugs.mojang.com/browse/MC-122477) | Linux/GNU: Opening chat sometimes writes 't                                                                                         |
| Basic    | [MC-127970](https://bugs.mojang.com/browse/MC-127970) | Using riptide on a trident with an item in your off-hand causes visual glitch with the item in your offhand                         |
| Basic    | [MC-143474](https://bugs.mojang.com/browse/MC-143474) | Respawning causes your hotbar to reset to the first space                                                                           |
| Basic    | [MC-159163](https://bugs.mojang.com/browse/MC-159163) | Quickly pressing the sneak key causes the sneak animation to play twice                                                             |
| Basic    | [MC-165381](https://bugs.mojang.com/browse/MC-165381) | Block breaking can be delayed by dropping/throwing the tool while breaking a block                                                  |                                                             |
| Basic    | [MC-176559](https://bugs.mojang.com/browse/MC-176559) | Breaking process resets when a pickaxe enchanted with Mending mends by XP / Mending slows down breaking blocks again                |
| Basic    | [MC-183776](https://bugs.mojang.com/browse/MC-183776) | After switching gamemodes using F3+F4, you need to press F3 twice to toggle the debug screen                                        |
| Basic    | [MC-197260](https://bugs.mojang.com/browse/MC-197260) | Armor Stand renders itself and armor dark if its head is in a solid block                                                           |
| Basic    | [MC-215531](https://bugs.mojang.com/browse/MC-215531) | The carved pumpkin overlay isn't removed when switching into spectator mode                                                         |
| Basic    | [MC-217716](https://bugs.mojang.com/browse/MC-217716) | The green nausea overlay isn't removed when switching into spectator mode                                                           |
| Basic    | [MC-227169](https://bugs.mojang.com/browse/MC-227169) | The main hand is broken when you hold a crossbow loaded into the secondary hand                                                     |
| Basic    | [MC-231097](https://bugs.mojang.com/browse/MC-231097) | Holding the "Use" button continues to slow down the player even after the used item has been dropped                                |
| Basic    | [MC-237493](https://bugs.mojang.com/browse/MC-237493) | Telemetry cannot be disabled                                                                                                        |

### Server Side (Both)
| Type     | Bug ID                                                | Name                                                                                                                                           |
|----------|-------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| Basic    | [MC-2025](https://bugs.mojang.com/browse/MC-2025)     | Mobs going out of fenced areas/suffocate in blocks when loading chunks                                                                         |
| Basic    | [MC-7569](https://bugs.mojang.com/browse/MC-7569)     | RCON output has newlines removed                                                                                                               |
| Gameplay | [MC-8187](https://bugs.mojang.com/browse/MC-8187)     | Two-by-two arrangements of jungle or spruce saplings cannot grow when there are adjacent blocks located north or west of the sapling formation |
| Basic    | [MC-14923](https://bugs.mojang.com/browse/MC-14923)   | Players can be kicked for spamming in a singleplayer world with cheats disabled                                                                |
| Basic    | [MC-30391](https://bugs.mojang.com/browse/MC-30391)   | Chickens, blazes and the wither emit particles when landing from a height, despite falling slowly                                              |
| Gameplay | [MC-31819](https://bugs.mojang.com/browse/MC-31819)   | Hunger saturation depletes on Peaceful                                                                                                         |
| Basic    | [MC-69216](https://bugs.mojang.com/browse/MC-69216)   | Switching to spectator mode while fishing keeps rod cast                                                                                       |
| Basic    | [MC-81773](https://bugs.mojang.com/browse/MC-81773)   | Bows and tridents drawn in survival/creative/adventure mode can be released in spectator mode                                                  |
| Basic    | [MC-88371](https://bugs.mojang.com/browse/MC-88371)   | Ender Dragon flies down in the void when the exit portal is destroyed                                                                          |
| Basic    | [MC-89146](https://bugs.mojang.com/browse/MC-89146)   | Pistons forget update when being reloaded                                                                                                      |
| Basic    | [MC-93018](https://bugs.mojang.com/browse/MC-93018)   | Wild wolves show breeding hearts but do not breed                                                                                              |
| Basic    | [MC-100991](https://bugs.mojang.com/browse/MC-100991) | Killing entities with a fishing rod doesn't count as a kill                                                                                    |
| Basic    | [MC-177381](https://bugs.mojang.com/browse/MC-177381) | Game does not count the distance properly if you locate a structure from more than 46340 blocks away                                           |
| Basic    | [MC-119417](https://bugs.mojang.com/browse/MC-119417) | A spectator can occupy a bed if they enter it and then are switched to spectator mode                                                          |
| Basic    | [MC-119754](https://bugs.mojang.com/browse/MC-119754) | Firework boosting on elytra continues in spectator mode                                                                                        |
| Basic    | [MC-121706](https://bugs.mojang.com/browse/MC-121706) | Skeletons and illusioners aren't looking up / down at their target while strafing                                                              |
| Basic    | [MC-121903](https://bugs.mojang.com/browse/MC-121903) | Command block minecarts do not save execution cooldown to NBT                                                                                  |
| Basic    | [MC-124177](https://bugs.mojang.com/browse/MC-124177) | Teleporting to another dimension loses some client states                                                                                      |
| Basic    | [MC-129909](https://bugs.mojang.com/browse/MC-129909) | Players in spectator mode continue to consume foods and liquids shortly after switching game modes                                             |
| Basic    | [MC-132878](https://bugs.mojang.com/browse/MC-132878) | Armor stands destroyed by explosions/lava/fire don't produce particles                                                                         |
| Basic    | [MC-135971](https://bugs.mojang.com/browse/MC-135971) | Can't use CTRL+Q in crafting table                                                                                                             |
| Basic    | [MC-155509](https://bugs.mojang.com/browse/MC-155509) | Puffed pufferfish can hurt the player while dying                                                                                              |
| Basic    | [MC-160095](https://bugs.mojang.com/browse/MC-160095) | End Rods only break Cactus when moved by pistons                                                                                               |
| Basic    | [MC-179072](https://bugs.mojang.com/browse/MC-179072) | Creepers do not defuse when switching from Survival to Creative/Spectator                                                                      |
| Basic    | [MC-183990](https://bugs.mojang.com/browse/MC-183990) | Group AI of some mobs breaks when their target dies                                                                                            |
| Basic    | [MC-193343](https://bugs.mojang.com/browse/MC-193343) | Soul Speed effect remains after switching to spectator mode                                                                                    |
| Basic    | [MC-199467](https://bugs.mojang.com/browse/MC-199467) | Certain entity animations stop after they've existed in world for too long                                                                     |
| Basic    | [MC-200418](https://bugs.mojang.com/browse/MC-200418) | Cured baby zombie villagers stay as jockey variant                                                                                             |
| Basic    | [MC-214147](https://bugs.mojang.com/browse/MC-214147) | Skeletons wearing leather armor still convert to strays in powder snow                                                                         |
| Basic    | [MC-206705](https://bugs.mojang.com/browse/MC-206705) | Spyglasses stay in use in spectator mode                                                                                                       |
| Basic    | [MC-206922](https://bugs.mojang.com/browse/MC-206922) | Items dropped by entities that are killed by lightning instantly disappear                                                                     |
| Basic    | [MC-215530](https://bugs.mojang.com/browse/MC-215530) | The freezing effect isn't immediately removed when switching into spectator mode                                                               |
| Basic    | [MC-223153](https://bugs.mojang.com/browse/MC-223153) | Block of Raw Copper uses stone sounds instead of copper sounds                                                                                 |
| Basic    | [MC-224729](https://bugs.mojang.com/browse/MC-224729) | Partially generated chunks are not saved in some situations                                                                                    |
| Basic    | [MC-231743](https://bugs.mojang.com/browse/MC-231743) | minecraft.used:minecraft.POTTABLE_PLANT doesn't increase when placing plants into flower pots                                                  |
| Basic    | [MC-232869](https://bugs.mojang.com/browse/MC-232869) | Adult striders can spawn with saddles in peaceful mode                                                                                         |

## Previously patched
Bugs that this mod has patched but has been superseded by a vanilla update.

| Bug ID                                                | Name                                                                                                            | Fixed in    |
|-------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|-------------|
| [MC-53312](https://bugs.mojang.com/browse/MC-53312)   | Illager/(zombie) villager/witch robes don't render the last two rows of pixels                                  | 22w17a      |
| [MC-72687](https://bugs.mojang.com/browse/MC-145929)  | There are no shadows on text displayed within the action bar                                                    | 22w24a      |
| [MC-148149](https://bugs.mojang.com/browse/MC-148149) | Linux game crash when opening links                                                                             | 1.19.1-pre6 |
| [MC-235035](https://bugs.mojang.com/browse/MC-235035) | Sleeping in a custom dimension with "natural" set to false causes crash                                         | 22w15a      |
| [MC-26757](https://bugs.mojang.com/browse/MC-26757)   | Large item tooltips can get cut off at the edges of the screen                                                  | 22w42a      |
| [MC-135973](https://bugs.mojang.com/browse/MC-135973) | Can't hold Q to drop items rapidly from container inventories                                                   | 22w42a      |
| [MC-145748](https://bugs.mojang.com/browse/MC-145748) | Clicking a settings button when there's a slider under the mouse in the next screen plays the click sound twice | 22w42a      |
| [MC-84873](https://bugs.mojang.com/browse/MC-84873)   | DeathTime values 20+ cause corrupted mobs                                                                       | 22w43a      |
| [MC-147605](https://bugs.mojang.com/browse/MC-147605) | Text cursors can exist in multiple fields                                                                       | 22w46a      |
| [MC-151412](https://bugs.mojang.com/browse/MC-151412) | "Edit Server Info" window does not focus "Server Name" text field automatically                                 | 22w46a      |
| [MC-233042](https://bugs.mojang.com/browse/MC-233042) | Server Address field isn't focused when Direct Connection menu is opened                                        | 22w46a      |
| [MC-249059](https://bugs.mojang.com/browse/MC-249059) | Loading terrain screen cannot close before 2 seconds have passed                                                | 22w46a      |
| [MC-228976](https://bugs.mojang.com/browse/MC-228976) | Entity collision is run on render thread                                                                        | 1.19.3-pre3 |
| [MC-165595](https://bugs.mojang.com/browse/MC-165595) | Guardian beam does not render when over a certain "Time" in level.dat                                           | 23w03a      |
| [MC-162253](https://bugs.mojang.com/browse/MC-162253) | Lag spike when crossing certain chunk borders                                                                   | 23w16a      |
| [MC-121772](https://bugs.mojang.com/browse/MC-121772) | Can't scroll while holding SHIFT on macOS                                                                       | 23w31a      |
| [MC-140646](https://bugs.mojang.com/browse/MC-140646) | Text fields don't scroll while selecting text with Shift                                                        | 23w31a      |
| [MC-90084](https://bugs.mojang.com/browse/MC-90084)   | When sitting in boats and boats with chest mobs legs penetrate the hull                                         | 23w31a      |
| [MC-72151](https://bugs.mojang.com/browse/MC-72151)   | Snow Golem's snowballs damage wolves instead of pushing them                                                    | 24w06a      |

