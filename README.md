Example of a JRuby extension in Java.

# Build

    $ mvn package

# Execution

    $ jruby test.rb

This script prints out the 10 first terms of the Fibonacci sequence.  To use the JRuby extension:

    require 'fibonacci'

    seq = Com::Weblogism::Myjrubyext::Fibonacci.new
    puts seq.fib(8)
