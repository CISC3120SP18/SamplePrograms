# Dependency Injection and Observer Pattern: Startup-Code

This directory contains the start-up code to enable a discussion in dependency
injection and the observer pattern. 

The start-up code is about a simple application called "Grades Tracker" whose
main functionality is to compute an individual's GPA. It has three "views",
- where a user enters the name of an individual;
- where a user enters the courses and the grades;
- and where the GPA is computed and displayed. 

The code contains objects representing courses and other data, and these
objects must be passed to the views. In other words, the views depend
on these "data" objects. The primary question is, how we provide these
"data" objects to the views. 

In addition, how are the views are updated when the underlying "data"
objects are updated? 

It is not only "views" that can depend on some external "data" objects. 
There are dependencies between other objects as well. For instance, when
a course grade change, how does the GPA or other values are updated? 

From this start-up code, we explore two main topics:
- from the perspective of modular design, how we transform the monolithic
  UI interface code to a more modular design. 
- what mechanisms we can employ to provide ***external*** dependencies to views
  and other objects.

These two topics are correlated in the sense as we make the application design
more modular some internal dependencies may become external dependencies.

The emphasis is given to the second topic, for which, we explore
a few mechanisms including
- Not to use the dependency injection design pattern
-  Use the dependency injection design pattern
   -  Via setter methods
   -  Via constructor methods
   -  Via inheritance (implementing interface or extending a class)


