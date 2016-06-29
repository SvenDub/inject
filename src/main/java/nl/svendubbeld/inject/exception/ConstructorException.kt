package nl.svendubbeld.inject.exception

/**
 * Thrown when there is no suitable constructor available.
 */
class ConstructorException : Exception("Type contains no constructors.")