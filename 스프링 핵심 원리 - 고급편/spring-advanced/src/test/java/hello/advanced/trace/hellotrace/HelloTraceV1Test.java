package hello.advanced.trace.hellotrace;

import hello.advanced.trace.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
        // 00:56:30.117 [Test worker] INFO hello.advanced.trace.HelloTraceV1 - [3b8e8e4b] hello
        // 00:56:30.120 [Test worker] INFO hello.advanced.trace.HelloTraceV1 - [3b8e8e4b] hello time=4ms
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
        // 00:57:08.103 [Test worker] INFO hello.advanced.trace.HelloTraceV1 - [696b38cb] hello
        // 00:57:08.107 [Test worker] INFO hello.advanced.trace.HelloTraceV1 - [696b38cb] hello time=5ms ex=java.lang.IllegalStateException
    }
}
