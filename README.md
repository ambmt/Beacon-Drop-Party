https://ambmt.xyz


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/ambmt">
    <img src="images/clear-a.jpg" alt="Logo" width="80" height="80">
  </a>

## Prerequisites

In order to try this out you need the following software to be installed on your machine:

* Java version 8 or above (e.g. [OpenJDK](https://openjdk.java.net/install/))
* [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
* [docker](https://docs.docker.com/v17.09/engine/installation/)

## Quickstart

Clone the template project to your system:
````bash
git clone https://github.com/ambmt/Beacon-Drop-Party.git
````

This project uses [Maven](https://maven.apache.org/) for building. So on your command line run

````bash
mvn package
```` 

To test the plugin we fire up the spigot Minecraft server using an existing docker image.
In order for it to find the jar containing our plugin we need to mount the `target` folder to `/data/plugins`

````bash
docker run --rm -e EULA=true  -p 25565:25565 -v $(pwd)/target:/data/plugins cmunroe/spigot:1.16.4 
````

To test it with bukkit do:

````bash
docker run --rm -e EULA=true  -p 25565:25565 -v $(pwd)/target:/data/plugins cmunroe/bukkit:1.16.4
````

In the log produced by the server on the command line watch out for the following lines indicating that the plugin
was deployed properly:

```
[19:33:52 INFO]: [Beacon Drop Party] Ready for Beacon Party Drops
``` 

Start the Mincraft client on your computer and connect to the local Mincraft server by specifying `localhost` as Server Address.

Open the command line in Minecraft (by pressing `t`) try the new command and see what happens:
```
bdp:
    usage: /<command> [setblock|startevent|forcestart|stop|setdroprate|add|reload] [time|droprate|objectname] [amount]
```
Permissions
```
  beacondropparty.setblock:
    description: Set a new BeaconBlock as PartyDropBeacon.
    default: op
  beacondropparty.reload:
    description: Reload the Config file.
    default: op
  beacondropparty.add:
    description: Add a new Item to the dropItemList.
    default: op
  beacondropparty.forcestart:
    description: Forcestart the Event.
    default: op
  beacondropparty.startevent:
    description: Start the Event with Countdown.
    default: op
  beacondropparty.stop:
    description: Stop the Event.
    default: op
  beacondropparty.setdroprate:
    description: Set a new DropRate.
    default: op
```

To play with the code e.g. import this plugin in [Intellij](https://www.jetbrains.com/de-de/idea/download/). The
community edition is absolutely sufficient. Read [here](https://www.jetbrains.com/help/idea/maven-support.html) how to
import an existing Maven project.

Once you're done fiddling with the code don't forget to run `mvn package` and restarting the docker image for
your changes to take effect.

To install your plugin in another Minecraft server just copy the file `target/plotrating` to
that server's `plugin` folder. 

## Detailed instructions

TODO - contributions welcome! 
