require File.expand_path(File.join(File.dirname(__FILE__), "spec_helper"))

describe 'Fibonacci' do
  subject { Com::Weblogism::Myjrubyext::Fibonacci.new }


  it 'returns 0 for 1' do
    subject.fib(1).should == 0
  end

  it 'returns 1 for 2' do
    subject.fib(2).should == 1
  end

  it 'returns 1 for 3' do
    subject.fib(3).should == 1
  end

  it 'returns 34 for 10' do
    subject.fib(10).should == 34
  end

  it 'raises an error for a non-numeric arg' do
    expect{ subject.fib("4") }.to raise_error(TypeError)
  end
end
