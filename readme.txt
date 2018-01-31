Things to do
-Connect intersecting points with a line
-Connect triple-intersecting points with a line
-Change the epsilion easily
-Be able to input some data from a spreadsheet
-Be able output the double and triple intersections
-Have togglable visibilty of the epsilion squares.
-Have a reset function

-Connecting Lines
Josh recommended creating a table to deal with points. Consider the following table.

 |A|B|C|D|E|F|G|
A|-|t|f|t|f|f|f|
B|-|-|f|t|f|f|f|
C|-|-|-|f|t|f|t| t - true f - false
D|-|-|-|-|t|f|f|
E|-|-|-|-|-|t|f|
F|-|-|-|-|-|-|f|
G|-|-|-|-|-|-|-|

Simply put, if a cell is true, there is a connection between the points of the row and column the cell lives in.
Using this chart, it's relatively easy to find triangles. Simply put, if you have two true pairs on the same row, (a, b) and (a, c) and pair (b, c) is also true, you've found a triple intersection.


(If there's anything I missed or if you have any input for the project, don't be afraid to change the text document.)
