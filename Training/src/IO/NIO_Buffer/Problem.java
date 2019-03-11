package IO.NIO_Buffer;

import Interfaces.IProblem;

import java.nio.IntBuffer;

public class Problem implements IProblem {
    @Override
    public void solve() {
        System.out.println("============TESTING IntBuffer METHODS=======================================================");
        System.out.println("============GENERAL BUFFER INFORMATION=======================================================");
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("buffer.toString(): " + buffer.toString());
        System.out.println("buffer.isDirect(): " + buffer.isDirect());
        System.out.println("buffer.isReadOnly(): " + buffer.isReadOnly());
        System.out.println("buffer.hasArray(): " + buffer.hasArray());

        for(int i = 1; i < 11; i++){
            buffer.put(i);
        }
        System.out.println("======REMAINING ELEMENTS DEFINING===========================================================");
        System.out.println("buffer.position(): " + buffer.position());
        System.out.println("buffer.hasRemaining(): " + buffer.hasRemaining());
        System.out.println("buffer.remaining(): " + buffer.remaining());
        buffer.rewind();
        System.out.println("buffer.rewind()");
        System.out.println("buffer.position(): " + buffer.position());
        System.out.println("buffer.hasRemaining(): " + buffer.hasRemaining());
        System.out.println("buffer.remaining(): " + buffer.remaining());

        System.out.println("=======BUFFER CAPACITY AND LIMIT, CHANGING LIMIT============================================");
        System.out.println("buffer.capacity(): " + buffer.capacity());
        System.out.println("buffer.limit(): " + buffer.limit());
        buffer.limit(8);    //Не может быть больше, чем была задана capacity
        System.out.println("buffer.limit(8)");
        System.out.println("buffer.limit(): " + buffer.limit());

        System.out.println("========MOVING BY BUFFER====================================================================");
        System.out.println("buffer.position(): " + buffer.position());
        buffer.mark();
        System.out.println("buffer.mark()");
        System.out.println("buffer.get(): " + buffer.get());
        System.out.println("buffer.position(): " + buffer.position());
        buffer.reset();
        System.out.println("buffer.reset()");
        System.out.println("buffer.position(): " + buffer.position());
        buffer.position(7); //Если limit=8, то position=7 - последний элемент, к котором можно обращаться
        System.out.println("buffer.position(7)");
        System.out.println("buffer.position(): " + buffer.position());
        System.out.println("buffer.get(): " + buffer.get());

        buffer.position(4);
        System.out.println("buffer.position(4)");
        System.out.println("buffer.position(): " + buffer.position());
        buffer.flip();
        System.out.println("buffer.flip()");
        System.out.println("buffer.position(): " + buffer.position());
        System.out.println("buffer.capacity(): " + buffer.capacity());
        System.out.println("buffer.limit(): " + buffer.limit());
        System.out.println("buffer.get(): " + buffer.get());
    }
}
