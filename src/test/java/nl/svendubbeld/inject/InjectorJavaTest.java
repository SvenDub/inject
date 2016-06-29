package nl.svendubbeld.inject;

import nl.svendubbeld.inject.exception.ConstructorException;
import nl.svendubbeld.inject.exception.TypeNotResolvedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InjectorJavaTest {

    private Injector mInjector = Injector.INSTANCE;

    @Before
    public void setUp() {
        mInjector.reset();
    }

    @Test(expected = TypeNotResolvedException.class)
    public void testNotRegistered() {
        DataObject dataObject = mInjector.<DataObject>resolve(DataObject.class);
    }

    @Test
    public void testRegister() {
        mInjector.register(DataObject.class, DerivedDataObject.class);

        Assert.assertTrue(mInjector.<DataObject>resolve(DataObject.class) instanceof DerivedDataObject);
    }

    @Test(expected = ConstructorException.class)
    public void testRegisterConstructorless() {
        mInjector.register(DataObject.class, ConstructorlessDataObject.class);

        mInjector.<DataObject>resolve(DataObject.class);
    }
}
