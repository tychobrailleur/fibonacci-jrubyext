$: << File.join(File.dirname(__FILE__), "target")

require 'fibonacci'

seq = Com::Weblogism::Myjrubyext::Fibonacci.new
puts (1..10).map { |i| seq.fib(i) }.join(' ')
