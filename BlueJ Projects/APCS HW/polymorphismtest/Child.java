package polymorphismtest;
import java.lang.reflect.*;
public class Child extends Person {
    public static Child construct(Object... args) {
        Class[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) types[i] = args[i].getClass();
        try
        {
            try
            {
                try
                {
                    try
                    {
                        return Person.class.getConstructor(types).newInstance(args);
                    }
                    catch (InvocationTargetException ite)
                    {
                        ite.printStackTrace();
                    }
                }
                catch (IllegalAccessException iae)
                {
                    iae.printStackTrace();
                }
            }
            catch (InstantiationException ie)
            {
                ie.printStackTrace();
            }
        }
        catch (NoSuchMethodException nsme)
        {
            nsme.printStackTrace();
        }
        return null;
    }
}
