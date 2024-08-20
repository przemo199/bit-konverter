# bit-konverter

[![Tests](https://github.com/przemo199/bit-konverter/actions/workflows/tests.yaml/badge.svg)](https://github.com/przemo199/bit-konverter/actions/workflows/tests.yaml)
[![](https://jitpack.io/v/przemo199/bit-konverter.svg)](https://jitpack.io/#przemo199/bit-konverter)


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
BitKonverter.getBytes(1) // [1, 0, 0, 0]
1.toByteArray() // [1, 0, 0, 0]
```

Convert from ```ByteArray```

```kotlin
BitKonverter.toInt(byteArrayOf(1, 0, 0, 0)) // 1
Int.fromByteArray(byteArrayOf(1, 0, 0, 0)) // 1
```

## Limitations

Currently only JVM and little-endianness is supported.
