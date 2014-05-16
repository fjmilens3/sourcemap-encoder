sourcemap-encoder
=================

A simple JS sourcemap encoder for Java, inspired by coffee-source-map.

It's very lightweight with a small feature set, and mostly targeted at those
(like myself) who would like to dabble in compile-to-JS languages from within
the Java platform.

The library is implemented using Java as a Maven project, though it is not yet
available in any public Maven repositories. Sorry!

License
-------

This work is licensed under the 
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0). As
always, I'd really appreciate it if you'd release any of your own improvements
or additions to the code back into this project as well.

Acknowledgements
----------------

Benbria's [coffee-source-map](https://github.com/benbria/coffee-source-map) was
instrumental as an excellent reference implementation and resource for my work.
My encoder's inner workings are a shameless port of the (cleaner) Coffeescript.

Alexander Pavlov's [BASE64 VLQ CODEC](http://murzwin.com/base64vlq.html) was 
also quite helpful in generating known values for the unit test suite.

