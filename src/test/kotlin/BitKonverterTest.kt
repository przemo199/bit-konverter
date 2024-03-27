import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class BitKonverterTest {

    @Test
    fun boolean_test() {
        assertTrue(byteArrayOf(1).contentEquals(BitKonverter.getBytes(true)))
        assertTrue(byteArrayOf(0).contentEquals(BitKonverter.getBytes(false)))
        assertEquals(true, BitKonverter.toBoolean(byteArrayOf(1)))
        assertEquals(false, BitKonverter.toBoolean(byteArrayOf(0)))
    }

    @Test
    fun char_symmetry_test() {
        (-32768..32767).map(Int::toChar).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(2, byteArray.size)
            assertEquals(it, BitKonverter.toChar(byteArray))
        }
    }

    @Test
    fun short_test() {
        assertTrue(byteArrayOf(1, 0).contentEquals(BitKonverter.getBytes(1.toShort())))
        assertTrue(byteArrayOf(0, 0).contentEquals(BitKonverter.getBytes(0.toShort())))
        assertTrue(byteArrayOf(-1, -1).contentEquals(BitKonverter.getBytes((-1).toShort())))
    }

    @Test
    fun short_symmetry_test() {
        (-32768..32767).map(Int::toShort).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(2, byteArray.size)
            assertEquals(it, BitKonverter.toShort(byteArray))
        }
    }

    @Test
    fun int_test() {
        assertTrue(byteArrayOf(1, 0, 0, 0).contentEquals(BitKonverter.getBytes(1)))
        assertTrue(byteArrayOf(0, 0, 0, 0).contentEquals(BitKonverter.getBytes(0)))
        assertTrue(byteArrayOf(-1, -1, -1, -1).contentEquals(BitKonverter.getBytes(-1)))
    }

    @Test
    fun int_symmetry_test() {
        (-327690..327680).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(4, byteArray.size)
            assertEquals(it, BitKonverter.toInt(byteArray))
        }
    }

    @Test
    fun long_test() {
        assertTrue(byteArrayOf(1, 0, 0, 0, 0, 0, 0, 0).contentEquals(BitKonverter.getBytes(1.toLong())))
        assertTrue(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0).contentEquals(BitKonverter.getBytes(0.toLong())))
        assertTrue(byteArrayOf(-1, -1, -1, -1, -1, -1, -1, -1).contentEquals(BitKonverter.getBytes((-1).toLong())))
    }

    @Test
    fun long_symmetry_test() {
        (-327690..327680).map(Int::toLong).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(8, byteArray.size)
            assertEquals(it, BitKonverter.toLong(byteArray))
        }
    }

    @Test
    fun float_test() {
        assertTrue(BitKonverter.toFloat(byteArrayOf(0, 0, 0, 65)).equals(8.toFloat()))
        assertTrue(BitKonverter.toFloat(byteArrayOf(0, 0, 0, 0)).equals(0.toFloat()))
        assertTrue(BitKonverter.toFloat(byteArrayOf(0, 0, 64, 65)).equals(12.toFloat()))
    }

    @Test
    fun float_symmetry_test() {
        (-327697..32768).map(Int::toFloat).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(4, byteArray.size)
            assertEquals(it, BitKonverter.toFloat(byteArray))
        }
    }

    @Test
    fun double_test() {
        assertTrue(BitKonverter.toDouble(byteArrayOf(0, 0, 0, 0, 0, 0, 32, 64)).equals(8.toDouble()))
        assertTrue(BitKonverter.toDouble(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0)).equals(0.toDouble()))
        assertTrue(BitKonverter.toDouble(byteArrayOf(0, 0, 0, 0, 0, 0, 40, 64)).equals(12.toDouble()))
    }

    @Test
    fun double_symmetry_test() {
        (-327680..327690).map(Int::toDouble).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(8, byteArray.size)
            assertEquals(it, BitKonverter.toDouble(byteArray))
        }
    }

    @Test
    fun ushort_test() {
        assertTrue(byteArrayOf(1, 0).contentEquals(BitKonverter.getBytes(1.toUShort())))
        assertTrue(byteArrayOf(0, 0).contentEquals(BitKonverter.getBytes(0.toUShort())))
        assertTrue(byteArrayOf(-1, -1).contentEquals(BitKonverter.getBytes((-1).toUShort())))
    }

    @Test
    fun ushort_symmetry_test() {
        (-32768..32769).map(Int::toUShort).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(2, byteArray.size)
            assertEquals(it, BitKonverter.toUShort(byteArray))
        }
    }

    @Test
    fun uint_test() {
        assertTrue(byteArrayOf(1, 0, 0, 0).contentEquals(BitKonverter.getBytes(1.toUInt())))
        assertTrue(byteArrayOf(0, 0, 0, 0).contentEquals(BitKonverter.getBytes(0.toUInt())))
        assertTrue(byteArrayOf(-1, -1, -1, -1).contentEquals(BitKonverter.getBytes((-1).toUInt())))
    }

    @Test
    fun uint_symmetry_test() {
        (-327680..327690).map(Int::toUInt).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(4, byteArray.size)
            assertEquals(it, BitKonverter.toUInt(byteArray))
        }
    }

    @Test
    fun ulong_test() {
        assertTrue(byteArrayOf(1, 0).contentEquals(BitKonverter.getBytes(1.toUShort())))
        assertTrue(byteArrayOf(0, 0).contentEquals(BitKonverter.getBytes(0.toUShort())))
        assertTrue(byteArrayOf(-1, -1).contentEquals(BitKonverter.getBytes((-1).toUShort())))
    }

    @Test
    fun ulong_symmetry_test() {
        (-327680..327690).map(Int::toULong).forEach {
            val byteArray = BitKonverter.getBytes(it)
            assertEquals(8, byteArray.size)
            assertEquals(it, BitKonverter.toULong(byteArray))
        }
    }

    @Test
    fun string_test() {
        val text = "test text"
        assertEquals(text, BitKonverter.toString(text.toByteArray()))
    }
}
