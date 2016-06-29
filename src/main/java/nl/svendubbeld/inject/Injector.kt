package nl.svendubbeld.inject

import nl.svendubbeld.inject.exception.ConstructorException
import nl.svendubbeld.inject.exception.TypeNotResolvedException
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Handles dependency injection. Types can be registered using [register] and resolved using [resolve].
 */
object Injector {

    /**
     * Mapping of all types to their implementation.
     */
    private var _types: MutableMap<Class<*>, Class<*>> = ConcurrentHashMap();

    /**
     * Mapping of all instances of implementations.
     */
    private var _instances: MutableMap<Class<*>, Any> = ConcurrentHashMap();

    /**
     * Register the implementation of a type.
     *
     * @param contract The type to register.
     * @param implementation The implementation of the type.
     */
    inline fun <reified Contract : Any, reified Implementation : Any> register() = register(Contract::class.java, Implementation::class.java);

    /**
     * Register the implementation of a type.
     *
     * @param contract The type to register.
     * @param implementation The implementation of the type.
     */
    fun register(contract: Class<*>, implementation: Class<*>) = _types.set(contract, implementation);

    /**
     * Register multiple types and their implementations.
     *
     * @param types The Map that contains the types and implementations. The left type is the contract, the right is the implementation.
     */
    fun register(types: Map<Class<*>, Class<*>>) = types.forEach { register(it.key, it.value) }

    /**
     * Resolve a given type to its implementation.
     *
     * @param T The type to resolve.
     * @return The implementation of the given type.
     */
    inline fun <reified T : Any> resolve(): T = resolve(T::class.java as Class<*>) as T

    /**
     * Resolve a given type to its implementation.
     *
     * @param T The type to resolve.
     * @return The implementation of the given type.
     */
    fun resolve(contract: Class<*>): Any {
        if (_instances.containsKey(contract)) {
            return _instances[contract]!!;
        }

        if (_types.containsKey(contract)) {

            // Resolve the type
            val implementation: Class<*> = _types[contract]!!;

            // Get constructor
            if (implementation.constructors.size == 0) {
                throw ConstructorException();
            }

            val constructor = implementation.constructors[0];
            val constructorParams = constructor.parameters;

            // Use default constructor if available
            if (constructorParams.size == 1) {
                val instance = constructor.newInstance();
                _instances[contract] = instance;
                return instance;
            }

            // Create using available constructor that takes parameters that are registered and therefore can also be injected
            val params: List<Any> = ArrayList(constructorParams.size);
            params.plus(constructorParams.map { param -> resolve(param.type) });

            val instance = constructor.newInstance(*params.toTypedArray())
            _instances[contract] = instance;
            return instance;
        } else {
            throw TypeNotResolvedException(contract);
        }
    }

    /**
     * Remove all data from the Injector.
     */
    fun reset() {
        _types.clear();
        _instances.clear();
    }
}
