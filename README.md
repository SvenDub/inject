[![Build Status](https://travis-ci.org/SvenDub/inject.svg?branch=master)](https://travis-ci.org/SvenDub/inject)
[![Maven Central](https://img.shields.io/maven-central/v/nl.svendubbeld/inject.svg)](https://maven-badges.herokuapp.com/maven-central/nl.svendubbeld/inject)
# Inject

Inject is a small Dependency Injection tool for the JVM. It is written in Kotlin and also supports Java.

## Usage

### Java

```java
// Register types
Injector.INSTANCE.inject(Contract.class, Implementation.class);

// Resolve types
Contract contract = (Contract) Injector.INSTANCE.resolve(Contract.class);
```

### Kotlin
```kotlin
// Register types
Injector.register<Contract, Implementation>();

// Resolve types
var contract: Contract = Injector.resolve<Contract>();
```

## License
    The MIT License (MIT)

    Copyright (c) 2016 Sven Dubbeld

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in
    the Software without restriction, including without limitation the rights to
    use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
    the Software, and to permit persons to whom the Software is furnished to do so,
    subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
    COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
    IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
    CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.