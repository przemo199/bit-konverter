# bit-konverter

BitKonverter is a small utility library inspired by C#'s BitConverter class which provides handy conversion methods from common Kotlin data types into ByteArray and vice versa.

Additionally, a set of extension functions is available to simplify library usage, simply look for the ```toByteArray``` and ```fromByteArray``` methods on the type you wish to convert.

Supported types:
 - Boolean
 - Char
 - Short
 - UShort
 - Int
 - UInt
 - Long
 - ULong
 - Float
 - Double
 - String

## Usage

Convert to ```ByteArray```
```kotlin
println(BitKonverter.getBytes(1).contentToString()) // [1, 0, 0, 0]
println(1.toByteArray().contentToString()) // [1, 0, 0, 0]
```

Convert from ```ByteArray```

```kotlin
println(BitKonverter.toInt(byteArrayOf(1, 0, 0, 0)) // 1
println(Int.fromByteArray(byteArrayOf(1, 0, 0, 0))) // 1
```

## Limitations

Currently only JVM and little-endianness is supported.
