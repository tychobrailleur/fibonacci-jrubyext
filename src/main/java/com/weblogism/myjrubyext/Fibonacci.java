package com.weblogism.myjrubyext;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyFixnum;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * This class implements the logic of the Ruby <code>Fibonacci</code> class by
 * using the JRuby annotations.
 * 
 * @author Sébastien Le Callonnec
 */
@JRubyClass(name = "Com::Weblogism::Myjrubyext::Fibonacci")
public class Fibonacci extends RubyObject {
    public Fibonacci(Ruby runtime, RubyClass metaClass) {
        super(runtime, metaClass);
    }

    @JRubyMethod(name = "new", meta = true, rest = true)
    public static IRubyObject rbNew(ThreadContext context, IRubyObject klazz, IRubyObject[] args) {
        Fibonacci fibonnaci = (Fibonacci) ((RubyClass) klazz).allocate();
        return fibonnaci;
    }

    /**
     * calculates the <i>n</i>th term of the <a
     * href="http://oeis.org/A000045">Fibonacci sequence</a>. It requires a
     * <code>Fixnum</code> as a parameter — it throws a <code>TypeError</code>
     * otherwise.
     */
    @JRubyMethod(name = "fib")
    public IRubyObject fib(ThreadContext context, IRubyObject other) {

        Ruby runtime = context.getRuntime();
        if (other instanceof RubyFixnum) {
            RubyFixnum num = (RubyFixnum) other;
            return RubyFixnum.newFixnum(runtime,
                    fibSequence(num.getLongValue()));
        }

        throw runtime.newTypeError(other, runtime.getFixnum());
    }

    private long fibSequence(long number) {
        if (number == 1) {
            return 0;
        } else if (number == 2) {
            return 1;
        } else {
            return fibSequence(number - 2) + fibSequence(number - 1);
        }
    }
}
