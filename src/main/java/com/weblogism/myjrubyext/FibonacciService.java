package com.weblogism.myjrubyext;

import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

/**
 * Service defining the module and loading the {@link Fibonacci} class.
 * 
 * @author Sébastien Le Callonnec
 */
public class FibonacciService implements BasicLibraryService {

    // The allocator instantiates the {@link Fibonacci} object.
    private final static ObjectAllocator FIBONACCI_ALLOCATOR = new ObjectAllocator() {
        @Override
        public IRubyObject allocate(Ruby runtime, RubyClass klass) {
            return new Fibonacci(runtime, klass);
        }
    };

    @Override
    public boolean basicLoad(Ruby runtime) throws IOException {
        // The Ruby module structure must mirror the Java package name.
        // Here Com::Weblogism::Myjrubyext --> com.weblogism.myjrubyext
        RubyModule com = runtime.defineModule("Com");
        RubyModule weblogism = com.defineModuleUnder("Weblogism");
        RubyModule myjrubyext = weblogism.defineModuleUnder("Myjrubyext");
        RubyClass fibonacciClass = myjrubyext.defineClassUnder("Fibonacci", runtime.getObject(), FIBONACCI_ALLOCATOR);

        fibonacciClass.defineAnnotatedMethods(Fibonacci.class);

        return true;
    }
}