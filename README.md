# roBott - Off-line industrial robot programming framework

Abstract — The industrial robot programming is a work for specialist
in robotics. Today, this work is very hard because there are many
robot manufacturers with different languages and different programming
environments. Although, off-line programming is an way that can reduce
drastically the machines stop time to maintenance.
With the use of object oriented design patterns, it is possible minimize
the time spent in robot programming. In this work is proposed a
off-line programming environment. This tool is based in one abstract
model to program robots, encapsulate in java classes. This way has the
main advantage of best source code utilization. Grouping the business
classes in modules by functionalities, we can reduce complexity between
low matching. Recognized patterns like Facade and Template Method
will construct the base to develop this programming framework. The
programming robot languages tested in this work was Rapid, Karel
and Melfa Basic IV, respectively languages used by ABB, Fanuc and
Mitsubishi constructors.

Index Terms — Framework, off-line programming, robot code genera-
tor

## Dependencies

- JavaJDK >= 8.0
- Gradle >= 6.7
- JMonkeyEngine >= 3.1
- Java-ML == 0.1.7.jar ([Free Download here](https://sourceforge.net/projects/java-ml/files/))

Please copy manually the file "javaml-0.1.7.jar" to src/libs folder. Unfortunately, the gradle repo was not responsive at the time of this exercise to automate java-ml dependencies automatically. Please see build.gradle for more details.

## Run

- gradle build
- gradle run

## How to use

- Alt + Tab keys to switch between 3D visualisation and the GUI tool for Off-line robot programming.
- [Load Coordinates] Loads the surface of the file.stl model.
- [Select Points] Slice the model based in the selected Z axis height (e.g. ZSelection contour).
- [Generate Code] Generates a robot program using the selected points.

## TODO's list

- ~~Find an alternative for old Canvas3D~~ (JMonkeyEngine 3D is the new candidate)
- Explore Point Cloud based visualisations;
- Upgrade from JOGL towards WebGL to comply with Web standards;
- Translate all terms/standards to english allowing easier cooperation;
- Refactoring on modules to reduce duplicated code and improve design;
- Incorporate novel 3D exchange formats towards (e.g. PCL, IFC, xMCF, etc);
- Check current networking protocol used by robot manufacturers for integration;
- Add a newer ftp/scp feature to load the programs directly into the robot controller;
- Create a docker file to run and expose the code generation/translation via API;

## Video Resources

[roBott App Demo](https://www.youtube.com/watch?v=P5lRkhpcGO8) - Industrial Robots Offline Programming ([University of Minho](https://www.uminho.pt/EN/uminho/Units/schools-and-institutes/Pages/school-of-Engineering.aspx) - Mechatronics Lab).

[Robot Code Generator](https://www.youtube.com/watch?v=sCwH7NzrpoY) - First Off-line Programming Trials in 2002 ([CIMATEC/SENAI-BA](http://www.senaicimatec.com.br/en/areas-de-interesse/)).

## Contact

For enquiries about this open source project please contact:
Dr. Vitor Santos Bottazzi - vitor dot bottazzi at holovision dot com dot au

## License

    roBott - Copyright (C) 2002-2020  Vitor Santos Bottazzi

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

## Acknowledgements:

This research was carried out in the context of the Masters Industrial Electronic Engineering project supported by the Programme ALBAN, the European Union Programme of High level Scholarships for Latin America, scholarship no. E04M033540BR. 

Special thanks to SENAI-BA/CIMATEC, the Integrated Centre of Manufacture and Technology for the support during early in my career, allowing me to use their cutting edge robotics infrastructure and also to network with research centres around the world.

## References:

Bottazzi, V. S., & Fonseca, J. C. (2005, October). Off-line robot programming framework. In Joint International Conference on Autonomic and Autonomous Systems and International Conference on Networking and Services - (ICAS-ISNS' 05) (pp. 71-71). IEEE. ([Free PDF Download Here](https://github.com/bottazzi/roBott/blob/master/doc/bottazzi_robot_programming_framework.pdf))

Bottazzi, V., & Fonseca, J. (2006). Off-line Programming Industrial Robots Based in the Information Extracted From Neutral Files Generated by the Commercial CAD Tools Industrial Robotics: Programming, Simulation and Application, Edited by: Low Kin Huat.

## Models

[Free 3D models](https://www.thingiverse.com/thing:322130) in STL format.
