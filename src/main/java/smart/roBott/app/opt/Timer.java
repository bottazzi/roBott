/**
 *     
    roBott - Copyright (C) 2002  Vitor Santos Bottazzi

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
 *    
 */

package smart.roBott.app.opt;

public class Timer {
long t;
// constructor
public Timer() {
reset();
}
// reset timer
public void reset() {
t = System.currentTimeMillis();
}
// return elapsed time
public long elapsed() {
return System.currentTimeMillis() - t;
}
// print explanatory string and elapsed time
public void print(String s) {
System.out.println(s + ": " + elapsed());
}
}