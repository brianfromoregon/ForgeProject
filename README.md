[![Build Status](https://travis-ci.org/brianfromoregon/ForgeProject.svg?branch=master)](https://travis-ci.org/brianfromoregon/ForgeProject)

#### Setup
1. Install [Oracle JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). 
1. Install an IDE. 
  * I prefer [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/) 
  * But [Eclipse](http://www.eclipse.org/downloads/) works too, pick "Eclipse IDE for Java Developers"
1. Download and install [Git](https://git-scm.com/downloads)
1. Clone this repository. 
  * From a command window in the directory where you want to clone into, enter `git clone https://github.com/brianfromoregon/ForgeProject.git`
1. Download all the dependencies.
  * From a command window in the ForgeProject directory, enter `gradlew --refresh-dependencies setupDecompWorkspace eclipse`
1. These steps are only required if you are using IntelliJ.
  * Open IntelliJ, choose Import Project, and select the build.gradle file inside the ForgeProject directory. Press OK twice.
  * After import finishes open Run > Edit Configurations, click the green plus button, select Application, and set these values:
    * Name: Minecraft
    * Main class: GradleStart
    * Use classpath of module: ForgeProject_main
1. Press OK and click the green play button.

#### Verify
1. Run Minecraft from within the IDE
1. Select Single Player, then Create New World, set Game Mode to Creative, and click create.
1. Type the word *draw* in chat and see if a line of glass blocks is created in front of you
