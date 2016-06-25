package nl.svendubbeld.inject

import nl.svendubbeld.inject.exception.ConstructorException
import nl.svendubbeld.inject.exception.TypeNotResolvedException
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class InjectorTest {

    @Before
    fun setUp() {
        Injector.reset();
    }

    @Test(expected = TypeNotResolvedException::class)
    fun testNotRegistered() {
        var dataObject: DataObject = Injector.resolve<DataObject>();
    }

    @Test(expected = TypeNotResolvedException::class)
    fun testNotRegisteredParam() {
        var dataObject: DataObject = Injector.resolve(DataObject::class.java) as DataObject;
    }

    @Test(expected = ConstructorException::class)
    fun testRegisterConstructorless() {
        Injector.register<DataObject, ConstructorlessDataObject>();

        Injector.resolve<DataObject>();
    }

    @Test
    fun testRegister() {
        Injector.register<DataObject, DerivedDataObject>();

        Assert.assertTrue(Injector.resolve<DataObject>() is DerivedDataObject)
    }

    @Test
    fun testRegisterParam() {
        Injector.register(DataObject::class.java, DerivedDataObject::class.java);

        Assert.assertTrue(Injector.resolve<DataObject>() is DerivedDataObject)
    }

    @Test
    fun testResolve() {
        Injector.register<DataObject, DerivedDataObject>();

        Assert.assertTrue(Injector.resolve<DataObject>() is DerivedDataObject)
    }

    @Test
    fun testResolveParam() {
        Injector.register<DataObject, DerivedDataObject>();

        Assert.assertTrue(Injector.resolve(DataObject::class.java) is DerivedDataObject)
    }
}