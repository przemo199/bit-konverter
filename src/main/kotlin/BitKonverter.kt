package org.github.przemo199.bitkonverter

import kotlin.jvm.JvmStatic
import kotlin.math.min

/**
 * The BitKonverter object contains methods for converting an array of bytes to the data types,
 * as well as for converting a data type to an array of bytes.
 */
object BitKonverter {
    private const val BYTE_SIZE: Int = 8

    /**
     * byte identity mask
     */
    private const val BYTE_MASK: Int = 0xFF

    /**
     * This field indicates the "endianess" of the architecture.
     */
    @JvmField
    val isLittleEndian: Boolean = true

    /**
     * Returns the specified [Boolean] value as a [ByteArray].
     *
     * @param value [Boolean] to convert
     * @return A [ByteArray] with length one
     */
    @JvmStatic
    fun getBytes(value: Boolean): ByteArray {
        return byteArrayOf(if (value) 1 else 0)
    }

    /**
     * Returns the specified [Char] value as a [ByteArray].
     *
     * @param value [Char] to convert
     * @return A [ByteArray] with length two
     */
    @JvmStatic
    fun getBytes(value: Char): ByteArray {
        return getBytes(value.code.toShort())
    }

    /**
     * Returns the specified [Short] value as a [ByteArray].
     *
     * @param value [Short] to convert
     * @return A [ByteArray] with length two
     */
    @JvmStatic
    fun getBytes(value: Short): ByteArray {
        return ByteArray(Short.SIZE_BYTES) {
            (value.toInt() shr (it * BYTE_SIZE)).toByte()
        }
    }

    /**
     * Returns the specified [Int] value as a [ByteArray].
     *
     * @param value [Int] to convert
     * @return A [ByteArray] with length four
     */
    @JvmStatic
    fun getBytes(value: Int): ByteArray {
        return ByteArray(Int.SIZE_BYTES) {
            (value shr (it * BYTE_SIZE)).toByte()
        }
    }

    /**
     * Returns the specified [Long] value as a [ByteArray].
     *
     * @param value [Long] to convert
     * @return A [ByteArray] with length eight
     */
    @JvmStatic
    fun getBytes(value: Long): ByteArray {
        return ByteArray(Long.SIZE_BYTES) {
            (value shr (it * BYTE_SIZE)).toByte()
        }
    }

    /**
     * Returns the specified [UShort] value as a [ByteArray].
     *
     * @param value [UShort] to convert
     * @return A [ByteArray] with length two
     */
    @JvmStatic
    fun getBytes(value: UShort): ByteArray {
        return getBytes(value.toShort())
    }

    /**
     * Returns the specified [UInt] value as a [ByteArray].
     *
     * @param value [UInt] to convert
     * @return A [ByteArray] with length four
     */
    @JvmStatic
    fun getBytes(value: UInt): ByteArray {
        return getBytes(value.toInt())
    }

    /**
     * Returns the specified [ULong] value as a [ByteArray].
     *
     * @param value [ULong] to convert
     * @return A [ByteArray] with length eight
     */
    @JvmStatic
    fun getBytes(value: ULong): ByteArray {
        return getBytes(value.toLong())
    }

    /**
     * Returns the specified [Float] value as a [ByteArray].
     *
     * @param value [Float] to convert
     * @return A [ByteArray] with length four
     */
    @JvmStatic
    fun getBytes(value: Float): ByteArray {
        return getBytes(value.toRawBits())
    }

    /**
     * Returns the specified [Double] value as a [ByteArray].
     *
     * @param value [Double] to convert
     * @return A [ByteArray] with length eight
     */
    @JvmStatic
    fun getBytes(value: Double): ByteArray {
        return getBytes(doubleBitsToLong(value))
    }

    /**
     * Converts a [ByteArray] into a [Char]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [Char] converted from [bytes]
     */
    @JvmStatic
    fun toChar(bytes: ByteArray, startIndex: Int = 0): Char {
        return toInt(bytes, startIndex).toChar()
    }

    /**
     * Converts a [ByteArray] into a [Short]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     * @return [Short] converted from [bytes]
     */
    @JvmStatic
    fun toShort(bytes: ByteArray, startIndex: Int = 0): Short {
        return toInt(bytes, startIndex).toShort()
    }

    /**
     * Converts a [ByteArray] into an [Int]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [Int] converted from [bytes]
     */
    @JvmStatic
    fun toInt(bytes: ByteArray, startIndex: Int = 0): Int {
        return (startIndex..<min(startIndex + Int.SIZE_BYTES, bytes.size)).withIndex().fold(0) { accumulator, (index, arrayIndex) ->
            (bytes[arrayIndex].toInt() and BYTE_MASK shl (BYTE_SIZE * index)) or accumulator
        }
    }

    /**
     * Converts a [ByteArray] into a [Long]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [Long] converted from [bytes]
     */
    @JvmStatic
    fun toLong(bytes: ByteArray, startIndex: Int = 0): Long {
        return (startIndex..<min(startIndex + Long.SIZE_BYTES, bytes.size)).withIndex().fold(0.toLong()) { accumulator, (index, arrayIndex) ->
            (bytes[arrayIndex].toLong() and BYTE_MASK.toLong() shl (BYTE_SIZE * index)) or accumulator
        }
    }

    /**
     * Converts a [ByteArray] into a [UShort]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [UShort] converted from [bytes]
     */
    @JvmStatic
    fun toUShort(bytes: ByteArray, startIndex: Int = 0): UShort {
        return toInt(bytes, startIndex).toUShort()
    }

    /**
     * Converts a [ByteArray] into a [UInt]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [UInt] converted from [bytes]
     */
    @JvmStatic
    fun toUInt(bytes: ByteArray, startIndex: Int = 0): UInt {
        return toInt(bytes, startIndex).toUInt()
    }

    /**
     * Converts a [ByteArray] into a [ULong]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [ULong] converted from [bytes]
     */
    @JvmStatic
    fun toULong(bytes: ByteArray, startIndex: Int = 0): ULong {
        return toLong(bytes, startIndex).toULong()
    }

    /**
     * Converts a [ByteArray] into a [Float]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [Float] converted from [bytes]
     */
    @JvmStatic
    fun toFloat(bytes: ByteArray, startIndex: Int = 0): Float {
        return Float.fromBits(toInt(bytes, startIndex))
    }

    /**
     * Converts a [ByteArray] into a [Double]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [Double] converted from [bytes]
     */
    @JvmStatic
    fun toDouble(bytes: ByteArray, startIndex: Int = 0): Double {
        return Double.fromBits(toLong(bytes, startIndex))
    }

    /**
     * Converts a [ByteArray] into a [String]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [String] converted from [bytes]
     */
    @JvmStatic
    fun toString(bytes: ByteArray, startIndex: Int = 0, endIndex: Int = bytes.size): String {
        return String(bytes.sliceArray(startIndex..<endIndex))
    }

    /**
     * Converts a [ByteArray] into a [Boolean]
     *
     * @param bytes [ByteArray] to convert
     * @param startIndex [Int] indicating first byte to convert
     *
     * @return [Boolean] converted from [bytes]
     */
    @JvmStatic
    fun toBoolean(bytes: ByteArray, startIndex: Int = 0): Boolean {
        return bytes[startIndex] == 1.toByte()
    }

    /**
     * Converts an [Int] bits into a [Float]
     */
    @JvmStatic
    fun intBitsToFloat(value: Int): Float {
        return toFloat(getBytes(value))
    }

    /**
     * Converts a [Double] bits into a [Long]
     */
    @JvmStatic
    fun doubleBitsToLong(value: Double): Long {
        return value.toRawBits()
    }

    /**
     * Converts a [Long] bits into a [Double]
     */
    @JvmStatic
    fun longBitsToDouble(value: Long): Double {
        return Double.fromBits(value)
    }
}

fun Boolean.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun Boolean.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): Boolean {
    return BitKonverter.toBoolean(bytes, startIndex)
}

fun Char.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun Char.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): Char {
    return BitKonverter.toChar(bytes, startIndex)
}

fun Short.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun Short.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): Short {
    return BitKonverter.toShort(bytes, startIndex)
}

fun UShort.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun UShort.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): UShort {
    return BitKonverter.toUShort(bytes, startIndex)
}

fun Int.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun Int.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): Int {
    return BitKonverter.toInt(bytes, startIndex)
}

fun UInt.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun UInt.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): UInt {
    return BitKonverter.toUInt(bytes, startIndex)
}

fun Long.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun Long.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): Long {
    return BitKonverter.toLong(bytes, startIndex)
}

fun ULong.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun ULong.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): ULong {
    return BitKonverter.toULong(bytes, startIndex)
}

fun Float.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun Float.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): Float {
    return BitKonverter.toFloat(bytes, startIndex)
}

fun Double.toByteArray(): ByteArray {
    return BitKonverter.getBytes(this)
}

fun Double.Companion.fromByteArray(bytes: ByteArray, startIndex: Int = 0): Double {
    return BitKonverter.toDouble(bytes, startIndex)
}

fun ByteArray.toBoolean(startIndex: Int = 0): Boolean {
    return BitKonverter.toBoolean(this, startIndex)
}

fun ByteArray.toChar(startIndex: Int = 0): Char {
    return BitKonverter.toChar(this, startIndex)
}

fun ByteArray.toShort(startIndex: Int = 0): Short {
    return BitKonverter.toShort(this, startIndex)
}

fun ByteArray.toUShort(startIndex: Int = 0): UShort {
    return BitKonverter.toUShort(this, startIndex)
}

fun ByteArray.toInt(startIndex: Int = 0): Int {
    return BitKonverter.toInt(this, startIndex)
}

fun ByteArray.toUInt(startIndex: Int = 0): UInt {
    return BitKonverter.toUInt(this, startIndex)
}

fun ByteArray.toLong(startIndex: Int = 0): Long {
    return BitKonverter.toLong(this, startIndex)
}

fun ByteArray.toULong(startIndex: Int = 0): ULong {
    return BitKonverter.toULong(this, startIndex)
}

fun ByteArray.toFloat(startIndex: Int = 0): Float {
    return BitKonverter.toFloat(this, startIndex)
}

fun ByteArray.toDouble(startIndex: Int = 0): Double {
    return BitKonverter.toDouble(this, startIndex)
}
